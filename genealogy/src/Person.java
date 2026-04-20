import java.io.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Person implements Comparable<Person>, Serializable {
    private Set<Person> children;
    private String name;
    private String surname;
    private LocalDate date;
    private LocalDate death;

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

    public Person(String name, String surname, LocalDate date, LocalDate death){
        this.name = name;
        this.surname = surname;
        this.date = date;
        this.children = new HashSet<>();
        this.death = death;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", date=" + date +
                ", death=" + death +
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

    public static Person fromCsvLine(String line) {
        String[] elements = line.split(",", -1);
        String[] name = elements[0].split(" ", 2);
        LocalDate birth = LocalDate.parse(elements[1], DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        LocalDate death = null;
        if (!elements[2].isEmpty()) {
            death = LocalDate.parse(elements[2], DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            if (death.isBefore(birth)) {
                throw new NegativeLifespanException(birth, death);
            }
        }
        Person created = new Person(name[0], name[1], birth, death);
        return  created;
    }

    public static List<Person> fromCsv(String filePath) {
        List<Person> personList = new ArrayList<>();
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine(); // tutaj wczyta się linia nagłówka, możemy zignorować
            // reader.readLine() zwróci null gdy plik się skończy
            while ((line = reader.readLine()) != null) {
                //System.out.println("wczytana linia: " + line);
                Person parsed = fromCsvLine(line);
                for(Person existing : personList) {
                    if(existing.getName().equals(parsed.getName()) &&
                       existing.getSurname().equals(parsed.getSurname())) {
                        throw new AmbiguousPersonException(existing);
                    }
                }

                String[] elements = line.split(",", -1);
                String parent1 = elements[3];
                String parent2 = elements[4];
                for(Person p : personList) {
                    String fullName = p.name + " " + p.surname;
                    if(fullName.equals(parent1)) {
                        try {
                            validateParenting(p, parsed);
                        } catch (ParentingAgeException e) {
                            System.out.println(e.getMessage());
                            System.out.print("Do you want to keep this relation? (Y/N): ");

                            String decision = scanner.nextLine();
                            if (!decision.equalsIgnoreCase("Y")) {
                                continue;
                            }
                        }

                        p.adopt(parsed);
                    }

                    if(fullName.equals(parent2)) {
                        try {
                            validateParenting(p, parsed);
                        } catch (ParentingAgeException e) {
                            System.out.println(e.getMessage());
                            System.out.print("Do you want to keep this relation? (Y/N): ");

                            String decision = scanner.nextLine();
                            if (!decision.equalsIgnoreCase("Y")) {
                                continue;
                            }
                        }

                        p.adopt(parsed);
                    }
                }
                personList.add(parsed);
            }
        } catch (IOException e) {
            System.out.println("Błąd odczytu: " + e.getMessage());
        }
        return personList;
    }

    public static void toBinaryFile(String filePath, List<Person> toSave) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(toSave);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<Person> fromBinaryFile(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            List<Person> loaded = (List<Person>)ois.readObject();
            return loaded;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void validateParenting(Person parent, Person child)
        throws ParentingAgeException {
            if (parent == null || child == null) return;

            int age = Period.between(parent.date, child.date).getYears();

            if (age < 15) {
                throw new ParentingAgeException(parent, child, "parent is younger than 15 years (" + age + ")");
            }

            if (parent.death != null && parent.death.isBefore(child.date)) {
                throw new ParentingAgeException(parent, child, "parent wasn't living at the time of child's birth");
            }
        }

}
