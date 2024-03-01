import java.util.ArrayList;

public class FullCourse extends Course{

    public FullCourse(CourseBuilder builder) {

        super(builder);
    }

    @Override
    public ArrayList<Student> getGraduatedStudents() {
        ArrayList<Student> ret = new ArrayList<>();
        for(Grade g : grades) {
            if(g.getPartialScore() >= 3 && g.getExamScore() >= 2) {
                ret.add(g.getStudent());
            }
        }
        return ret;
    }

    public static class FullCourseBuilder extends Course.CourseBuilder {

        public FullCourseBuilder(String name) {
            super(name);
        }

        public FullCourse build() {
            return new FullCourse(this);
        }
    }

}
