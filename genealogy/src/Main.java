import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Person created = Person.fromCsvLine(
                "Anna Dąbrowska,07.02.1930,22.12.1991,Ewa Kowalska,Marek Kowalski"
        );
        System.out.println(created);
    }
}