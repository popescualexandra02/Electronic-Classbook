import java.util.ArrayList;
import java.util.HashMap;

public class ScoreVisitor implements Visitor{
    public HashMap<Teacher, ArrayList<Tuple<Student,String,Double>>> examScores;
    public HashMap<Assistant, ArrayList<Tuple<Student,String,Double>>> partialScores;

    public ScoreVisitor(HashMap<Teacher, ArrayList<Tuple<Student,String,Double>>> examScores,HashMap<Assistant,
            ArrayList<Tuple<Student,String,Double>>> partialScore) {
        this.examScores = examScores;
        this.partialScores = partialScore;
    }
    public HashMap<Teacher, ArrayList<Tuple<Student, String, Double>>> getExamScores() {
        return examScores;
    }

    public HashMap<Assistant, ArrayList<Tuple<Student, String, Double>>> getPartialScores() {
        return partialScores;
    }
    public void visit(Assistant assistant) {
        ArrayList<Course> courses = Catalog.getInstance().courses;
        ArrayList<Tuple<Student,String,Double>> all_grades = partialScores.get(assistant);
        for(Tuple<Student,String,Double> gradeList : all_grades) {
            for(Course course : courses ) {
                if(course.name.equals(gradeList.getValue1())) {
                    boolean ok = false;
                    for(Grade grade : course.grades) {
                        Student searched = gradeList.getKey();
                        if(searched.equals(gradeList.getKey())) {
                            grade.setPartialScore(gradeList.getValue2());
                            Catalog.getInstance().notifyObservers(grade);
                            ok = true;
                        }
                    }
                    if(!ok) {
                        Grade newPartial = new Grade(course.name,gradeList.getKey(),gradeList.getValue2(),0.0);
                        course.addGrade(newPartial);
                        Catalog.getInstance().notifyObservers(newPartial);
                    }

                }
            }
        }
    }

    @Override
    public void visit(Teacher teacher) {
        ArrayList<Course> courses = Catalog.getInstance().courses;
        ArrayList<Tuple<Student,String,Double>> all_grades = examScores.get(teacher);
        for(Tuple<Student,String,Double> gradeList : all_grades) {
            for(Course course : courses ) {
                if(course.name.equals(gradeList.getValue1())) {
                    boolean ok = false;
                    for(Grade grade : course.grades) {
                        Student searched = gradeList.getKey();
                        if(searched.equals(gradeList.getKey())) {
                            grade.setExamScore(gradeList.getValue2());
                            Catalog.getInstance().notifyObservers(grade);
                            ok = true;
                        }
                    }
                    if(!ok) {
                        Grade newExam = new Grade(course.name,gradeList.getKey(),gradeList.getValue2(),0.0);
                        course.addGrade(newExam);
                        Catalog.getInstance().notifyObservers(newExam);
                    }

                }
            }
        }
    }

    @Override
    public String toString() {
        return "ScoreVisitor{" +
                "examScores=" + examScores +
                "partialScores=" + partialScores +
                '}';
    }

    static class Tuple<K, V1, V2> {
        private K key;
        private V1 value1;
        private V2 value2;

        public Tuple(K key, V1 value1, V2 value2) {
            this.key = key;
            this.value1 = value1;
            this.value2 = value2;
        }

        public K getKey() {
            return key;
        }

        public V1 getValue1() {
            return value1;
        }

        public V2 getValue2() {
            return value2;
        }

        @Override
        public String toString() {
            return
                    "Student = " + key +
                            " curs = " + value1 +
                    " nota =" + value2 +
                    '\n';
        }
    }

}
