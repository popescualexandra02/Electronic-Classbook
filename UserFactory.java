public class UserFactory {
    public static User getUser(String type, String firstname, String lastname) {
        if("Student".equalsIgnoreCase(type))
            return new Student(firstname,lastname);
        if("Parent".equalsIgnoreCase(type))
            return new Parent(firstname,lastname);
        if("Assistant".equalsIgnoreCase(type))
            return new Assistant(firstname,lastname);
        if("Teacher".equalsIgnoreCase(type))
            return new Teacher(firstname,lastname);
        return null;
    }
}