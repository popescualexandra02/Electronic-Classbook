import java.util.Collection;

public class BestPartialScore implements Strategy{

    @Override
    public Student getBestStudent(Collection<Grade> grades) {
        Double grade = 0d;
        Student s = null;
        for(Grade g : grades) {
            if(g.getPartialScore() > grade) {
                grade = g.getPartialScore();
                s = g.getStudent();
            }
        }
        return s;
    }
}
