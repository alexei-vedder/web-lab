package vedder.models;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.*;
import java.util.Objects;

@XmlType
@XmlRootElement(name = "dish")
@Entity
@Table(name = "dish", schema = "web_lab_schema", catalog = "web-lab")
@SqlResultSetMapping(
        name = "dish",
        entities = @EntityResult(entityClass = Dish.class)
)
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "Dish.getDishesByRationId",
                query = "SELECT * " +
                        "FROM web_lab_schema.dish " +
                        "WHERE name IN(" +
                        "SELECT dish_name " +
                        "FROM web_lab_schema.ration_dish " +
                        "WHERE ration_id = :ration_id" +
                        ")",
                resultSetMapping = "dish"
        ),

        @NamedNativeQuery(
                name = "Dish.insertNewDish",
                query = "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) " +
                        "VALUES (:name, :calorie_per_100g, :mass_in_g)"
        )
})
public class Dish implements Serializable {
    @Id
    @Column(name = "name")
    private String name;
    @Column(name = "calorie_per_100g")
    private double caloriePer100g;
    @Column(name = "mass_in_g")
    private double massInG;

    public Dish() {
    }

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
    @XmlElement(name = "dish-name")
    public String getName() {
        return name;
    }

    @JsonSetter("dish-name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonGetter("calorie-per-100g")
    @XmlElement(name = "calorie-per-100g")
    public double getCaloriePer100g() {
        return caloriePer100g;
    }

    @JsonSetter("calorie-per-100g")
    public void setCaloriePer100g(double caloriePer100g) {
        if (caloriePer100g >= 0) {
            this.caloriePer100g = caloriePer100g;
        } else throw new IllegalArgumentException("wrong argument");
    }

    @JsonGetter("mass-in-g")
    @XmlElement(name = "mass-in-g")
    public double getMassInG() {
        return massInG;
    }

    @JsonSetter("mass-in-g")
    public void setMassInG(double massInG) {
        if (massInG > 0) {
            this.massInG = massInG;
        } else throw new IllegalArgumentException("wrong argument");
    }

    @JsonGetter("calorie-total")
    @XmlElement(name = "calorie-total")
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
    public String toString() {
        return getName() + " " + getCaloriePer100g() + " " + getMassInG();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.caloriePer100g, this.massInG);
    }
}
