public class ParentingAgeException extends RuntimeException {

    public ParentingAgeException(Person parent, Person child, String reason) {
        super("Invalid parent to child relation: "
        + parent.getName() + " " + parent.getSurname() + " -> "
        + child.getName() + " " + child.getSurname() + ". Reason: " + reason);
    }
}
