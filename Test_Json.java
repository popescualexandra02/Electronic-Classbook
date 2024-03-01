import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Test_Json {
    public static void main(String[] args) throws IOException, ParseException {
        Object obj = new JSONParser().parse(new FileReader("src/catalog1.json"));
        JSONObject input = (JSONObject) obj;

        JSONArray courses = (JSONArray) input.get("courses");
        JSONArray examScores = (JSONArray) input.get("examScores");
        JSONArray partialScores = (JSONArray) input.get("partialScores");

        Catalog catalog = Catalog.getInstance();
        HashMap<Teacher, ArrayList<ScoreVisitor.Tuple<Student, String, Double>>> examScores_curs=new HashMap<>();


        HashMap<Assistant, ArrayList<ScoreVisitor.Tuple<Student, String, Double>>> partialScores_curs= new HashMap<>();
        ArrayList<ScoreVisitor.Tuple<Student, String, Double>> ar1;
        ArrayList<ScoreVisitor.Tuple<Student, String, Double>> ar2=new ArrayList<>();

        ScoreVisitor v = new ScoreVisitor(examScores_curs,partialScores_curs);
        Student student_test = null;
        Teacher teacher_test = null;
        Assistant assistant_test = null;


        //courses
        for(int i = 0; i < courses.size(); i++) {
            Course course_add = null;

            JSONObject course = (JSONObject) courses.get(i);
            String type = (String) course.get("type");
            String strategy = (String) course.get("strategy");
            String name = (String) course.get("name");

            JSONObject teacher = (JSONObject) course.get("teacher");
            String firstName = (String) teacher.get("firstName");
            String lastName = (String) teacher.get("lastName");


            //adauga nume cursuri in functie de type si strategy
            if( type.equals("FullCourse")) {
                if( strategy.equals("BestPartialScore"))
                    course_add = (FullCourse) new FullCourse.FullCourseBuilder(name).titular(new Teacher(firstName,lastName)).strategy(new
                            BestPartialScore()).build();
                if(strategy.equals("BestExamScore"))
                    course_add = (FullCourse) new FullCourse.FullCourseBuilder(name).titular(new Teacher(firstName,lastName)).strategy(new
                            BestExamScore()).build();
                if(strategy.equals("BestTotalScore"))
                    course_add = (FullCourse) new FullCourse.FullCourseBuilder(name).titular(new Teacher(firstName,lastName)).strategy(new
                            BestTotalScore()).build();

            }
            if( type.equals("PartialCourse")) {
                if( strategy.equals("BestPartialScore"))
                    course_add = (FullCourse) new FullCourse.FullCourseBuilder(name).titular(new Teacher(firstName,lastName)).strategy(new
                            BestPartialScore()).build();
                if(strategy.equals("BestExamScore"))
                    course_add = (FullCourse) new FullCourse.FullCourseBuilder(name).titular(new Teacher(firstName,lastName)).strategy(new
                            BestExamScore()).build();
                if(strategy.equals("BestTotalScore"))
                    course_add = (FullCourse) new FullCourse.FullCourseBuilder(name).titular(new Teacher(firstName,lastName)).strategy(new
                            BestTotalScore()).build();

            }

            ArrayList<Assistant>  assistants_array = new ArrayList<>();
            JSONArray assistants = (JSONArray) course.get("assistants");
            for(int j = 0 ; j < assistants.size(); j++) {
                JSONObject assistant = (JSONObject) assistants.get(j);
                String firstName_assistant = (String) assistant.get("firstName");
                String lastName_assistant = (String) assistant.get("lastName");
                assistants_array.add(new Assistant(firstName_assistant,lastName_assistant));
            }

            JSONArray groups = (JSONArray) course.get("groups");
            for(int z = 0; z < groups.size(); z++) {
                JSONObject group = (JSONObject) groups.get(z);
                String ID = (String) group.get("ID");

                JSONObject assistant_group = (JSONObject) group.get("assistant");
                String firstName_assistant_group = (String) assistant_group.get("firstName");
                String lastName_assistant_group = (String) assistant_group.get("lastName");

                Group g = new Group(ID,new Assistant(firstName_assistant_group,lastName_assistant_group));
                course_add.addGroup(g);
                course_add.addAssistant(ID,new Assistant(firstName_assistant_group,lastName_assistant_group));

                JSONArray students = (JSONArray) group.get("students");
                for(int k = 0; k < students.size(); k++) {
                    JSONObject student = (JSONObject) students.get(k);
                    String firstName_student = (String) student.get("firstName");
                    String lastName_student = (String) student.get("lastName");
                    Student s = new Student(firstName_student,lastName_student);
                    student_test = s;

                    JSONObject mother = (JSONObject) student.get("mother");
                    Parent mother_student = null;
                    Parent father_student = null;
                    if(mother != null) {
                        String firstName_mother = (String) mother.get("firstName");
                        String lastName_mother = (String) mother.get("lastName");
                        mother_student = new Parent(firstName_mother,lastName_mother);
                        s.setMother(mother_student);
                        catalog.addObserver(mother_student);

                    }

                    JSONObject father = (JSONObject) student.get("father");
                    if(father != null){
                        String firstName_father = (String) father.get("firstName");
                        String lastName_father = (String) father.get("lastName");
                        father_student = new Parent(firstName_father,lastName_father);
                        s.setFather(father_student);
                        catalog.addObserver(father_student);
                    }
                    course_add.addStudent(ID,s);
                    course_add.addGrade(new Grade(course_add.name,s));
                }

            }
            catalog.addCourse(course_add);
        }

        //examScores
        for(int i = 0; i < examScores.size(); i++) {
            JSONObject examScore = (JSONObject) examScores.get(i);

            JSONObject teacher = (JSONObject) examScore.get("teacher");
            String firstName_teacher = (String) teacher.get("firstName");
            String lastName_teacher = (String) teacher.get("lastName");
            Teacher t = new Teacher(firstName_teacher,lastName_teacher);


            JSONObject student = (JSONObject) examScore.get("student");
            String firstName_student = (String) student.get("firstName");
            String lastName_student = (String) student.get("lastName");
            Student s = new Student(firstName_student,lastName_student);

            String course = (String) examScore.get("course");
            Double grade = (Double) examScore.get("grade");


            for(Course c : catalog.courses) {
                if(c.name.equals(course)){
                    teacher_test = c.titular;
                    if(v.examScores.containsKey(c.titular)) {
                        ar1 = v.examScores.get(c.titular);
                        ar1.add(new ScoreVisitor.Tuple<>(s,c.name,grade));
                    }
                    else {
                        ar1 = new ArrayList<>();
                        ar1.add(new ScoreVisitor.Tuple<>(s,c.name,grade));
                    }
                    v.examScores.put(c.titular,ar1);
                }
            }
        }

        for(int i = 0; i < partialScores.size(); i++) {
            JSONObject partialScore = (JSONObject) partialScores.get(i);

            JSONObject assistant = (JSONObject) partialScore.get("assistant");
            String firstName_assistant = (String) assistant.get("firstName");
            String lastName_assistant = (String) assistant.get("lastName");
            Assistant a = new Assistant(firstName_assistant,lastName_assistant);
            assistant_test = a;

            JSONObject student = (JSONObject) partialScore.get("student");
            String firstName_student = (String) student.get("firstName");
            String lastName_student = (String) student.get("lastName");
            Student s = new Student(firstName_student,lastName_student);

            String course = (String) partialScore.get("course");
            Double grade = (Double) partialScore.get("grade");

            for(Course c : catalog.courses) {
                if(c.name.equals(course)){
                    for(Assistant A : c.assistants) {
                        if(A.compareTo(a) == 0) {

                            assistant_test = A;
                            if(v.partialScores.containsKey(A)) {
                                ar2 = v.partialScores.get(A);
                                ar2.add(new ScoreVisitor.Tuple<>(s,c.name,grade));
                            }
                            else {
                                ar2 = new ArrayList<>();
                                ar2.add(new ScoreVisitor.Tuple<>(s,c.name,grade));
                            }
                            v.partialScores.put(A,ar2);

                        }
                    }
                }
            }
        }
//        System.out.println(v.examScores);
        Page_for_Student.open_page_student(student_test);
        Page_for_Teacher.open_page_teacher(teacher_test,v);
        Page_for_Teacher.open_page_teacher(assistant_test,v);
        Page_for_Parent.open_page_parent(student_test.getFather());
        Login.login(v);
    }
}
