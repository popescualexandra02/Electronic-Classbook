import java.util.*;


public abstract class Course {
    String name;
    Teacher titular;
    HashSet<Assistant> assistants = new HashSet<Assistant>();
    ArrayList<Grade> grades = new ArrayList<>();
    HashMap<String,Group> groups = new HashMap<String,Group>();
    int nr_credits;
    Strategy strategy;
    private SnapShot snapShot;

    Course(CourseBuilder builder) {
        name = builder.name;
        titular = builder.titular;
        assistants = builder.assistants;
        grades = builder.grades;
        groups = builder.groups;
        nr_credits = builder.nr_credits;
        strategy = builder.strategy;
    }


    public void addAssistant(String ID, Assistant assistant){
        Group g = groups.get(ID);
        if(g.getAssistant() != null) {
            g.setAssistant(assistant);
        }
        if(assistants.contains(assistant))
            return;
        assistants.add(assistant);
    }


    public void addStudent(String ID, Student student){
        Group g = groups.get(ID);
        if(g.contains(student)) {
            return;
        }
        g.add(student);
    }

    public void addGroup(Group group){
        groups.put(group.ID,group);
    }

    public void addGroup(String ID, Assistant assistant){
        groups.put(ID,new Group(ID,assistant));
    }

    public void addGroup(String ID, Assistant assist, Comparator<Student> comp){
        groups.put(ID,new Group(ID,assist,comp));
    }

    public Grade getGrade(Student student) {
        for(Grade g: grades) {
            if(g.getStudent().compareTo(student) == 0)
                return g;
        }
        return null;
    }

    public void addGrade(Grade grade) {
        grades.add(grade);
    }

    public ArrayList<Student> getAllStudents() {
        ArrayList<Student> ret = new ArrayList<>();
        for(Group g : groups.values()) {
            Iterator value = g.iterator();
            while (value.hasNext()) {
                ret.add((Student) value.next());
            }
        }
        return ret;
    }

    public HashMap<Student, Grade> gettAllStudentGrades() {
        HashMap<Student,Grade> ret = new HashMap<>();
        for(Grade g : grades)
            ret.put(g.getStudent(),g);
        return null;
    }
    public Student getBestStudent() {
        return strategy.getBestStudent(grades);
    }
    public abstract ArrayList<Student> getGraduatedStudents();

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' + "\n" +
                "titular=" + titular + "\n" +
                "assistants=" + assistants + "\n" +
                "grades=" + grades + "\n" +
                "groups=" + groups + "\n" +
                "nr_credits=" + nr_credits + "\n" +
                "strategy=" + strategy +
                '}';
    }



    static abstract class CourseBuilder {
        String name;
        Teacher titular;
        HashSet<Assistant> assistants ;
        ArrayList<Grade> grades ;
        HashMap<String,Group> groups ;
        int nr_credits;
        Strategy strategy;
        public CourseBuilder(String name){
            assistants = new HashSet<>();
            grades = new ArrayList<>();
            groups = new HashMap<>();
            this.name = name;
        }
        public CourseBuilder name(String name) {
            this.name = name;
            return this;
        }
        public CourseBuilder titular(Teacher titular) {
            this.titular = titular;
            return this;
        }
        public CourseBuilder assistant(Assistant assistant1) {
            assistants.add(assistant1);
            return this;
        }
        public CourseBuilder grade(Grade grade) {
            grades.add(grade);
            return this;
        }
        public CourseBuilder group(String id, Group group){
            groups.put(id,group);
            return this;
        }
        public CourseBuilder nr_credits(int nr_credits) {
            this.nr_credits = nr_credits;
            return this;
        }
        public CourseBuilder strategy(Strategy strategy1){
            strategy = strategy1;
            return this;
        }
        public abstract Course build();

    }
    private class SnapShot{
        private ArrayList<Grade> grades_snapshot = new ArrayList<>();
        public ArrayList<Grade> getGrades_snapshot() {
            return grades_snapshot;
        }

        public void setGrades_snapshot(ArrayList<Grade> grades_snapshot) {
            this.grades_snapshot = grades_snapshot;
        }

        public SnapShot(ArrayList<Grade> grades) {
            ArrayList<Grade> grades_course = getGrades();
            for(Grade elem : grades_course ){
                grades_snapshot.add((Grade) elem.clone());
            }
        }
    }

    private ArrayList<Grade> getGrades() {
        return grades;
    }

    //Metoda makeBackup trebuie să stocheze notele într-o instanță de tip Snapshot
    public void makeBackup() {
        this.snapShot = new SnapShot(grades);
    }

    //metoda undo trebuie să înlocuiască notele existente într-un curs cu unele existente în instanța de tip Snapshot
    // (în cazul în care a fost realizat un backup anterior)
    public void undo() {
        this.grades = this.snapShot.getGrades_snapshot();
    }

}
