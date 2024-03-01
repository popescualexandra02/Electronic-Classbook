import java.util.Collection;

public class BestTotalScore implements Strategy{
    @Override
    public Student getBestStudent(Collection<Grade> grades) {
        Double grade = 0d;
        Student s = null;
        for(Grade g : grades) {
            if( g.getTotal() > grade) {
                grade = g.getTotal();
                s = g.getStudent();
            }
        }
        return s;
    }
}
