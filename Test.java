import java.util.ArrayList;
import java.util.HashMap;

public class Test {
    public static void main(String[] args) {
        User studentA = UserFactory.getUser("Student","Ale","Popescu");
        User studentB = UserFactory.getUser("Student","Stefi","Vilcea");
        User studentC = UserFactory.getUser("Student","Elena","Ionita");
        User studentD = UserFactory.getUser("Student","Sebi","Lutan");


        User fatherA = UserFactory.getUser("Parent","C","Popescu");
        User motherA = UserFactory.getUser("Parent","M","Popescu");
        User fatherB = UserFactory.getUser("Parent","C","Pop");
        User motherB = UserFactory.getUser("Parent","M","Pop");

        User teacher = UserFactory.getUser("Teacher","Razvan","Diac");
        User assistant1 = UserFactory.getUser("Assistant","Ana","Ioana");
        User assistant2 = UserFactory.getUser("Assistant","Claudia","Ene");

        User teacher2 = UserFactory.getUser("Teacher","Carmen","Odubasteanu");
        User assistant3 = UserFactory.getUser("Assistant","Sarah","Pandel");

        Course course = new FullCourse.FullCourseBuilder("SO").titular((Teacher) teacher)
                .strategy(new BestTotalScore()).build();
        course.grades.add(new Grade("SO", (Student) studentA, 7d,3d));
        course.grades.add(new Grade("SO", (Student) studentB,3d,5d));

        Group g = new Group("B", (Assistant) assistant2);
        course.addGroup(g);

        course.addGroup("A", (Assistant) assistant1);
        course.addStudent("A", (Student) studentA);
        course.addStudent("A", (Student) studentB);
        course.addStudent("B", (Student) studentC);
        course.addStudent("B", (Student) studentD);

        course.addGrade(new Grade("SO", (Student) studentC,3d,3d));
        course.addGrade(new Grade("SO", (Student) studentD,3d,2d));

        course.addAssistant("A", (Assistant) assistant1);
        course.addAssistant("B", (Assistant) assistant2);

        Course course2 = new FullCourse.FullCourseBuilder("POO").titular((Teacher) teacher2)
                .strategy(new BestTotalScore()).build();
        course2.grades.add(new Grade("POO", (Student) studentA, 7d,3d));
        course2.addGroup("A", (Assistant) assistant1);
        course2.addStudent("A", (Student) studentA);
        course2.addAssistant("A", (Assistant) assistant1);

        Catalog catalog = Catalog.getInstance();
        catalog.addCourse(course);
        catalog.addCourse(course2);

        ((Student) studentA).setMother((Parent) motherA);
        ((Student) studentA).setFather((Parent) fatherA);
        ((Student) studentB).setMother((Parent) motherB);

        catalog.addObserver((Parent) motherA);
        catalog.addObserver((Parent) fatherA);
        catalog.notifyObservers(new Grade("POO", (Student)studentA,2.5d,3d));

        HashMap<Teacher, ArrayList<ScoreVisitor.Tuple<Student, String, Double>>> examScores=new HashMap<>();
        ArrayList<ScoreVisitor.Tuple<Student, String, Double>> ar1=new ArrayList<>();
        ar1.add(new ScoreVisitor.Tuple(studentA,"POO",3.6d));
        examScores.put((Teacher)teacher,ar1);

        HashMap<Assistant, ArrayList<ScoreVisitor.Tuple<Student, String, Double>>> partialScores= new HashMap<>();
        ArrayList<ScoreVisitor.Tuple<Student, String, Double>> ar2=new ArrayList<>();
        ar2.add(new ScoreVisitor.Tuple(studentC,"POO",4.6d));
        partialScores.put((Assistant)assistant1,ar2);

        Visitor v = new ScoreVisitor(examScores,partialScores);

        //v.visit((Teacher)teacher);
        System.out.println("Actualizare teacher:" + catalog);

        v.visit((Assistant)assistant1);
        System.out.println("Actualizare assistant:" + catalog);


        //System.out.println("Curs: "+ course2);
        //System.out.println("Best Student :" + course.getBestStudent());
        //Page_for_Teacher.open_page_teacher(assistant1);
        Page_for_Student.open_page_student((Student) studentA);
    }
}
