import java.util.*;

public class DietingPerson {
    private UUID id;
    private String name;
    private String login;
    private String password;
    private List<Ration> ration;

    DietingPerson(String login, String password, String name, UUID id) {
        this.id = id; // UUID.randomUUID();
        this.login = login;
        this.password = password;
        this.name = name;
        this.ration = new ArrayList<>();
    }

    public void addRation(Ration ration) {
        this.ration.add(ration);
    }

    public void deleteRation(Ration ration) {
        this.ration.remove(ration);
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
        return this.ration;
    }

    public int getRationsTotal() {
        return this.ration.size();
    }
}
