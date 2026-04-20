import java.util.List;

public class Main {
    public static void main(String[] args) {
        // TODO: zadanie 6 "Pliki, wyjątki"
        List<Person> loaded = Person.fromCsv("C:\\Users\\Admin\\IdeaProjects\\OOP2_2026\\genealogy\\src\\family.csv");

        System.out.println("People");
        for (Person p : loaded) {
            System.out.println(p);

            if (!p.getChildren().isEmpty()) {
                System.out.println("Dzieci:");
                for (Person child : p.getChildren()) {
                    System.out.println(" -> " + child);
                }
            }
        }
    }
}