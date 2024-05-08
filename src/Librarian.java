//En klass som representerar librarian. Ärver alla egenskaper som user har. Dock så har Librarian ett id istället för personnummer
public class Librarian extends User {

    //Constructor
    public Librarian(String name, String id) {
        super(name, id);
    }
}
