package kurs;

public class User {
    private Long id;
    private String name;
    private String fName;
    private Country country;
    private Long age;
    private Boolean teen;

    public User(Long id, String name, String fName, Country country, Long age, Boolean teen) {
        this.id = id;
        this.name = name;
        this.fName = fName;
        this.country = country;
        this.age = age;
        this.teen = teen;
    }

    public Country getCountry() {
        return country;
    }

    public Long getAge() {
        return age;
    }

    public Boolean getTeen() {
        return teen;
    }

    public String info() {
        return String.format("%d: %s %s %s %d %b", id, name, fName, country.getName(), age, teen);
    }
}
