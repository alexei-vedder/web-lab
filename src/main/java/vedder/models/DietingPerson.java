package vedder.models;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.*;

@XmlType
@XmlRootElement(name = "dieter")
@Entity
@Table(name = "dieting_person", schema = "web_lab_schema", catalog = "web-lab")
@NamedQuery(
        name = "DietingPerson.getUser",
        query = "FROM DietingPerson user WHERE user.login = :login AND user.password = :password"
)
public class DietingPerson {
    @Id
    @Column(name = "id")
    private UUID id;
    @Column(name = "name")
    private String name;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "dietingPerson")
    private List<Ration> rations;

    public DietingPerson() {
    }

    public DietingPerson(String login, String password, String name, UUID id) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.rations = new ArrayList<>();
    }

    public void addRation(Ration ration) {
        this.rations.add(ration);
    }

    public void deleteRation(Ration ration) {
        this.rations.remove(ration);
    }

    @XmlElement(name = "id")
    public UUID getId() {
        return id;
    }

    @XmlElement(name = "login")
    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    @XmlElementWrapper(nillable = true, name = "rations")
    @XmlElement(name = "ration")
    public List<Ration> getRations() {
        return this.rations;
    }

    public void setRations(List<Ration> rations) {
        this.rations = rations;
    }

    public int getRationsSize() {
        return this.rations.size();
    }

    @XmlElement(name = "name")
    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return getId() + " " + getLogin() + " " + getName() + " " + getRations();
    }

    @Override
    public boolean equals(Object obj) {
        DietingPerson person2 = (DietingPerson) obj;
        return this.getName().equals(person2.getName()) &&
                this.getId().toString().equals(person2.getId().toString()) &&
                this.getLogin().equals(person2.getLogin()) &&
                this.getPassword().equals(person2.getPassword());
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
