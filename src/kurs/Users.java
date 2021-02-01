package kurs;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Users {
    public static final int COND_COUNTRY = 1;
    public static final int COND_AGE = 2;
    public static final int COND_AGE_OLDER = 3;
    public static final int COND_AGE_UNDER = 4;
    public static final int COND_ADULT = 5;
    public static final int COND_TEEN = 6;
    public static final int COND_WRONG = 7;

    private static final String FILE_NAME = "users.json";
    private final Countries countries;
    private final ArrayList<User> list;

    Users() {
        //создаем базу стран, просто вбивая 4 страны в массив
        countries = new Countries();
        //создаем массив пользователей, пустой для начала
        list = new ArrayList<>();
        //считываем ЖСОН и по нему заполняем массив
        String text = readFile(FILE_NAME);
        try {
            JSONArray a = (JSONArray) new JSONParser().parse(text);
            for (Object value : a) {
                JSONObject o = (JSONObject) value;
                String countryName = (String) o.get("county");
                Country country = countries.getByName(countryName);
                Long id = (Long) o.get("id");
                String name = (String) o.get("name");
                String fName = (String) o.get("fname");
                Long age = (Long) o.get("age");
                Boolean teen = (Boolean) o.get("is_teen");
                list.add(new User(id, name, fName, country, age, teen));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public User[] usersByCondition(String parString, Long parLong, int condition) {
        ArrayList<User> a = new ArrayList<>();
        Country c = null;
        if (condition == COND_COUNTRY)
            c = countries.getByName(parString);
        for (User u : list) {
            boolean need = false;
            if (condition == COND_COUNTRY) need = u.getCountry() == c;
            if (condition == COND_AGE) need = u.getAge() == parLong;
            if (condition == COND_AGE_OLDER) need = u.getAge() > parLong;
            if (condition == COND_AGE_UNDER) need = u.getAge() <= parLong;
            if (condition == COND_ADULT) need = !u.getTeen();
            if (condition == COND_TEEN) need = u.getTeen();
            if (condition == COND_WRONG) need = u.getTeen() != u.getCountry().getTeen(u.getAge());
            if (!need) continue;
            a.add(u);
        }
        return a.toArray(new User[a.size()]);
    }

    private String readFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        FileReader fr;
        try {
            fr = new FileReader(fileName);
            Scanner sc = new Scanner(fr);
            while (sc.hasNextLine()) {
                stringBuilder.append(sc.nextLine());
                stringBuilder.append("\n");
            }
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
