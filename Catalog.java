import java.util.ArrayList;

public class Catalog implements Subject {
    private static Catalog instance = null ;
    ArrayList <Course> courses ;
    ArrayList<Observer> observers;
    private Catalog() {
        courses = new ArrayList<>();
        observers = new ArrayList<>();
    }
    public static Catalog getInstance() {
        if(instance == null) {
            instance = new Catalog();
        }
        return instance;
    }
    // Adauga un curs în catalog ˘
    public void addCourse(Course course) {
        courses.add(course);
    }
    // Sterge un curs din catalog
    public void removeCourse(Course course) {
        courses.remove(course);
    }
    public ArrayList<Course> getCourses() {
        return courses;
    }

    public Course getCourse(String name) {
        Course result = null;
        for (Course c : courses) {
            if (c.getName().equals(name)) {
                result = c;
            }
        }
        return result;
    }
    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Grade grade) {
        for(Observer obs : observers) {
            if(grade.getStudent().isParent(obs))
                obs.update(new Notification(grade));
        }
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "courses=" + courses +
                ", observers=" + observers +
                '}';
    }
}
