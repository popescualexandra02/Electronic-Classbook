public class Teacher extends User implements Element,Comparable{
    public Teacher(String firstName, String lastName) {
        super(firstName, lastName);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public int compareTo(Object o) {
        User s =(User) o;
        int rez = getFirstName().compareTo(s.getFirstName());
        if( rez == 0) {
            rez = getLastName().compareTo(s.getLastName());
        }
        return rez;
    }
}
