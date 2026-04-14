import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Person> loaded = Person.fromCsv("family.csv");
        Person.toBinaryFile("osoby.data", loaded);
        List<Person> fromBinary = Person.fromBinaryFile("osoby.data");
        System.out.println("Wczytana lista:");
        if (fromBinary != null) {
            for (Person p : fromBinary) {
                System.out.println(p);
                System.out.println("dzieci: " + p.getChildren());
            }
        }
    }
}