import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Person implements Comparable<Person> {
    private Set<Person> children;
    private String name;
    private String surname;
    private LocalDate date;
    private LocalDate deth;

    public List<Person> getChildren(){
//        List<Person> resultList = new ArrayList<>();
//        for(Person p : children){
//            resultList.add(p);
//        }
//        resultList.sort(Person::compareTo);
//        return resultList;
        return children.stream().sorted().toList();
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Person(String name, String surname, LocalDate date, LocalDate deth){
        this.name = name;
        this.surname = surname;
        this.date = date;
        this.children = new HashSet<>();
        this.deth = deth;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", date=" + date +
                ", deth=" + deth +
                '}';
    }

    public boolean adopt(Person child) {
        boolean success = this.children.add(child);
        return success;
    }

    public Person getYoungestChild() {
        Person result = null;
        for(Person p : this.children) {
            if (result == null || result.compareTo(p) < 0) {
                result = p;
            }
        }
        return result;
    }

    @Override
    public int compareTo(Person other) {
        if (this.date.getYear() == other.date.getYear()) {
            return this.date.getDayOfYear() - other.date.getDayOfYear();
        }
        return this.date.getYear() - other.date.getYear();
    }

    public static Person fromCsvLine(String line){
        String[] elements = line.split(",", -1);
        String[] name = elements[0].split(" ", 2);
        LocalDate birth = LocalDate.parse(elements[1], DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        LocalDate deth = null;
        if (!elements[2].isEmpty()) {
            deth = LocalDate.parse(elements[2], DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        }
        Person created = new Person(name[0], name[1], birth, deth);
        return  created;
    }

    public static List<Person> fromCsv(String filePath) {
        List<Person> personList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine(); // tutaj wczyta się linia nagłówka, możemy zignorować
            // reader.readLine() zwróci null gdy plik się skończy
            while ((line = reader.readLine()) != null) {
                //System.out.println("wczytana linia: " + line);
                Person parsed = fromCsvLine(line);
                personList.add(parsed);
            }
        } catch (IOException e) {
            System.out.println("Błąd odczytu: " + e.getMessage());
        }
        return personList;
    }

}
