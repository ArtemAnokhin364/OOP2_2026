import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Person> loaded = Person.fromCsv("family.csv");
        System.out.println("Wczytana lista:");
        for (Person p : loaded) {
            System.out.println(p);
        }
    }
}