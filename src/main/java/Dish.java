import com.fasterxml.jackson.annotation.*;

import java.io.*;
import java.util.Objects;

public class Dish implements Serializable {
    private String name;
    private double caloriePer100g;
    private double massInG;

    // this constructor is needed only for jackson deserialization
    @JsonCreator
    protected Dish(
            @JsonProperty("dish-name") String name,
            @JsonProperty("calorie-per-100g") double caloriePer100g,
            @JsonProperty("mass-in-g") double massInG,
            @JsonProperty("calorie-total") double calorieTotal
    ) {
        this(name, caloriePer100g, massInG);
    }

    public Dish(
            String name,
            double caloriePer100g,
            double massInG
    ) {
        this.name = name;
        if (caloriePer100g >= 0 && massInG > 0) {
            this.caloriePer100g = caloriePer100g;
            this.massInG = massInG;
        } else throw new IllegalArgumentException("wrong arguments");
    }

    @JsonGetter("dish-name")
    public String getName() {
        return name;
    }

    @JsonSetter("dish-name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonGetter("calorie-per-100g")
    public double getCaloriePer100g() {
        return caloriePer100g;
    }

    @JsonSetter("calorie-per-100g")
    public void setCaloriePer100g(int caloriePer100g) {
        if (caloriePer100g >= 0) {
            this.caloriePer100g = caloriePer100g;
        } else throw new IllegalArgumentException("wrong argument");
    }

    @JsonGetter("mass-in-g")
    public double getMassInG() {
        return massInG;
    }

    @JsonSetter("mass-in-g")
    public void setMassInG(int massInG) {
        if (massInG > 0) {
            this.massInG = massInG;
        } else throw new IllegalArgumentException("wrong argument");
    }

    @JsonGetter("calorie-total")
    public double getCalorieTotal() {
        return this.caloriePer100g * this.massInG / 100;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return
                Objects.equals(this.name, dish.name)
                && Double.compare(this.caloriePer100g, dish.caloriePer100g) == 0
                && Double.compare(this.massInG, dish.massInG) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.caloriePer100g, this.massInG);
    }
}
