package pl.pawel.StreamAPI;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        List<Employee> employees = new ArrayList<>();


        Employee employee1 = new Employee("Honkytonk", "Cumbbercooch", 25, List.of("Java", "JavaScript", "Python"));
        Employee employee2 = new Employee("Brewery", "Chickenstrips", 31, List.of("C#"));
        Employee employee3 = new Employee("Beetlejuice", "Snugglesnatch", 23, List.of("Java", "Haskell", "Scala"));
        Employee employee4 = new Employee("Boobytrap", "Humperdinck", 42, List.of("PHP", "JavaScript", "Reack", "Angular"));
        Employee employee5 = new Employee("Buttermilk", "Bumbersplat", 38, List.of("Java", "Scala"));
        Employee employee6 = new Employee("Rinkydink", "Curdlesnoot", 30, List.of("C", "C++"));
        Employee employee7 = new Employee("Cogglesnatch", "Splishnsplash", 27, List.of("Rust", "Python"));
        Employee employee8 = new Employee("Tiddleywomp", "Charmander", 51, List.of("Java", "Spring", "Hibernate", "Java"));

        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);
        employees.add(employee4);
        employees.add(employee5);
        employees.add(employee6);
        employees.add(employee7);
        employees.add(employee8);

        //Wypisanie wszystkich pozycji z listy employees
        employees.stream()
                .forEach(employee -> System.out.println(employee));

        System.out.println('\n');
        //Wypisanie wszystkich pozycji z listy employees - to samo co powyżej
        employees.stream()
                .forEach(System.out::println); // lambda zastąpiona referencją do metody ?

        System.out.println('\n');
       //Wypisanie tak jak chcemy imię nazwisko wiek - map
        employees.stream()
                .map(employee -> employee.getFirstName() + " " + employee.getLastName() + " " + employee.getAge())
                .forEach(System.out::println);

        System.out.println('\n');
        //wypisanie wiek
        employees.stream()
                .map(Employee::getAge)
                .forEach(System.out::println);

        System.out.println('\n');

        //wypisanie listy list skillsów
        List<List<String>> allSkills = employees.stream()
                .map(Employee::getSkills)
                .collect(Collectors.toList());
        System.out.println(allSkills);

        System.out.println('\n');
        // Flat map - stworzenie 1 listy z wielu list
        // distnict - usunięcie powtarzających się elementów
        List<String> allSkills2 = employees.stream()
                .map(Employee::getSkills)
                .flatMap(list -> list.stream())
                .distinct()
                .collect(Collectors.toList());
        System.out.println(allSkills2);

        System.out.println('\n');
        //filter imiona zaczynające się na B
        employees.stream()
                .filter(employee -> employee.getFirstName().startsWith("B"))
                .forEach(System.out::println);

        System.out.println('\n');
        //sorted sortowanie po nazwiskach
        employees.stream()
                .sorted(Comparator.comparing(Employee::getLastName))
                .forEach(System.out::println);

        System.out.println('\n');
        //sortujemy po nazwiskach i wypisujemy tylko 2 pozycje - limit
        employees.stream()
                .sorted(Comparator.comparing(Employee::getLastName))
                .limit(2)
                .forEach(System.out::println);

        System.out.println('\n');
        // sortujemy po nazwiskach i opuszczamy 2 pierwsze z listy
        employees.stream()
                .sorted(Comparator.comparing(Employee::getLastName))
                .skip(2)
                .forEach(System.out::println);

        System.out.println('\n');
        //filtujemy, które osoby mają imię na B i podajemy ile ich jest
        long numberOfEmployees = employees.stream()
                .filter(employee -> employee.getFirstName().startsWith("B"))
                .count();
        System.out.println(numberOfEmployees);

        System.out.println('\n');
        // max wiek możemy dać min również
        Employee oldestEmployee = employees.stream()
                .max(Comparator.comparing(Employee::getAge)).get();
        System.out.println(oldestEmployee);

        System.out.println('\n');
        // znalezienie kogos z listy kto ma imię na B
    Employee employee9 = employees.stream()
            .filter(employee -> employee.getFirstName().startsWith("B"))
            .findAny().get();

        System.out.println(employee9);

        System.out.println('\n');
        // Pierwsza osoba z listy z imieniem na B
        Employee employee10 = employees.stream()
                .filter(employee -> employee.getFirstName().startsWith("B"))
                .findFirst().get();

        System.out.println(employee10);

        System.out.println('\n');
        // wszystkie osoby, które mają imię na B
        boolean b = employees.stream()
                .allMatch(employee -> employee.getFirstName().startsWith("B"));

        System.out.println(b);

        System.out.println('\n');
        // czy jakaś osoba z listy ma imię na B
        boolean c = employees.stream()
                .anyMatch(employee -> employee.getFirstName().startsWith("B"));

        System.out.println(c);

        System.out.println('\n');
        // czy żadna osoba z listy nie ma imienia na B
        boolean d = employees.stream()
                .noneMatch(employee -> employee.getFirstName().startsWith("B"));

        System.out.println(d);


        System.out.println('\n');
        // reduce - zredukowanie wszystkich wartości streama do pojedyńczej wartości
        // sumowanie wieku wszystkich osob z listy. Na końcu musi być get() bo jest Optional. Bierzmy to get jeżeli chcemy otrzmyać wartosć ???
        //Musi być get bo moglibyś otrzymać stream pusty i nieotrzymalibyśmy zadnego wyniku
        //1 kontruktor reduce
        Integer sumOfAllAges = employees.stream()
                .map(Employee::getAge)
                //.reduce(Integer::sum)
                .reduce((age1, age2) -> age1 + age2)
                .get();

        System.out.println(sumOfAllAges);


        System.out.println('\n');


        //2 konstruktor reduce
        Integer sumOfAllAges2= employees.stream()
                .map(Employee::getAge)
                .reduce(0,Integer::sum);

        System.out.println(sumOfAllAges2);


        System.out.println('\n');
        //3 konstruktor reduce
        Integer sumOfAllAges3= employees.stream()
                .reduce(0,(age, employee)-> age + employee.getAge(), Integer::sum);

        System.out.println(sumOfAllAges3);

        System.out.println('\n');

        //Wstawienia przecinka pomiedzy imionami
            String allNames = employees.stream()
                    .map(Employee::getFirstName)
                    .reduce((name, name2) -> name + ", " + name2)
                    .get();

            System.out.println(allNames);
        System.out.println('\n');

        //wypisanie wszystkich poniżej 30 lat trzeba posortować, bo może rózne wyniki dawać "while"
        employees.stream()
                .sorted(Comparator.comparing(Employee::getAge)) // trzeba najpierw posortować bo takeWhile może dawać za każdym razem różne wyniki
                .takeWhile(employee -> employee.getAge() <30)
                .forEach(System.out::println);

        System.out.println('\n');
        //Wypisanie wszystkich powyżej 30 lat trzeba posortować jak takeWhile
        employees.stream()
                .sorted(Comparator.comparing(Employee::getAge))
                .dropWhile(employee -> employee.getAge() <30)
                .forEach(System.out::println);

        System.out.println('\n');

                String sentence = "Jak nauczyć się programowania?";
                // Wyświetlenie wszsytkich znaków zadania. Castowanie zmiennej typu int na char ??
                sentence.chars().forEach(s-> System.out.print((char) s));
                System.out.println('\n');
                //ze streamów możemy korzystać wielowątkowo. Dodając parrallel  stream wykonywany jest w wielu wątkach.
                // Potrzebne jest forEachOrdered bo zdanie podzielone zostanie na wątki i zostaną listery w dziwnej kolejności wyświetlone                sentence.chars().parallel().forEach(s-> System.out.print((char) s));
                System.out.println('\n');
                sentence.chars().parallel().forEachOrdered(s-> System.out.print((char) s));
                //Peek pozwala na modyfikowanie elemnetów oryginalnych kolekcji.
                //Ideą streamów jest to, że nie modyfukujemy elemnetów oryginalnej kolekcji. Wykonujemy wszysto na streamach.
                // peek do podglądania naszych streamów przy debuggingu raczej nie powinno się tego używać do modyfikowania kolekcji
        List<Employee> newEmployees = employees.stream()
                .peek(employee -> employee.setFirstName("Kamil"))
                .collect(Collectors.toList());

        System.out.println(newEmployees);
        System.out.println();
        System.out.println(employees);

        int result = Arrays.stream(new int[]{1, 2, 3}) // IntStream
                .map(x -> x * 2)
                .map(x -> x * 3)
                .sum(); // 12
        System.out.println(result);




    }




}





