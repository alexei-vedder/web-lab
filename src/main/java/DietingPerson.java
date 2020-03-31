import java.util.*;

public class DietingPerson {
    private UUID id;
    private String name;
    private String login;
    private String password;
    private List<Ration> rations;

    DietingPerson(String login, String password, String name, UUID id) {
        this.id = id; // UUID.randomUUID();
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

    public UUID getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public List<Ration> getRations() {
        return this.rations;
    }

    public int getRationsTotal() {
        return this.rations.size();
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return getId() + " " + getLogin() + " " + getName() + " " + getRations();
    }
}
