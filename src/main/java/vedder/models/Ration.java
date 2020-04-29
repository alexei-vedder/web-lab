package vedder.models;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.xml.bind.annotation.*;

@XmlType
@XmlRootElement(name = "ration")
public class Ration implements Serializable {
    @JsonProperty("dishList")
    private List<Dish> dishList;
    @JsonProperty
    private Timestamp id;

    @Deprecated
    public Ration() {
        this.dishList = new ArrayList<>();
        this.id = new Timestamp(System.currentTimeMillis());
    }

    public Ration(List<Dish> dishList) {
        this.dishList = dishList;
        this.id = new Timestamp(System.currentTimeMillis());
    }

    public Ration(Timestamp id) {
        this.dishList = new ArrayList<>();
        this.id = id;
    }

    @JsonCreator
    public Ration(@JsonProperty("dishList") List<Dish> dishList, @JsonProperty("id") Timestamp id) {
        this.dishList = dishList;
        this.id = id;
    }

    @JsonIgnore
    public int getSize() {
        return this.dishList.size();
    }

    @JsonIgnore
    public Timestamp getId() {
        return this.id;
    }

    @XmlElement(name = "ration-id")
    public String getIdAsString() {
        return this.id.toString();
    }

    @JsonIgnore
    @XmlElement(name = "dish")
    @XmlElementWrapper(name = "dishes")
    public List<Dish> getDishList() {
        return this.dishList;
    }

    public void setDishList(List<Dish> dishList) {
        this.dishList = dishList;
    }

    public void setId(Timestamp id) {
        this.id = id;
    }

    public void remove(int index) {
        if (0 <= index && index < this.dishList.size()) {
            this.dishList.remove(index);
        } else throw new IllegalArgumentException();
    }

    public void add(Dish dish) {
        this.dishList.add(dish);
    }

    public void add(List<Dish> dishes) {
        this.dishList.addAll(dishes);
    }

    public void readFromJSON(File file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Ration tempRation = objectMapper.readValue(file, Ration.class);
        this.dishList = tempRation.getDishList();
    }

    public void writeToJSON(File file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(file, this);
    }

    public void sort() {
        Comparator<Dish> calorieTotalComparator = Comparator.comparingDouble(Dish::getCalorieTotal);
        this.dishList.sort(calorieTotalComparator);
    }

    public void filterUniqueItems() {
        this.dishList = this.dishList.stream().distinct().collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return getIdAsString() + " " + getDishList();
    }
}
