import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(
                "Adam",
                "Kowalski",
                LocalDate.of(2000, 3, 26)
        ));
        personList.add(new Person(
                "Alicja",
                "Kowalska",
                LocalDate.of(2010, 4, 16)
        ));
        personList.add(new Person(
                "Anna",
                "Kowalska",
                LocalDate.of(2015, 8, 9)
        ));

        for(Person p: personList){
            System.out.println(p);
        }
        personList.get(0).adopt(personList.get(2));
        personList.get(0).adopt(personList.get(1));

        Person youngest = personList.get(0).getYoungestChild();
        System.out.println("Najmłodze dziecko to: " + youngest);

        System.out.println("dzieci Adama: " + personList.get(0).getChildren());

        Family fam = new Family();
        fam.add(personList.get(0));
        fam.add(personList.get(1));
        fam.add(personList.get(2));

        System.out.println("'Adam Kowalski' w fam: " + fam.get("Adam Kowalski"));
        System.out.println("'Anna Kowalska' w fam: " + fam.get("Anna Kowalska"));
        System.out.println("'Nie Ma' w fam: " + fam.get("Nie Ma"));
    }
}