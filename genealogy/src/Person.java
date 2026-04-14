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
        LocalDate deth = LocalDate.parse(elements[2], DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        Person created = new Person(name[0], name[1], birth, deth);
        return  created;
    }
}
