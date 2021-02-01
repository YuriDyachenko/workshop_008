package kurs;

import java.util.ArrayList;

public class Countries {
    private final ArrayList<Country> list;

    public Countries() {
        list = new ArrayList<>();
        list.add(new Country("Russia", 17L));
        list.add(new Country("USA", 20L));
        list.add(new Country("Japan", 19L));
        list.add(new Country("Thailand", 19L));
    }

    public Country getByName(String name) {
        for (Country c : list)
            if (c.getName().equals(name))
                return c;
        return null;
    }
}
