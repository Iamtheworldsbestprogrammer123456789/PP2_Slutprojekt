import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private String id;

    public User(String name, String personnummer) {
        this.name = name;
        this.id = personnummer;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public String getId() {
        return this.id;
    }


}