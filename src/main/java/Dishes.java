import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Dishes implements Serializable {
    @JsonProperty("dishesList")
    private List<Dish> dishesList;
    @JsonProperty
    private Date id;

    public Dishes() {
        this.dishesList = new ArrayList<>();
        this.id = new Date();
    }

    @JsonCreator
    protected Dishes(@JsonProperty("dishesList") List<Dish> dishesList) {
        this.dishesList = dishesList;
        this.id = new Date();
    }

    @JsonIgnore
    public int getSize() {
        return this.dishesList.size();
    }

    @JsonIgnore
    public Date getId() {
        return this.id;
    }

    @JsonIgnore
    public List<Dish> getDishes() {
        return this.dishesList;
    }

    public void remove(int index) {
        if (0 <= index && index < this.dishesList.size()) {
            this.dishesList.remove(index);
        } else throw new IllegalArgumentException();
    }

    public void add(Dish dish) {
        this.dishesList.add(dish);
    }

    public void add(List<Dish> dishes) {
        this.dishesList.addAll(dishes);
    }

    public void readFromJSON(File file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Dishes tempDishes = objectMapper.readValue(file, Dishes.class);
        this.dishesList = tempDishes.getDishes();
    }

    public void writeToJSON(File file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(file, this);
    }

    public void sort() {
        Comparator<Dish> calorieTotalComparator = Comparator.comparingDouble(Dish::getCalorieTotal);
        this.dishesList.sort(calorieTotalComparator);
    }

    public void filterUniqueItems() {
        this.dishesList = this.dishesList.stream().distinct().collect(Collectors.toList());
    }
}
