public class Student extends  User implements Comparable{
    private Parent mother;
    private Parent father;

    public Student(String firstName, String lastName) {
        super(firstName, lastName);
    }


    public void setMother(Parent mother) {
        this.mother = mother;
    }

    public void setFather(Parent father) {
        this.father = father;
    }

    public Parent getMother() {
        return mother;
    }

    public Parent getFather() {
        return father;
    }

    public boolean isParent(Observer parent) {
        if(mother == parent || father == parent) {
            return true;
        }
        return false;
    }

    @Override
    public int compareTo(Object o) {
        Student s =(Student) o;
        int rez = getFirstName().compareTo(s.getFirstName());
        if( rez == 0) {
            rez = getLastName().compareTo(s.getLastName());
        }
        return rez;
    }


}
