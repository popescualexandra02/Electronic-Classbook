import java.util.ArrayList;
public class PartialCourse extends Course {
    public PartialCourse(PartialCourseBuilder builder){
        super(builder);
    }

    @Override
    public ArrayList<Student> getGraduatedStudents() {
        ArrayList<Student> ret = new ArrayList<>();
        for( Grade g : grades) {
            if(g.getTotal() >= 5){
                ret.add(g.getStudent());
            }
        }
        return ret;
    }

    public static class PartialCourseBuilder extends Course.CourseBuilder {
        public PartialCourseBuilder(String name) {
            super(name);
        }

        public PartialCourse build() {
            return new PartialCourse(this);
        }
    }

}
