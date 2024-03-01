import java.util.Collection;

public class BestExamScore implements Strategy{
    @Override
    public Student getBestStudent(Collection<Grade> grades) {
        Double grade = 0d;
        Student s = null;
        for(Grade g : grades) {
            if( g.getExamScore() > grade) {
                grade = g.getExamScore();
                s = g.getStudent();
            }
        }
        return s;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
