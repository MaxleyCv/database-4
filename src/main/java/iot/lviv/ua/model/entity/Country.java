package iot.lviv.ua.model.entity;

public class Country {
    private Integer id;
    private String name;
    private String president;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Country(Integer id, String name, String president) {
        this.id = id;
        this.name = name;
        this.president = president;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPresident() {
        return president;
    }

    public void setPresident(String president) {
        this.president = president;
    }
}
