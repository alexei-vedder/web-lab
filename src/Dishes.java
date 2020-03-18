import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.ObjectMapper;

public class Dishes implements Serializable {
    @JsonProperty
    private List<Dish> dishesList;
    private Date id;

    public Dishes() {
        this.dishesList = new ArrayList<>();
        this.id = new Date();
    }

    public Dishes(List<Dish> dishesList) {
        this.dishesList = dishesList;
        this.id = new Date();
    }

    @JsonCreator
    public Dishes(
            @JsonProperty("id") Date id,
            @JsonProperty("dishesList") List<Dish> dishesList
    ) {
        this.id = id;
        this.dishesList = dishesList;
    }

    @JsonIgnore
    public int getSize() {
        return this.dishesList.size();
    }

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
