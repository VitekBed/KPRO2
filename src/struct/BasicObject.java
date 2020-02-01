package struct;

public class BasicObject {
    private int ID;
    private String Name;

    public BasicObject() {
    }

    public BasicObject(int ID, String name) {
        this.ID = ID;
        Name = name;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return Name;
    }
}
