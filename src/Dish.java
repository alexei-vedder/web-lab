import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

import java.io.*;
import java.util.Objects;

public class Dish implements Serializable {
    private String name;
    private double caloriePer100g;
    private double calorieTotal;
    private double mass;

/*    public Dish(String name, double caloriePer100g, double calorieTotal, double mass) {
        this.name = name;
        if (caloriePer100g >= 0 && calorieTotal >= 0 && mass > 0) {
            this.caloriePer100g = caloriePer100g;
            this.calorieTotal = calorieTotal;
            this.mass = mass;
        } else throw new IllegalArgumentException("wrong arguments");
    }*/

    @JsonCreator
    public Dish(
            @JsonProperty("name") String name,
            @JsonProperty("caloriePer100g") double caloriePer100g,
            @JsonProperty("calorieTotal") double calorieTotal,
            @JsonProperty("mass") double mass
    ) {
        this.name = name;
        if (caloriePer100g >= 0 && calorieTotal >= 0 && mass > 0) {
            this.caloriePer100g = caloriePer100g;
            this.calorieTotal = calorieTotal;
            this.mass = mass;
        } else throw new IllegalArgumentException("wrong arguments");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCaloriePer100g() {
        return caloriePer100g;
    }

    public void setCaloriePer100g(int caloriePer100g) {
        if (caloriePer100g >= 0) {
            this.caloriePer100g = caloriePer100g;
        } else throw new IllegalArgumentException("wrong argument");
    }

    public double getCalorieTotal() {
        return calorieTotal;
    }

    public void setCalorieTotal(int calorieTotal) {
        if (calorieTotal >= 0) {
            this.calorieTotal = calorieTotal;
        } else throw new IllegalArgumentException("wrong argument");
    }

    public double getMass() {
        return mass;
    }

    public void setMass(int mass) {
        if (mass > 0) {
            this.mass = mass;
        } else throw new IllegalArgumentException("wrong argument");
    }

    public static void writeDish(Dish dish, File file) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(dish);
        oos.close();
        fos.close();
    }

    public static Dish readDish(File file) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("path\\filename");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Dish dish = (Dish) ois.readObject();
        fis.close();
        ois.close();
        return dish;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return
                Objects.equals(this.name, dish.name)
                && Double.compare(this.caloriePer100g, dish.caloriePer100g) == 0
                && Double.compare(this.calorieTotal, dish.calorieTotal) == 0
                && Double.compare(this.mass, dish.mass) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.caloriePer100g, this.mass);
    }
}
