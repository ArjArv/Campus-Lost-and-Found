package model;

public class Item {
    private static int counter = 1;
    private int id;
    private String name;
    private String description;
    private String location;
    private String owner;
    private String status; // Lost, Found, Claimed, Verified

    public Item(String name, String description, String location, String owner, String status) {
        this.id = counter++;
        this.name = name;
        this.description = description;
        this.location = location;
        this.owner = owner;
        this.status = status;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getLocation() { return location; }
    public String getOwner() { return owner; }
    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Item ID: " + id + ", Name: " + name + ", Owner: " + owner +
               ", Description: " + description + ", Location: " + location +
               ", Status: " + status;
    }
}
