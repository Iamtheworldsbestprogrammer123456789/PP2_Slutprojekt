public class User {
    private String name;
    private String personnummer;

    public User(String name, String personnummer) {
        this.name = name;
        this.personnummer = personnummer;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPersonnummer(String personnummer) {
        this.personnummer = personnummer;
    }

    public String getName() {
        return this.name;
    }

    public String getPersonnummer() {
        return this.personnummer;
    }


}