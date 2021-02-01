package kurs;

/*
Сегодня будем имитировать работу с базой данных. Только вместо базы данных у нас будет файл с JSON.
Сам файл можно скачать отсюда - https://drive.google.com/file/d/1CbAYvkwlFdviNWoA8MPu-WrF42znhjV6/view
Это небольшая база с пользователями, которая содержит следующие колонки -
ID пользователя, имя, фамилия, возраст, текущая страна, и булевое значение —
считается ли пользователь совершеннолетним в этой стране.
Известно, что в разных странах совершеннолетие считается с разного возраста. В файле принимают
участие четыре страны — Россия (с 18 лет), Япония (с 20 лет), США (с 21 года) и Тайланд (с 20 лет).
Наша задача — написать класс, которые работает с этой базой. Он должен содержать следующие методы:
1) Получить всех пользователей из указанной страны. Страна приходит параметром.
2) Получить всех пользователей указанного возраста. Возраст приходит параметром.
3) Получить всех пользователей старше указанного возраста. Возраст приходит параметром.
4) Получить всех пользователей младше указанного возраста или равного ему. Возраст приходит параметром.
5) Получить всех совершеннолетних.
6) Получить всех тинов.
7) Найти все битые записи. Битые записи — это когда пользователь для текущей локации на самом деле должен быть
совершеннолетним или тином, а в базе поле is_teen проставлено неправильно.
Само собой, нужно спроектировать класс так, чтобы было как можно меньше дублирования в коде.
*/

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //создаем базу пользователей из json файла
        Users users = new Users();
        //выводим меню, запрашиваем, что делать
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("--------------------------------------------------------");
            System.out.println("1. Получить всех пользователей из указанной страны");
            System.out.println("2. Получить всех пользователей указанного возраста");
            System.out.println("3. Получить всех пользователей старше указанного возраста");
            System.out.println("4. Получить всех пользователей не старше указанного возраста");
            System.out.println("5. Получить всех совершеннолетних (по полю teen)");
            System.out.println("6. Получить всех тинов (по полю teen)");
            System.out.println("7. Найти все битые записи");
            System.out.print("Введите номер операции (0 - для выхода): ");
            int answer = scanner.nextInt();
            if (answer == 0) break;
            if (answer == 1) usersByCountryName(scanner, users);
            if (answer == 2) usersByAge(scanner, users);
            if (answer == 3) usersByAgeOlder(scanner, users);
            if (answer == 4) usersByAgeUnder(scanner, users);
            if (answer == 5) outUsers(users.usersByCondition(null, null, Users.COND_ADULT));
            if (answer == 6) outUsers(users.usersByCondition(null, null, Users.COND_TEEN));
            if (answer == 7) outUsers(users.usersByCondition(null, null, Users.COND_WRONG));
        }
        scanner.close();
    }

    private static void usersByCountryName(Scanner scanner, Users users) {
        System.out.print("Введите название страны: ");
        String countryName = scanner.next();
        outUsers(users.usersByCondition(countryName, null, Users.COND_COUNTRY));
    }

    private static void usersByAge(Scanner scanner, Users users) {
        Long age = enterAge(scanner);
        outUsers(users.usersByCondition(null, age, Users.COND_AGE));
    }

    private static void usersByAgeOlder(Scanner scanner, Users users) {
        Long age = enterAge(scanner);
        outUsers(users.usersByCondition(null, age, Users.COND_AGE_OLDER));
    }

    private static void usersByAgeUnder(Scanner scanner, Users users) {
        Long age = enterAge(scanner);
        outUsers(users.usersByCondition(null, age, Users.COND_AGE_UNDER));
    }

    private static Long enterAge(Scanner scanner) {
        System.out.print("Введите возраст: ");
        return scanner.nextLong();
    }

    private static void outUsers(User[] ua) {
        for (int i = 0; i < ua.length; i++) {
            System.out.println(ua[i].info());
        }
    }
}
