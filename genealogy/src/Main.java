import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Person> loaded = Person.fromCsv("family.csv");
        System.out.println("Wczytana lista:");
        for (Person p : loaded) {
            System.out.println(p);
        }

        Person.fromCsvLine("Marek Kowalski,15.05.1899,25.06.1857,,");
    }
}