
import java.util.ArrayList;
import java.util.Comparator ;
import java.util.HashSet;
import java.util.TreeSet;

public class Group extends TreeSet<Student> {
    Assistant assistant;
    String ID;


    public Group(String ID, Assistant assistant, Comparator<Student> comp) {
        super(comp);
        this.ID = ID;
        this.assistant = assistant;
    }
    public Group(String ID, Assistant assistant) {
        this.ID = ID;
        this.assistant = assistant;

    }

    public Assistant getAssistant(){
        return assistant;
    }
    public String getID(){
        return ID;
    }
    public void setAssistant(Assistant a){
        assistant = a;

    }
    public void setID(String ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Student element : this) {
            sb.append(element.toString());
            sb.append(" ");
        }
        return "{" +
                "assistant=" + assistant  +" " + ", students " + sb + " "
                + '}';
    }
}
