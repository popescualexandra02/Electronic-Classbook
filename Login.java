import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class Login extends JFrame {
    public static void login(ScoreVisitor v) {
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JPanel p = new JPanel();
        p.setBounds(0,0,300,300);

        JLabel intro_nume = new JLabel("Introdu prenumele");
        JTextField nume = new JTextField("Nume");
        nume.setBounds(0,0,100,100);


        JLabel intro_prenume = new JLabel(("Introdu numele"));
        JTextField prenume = new JTextField("Prenume");
        prenume.setBounds(100,100,100,100);

        JButton b1 = new JButton("SUBMIT");
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user_name = nume.getText();
                String user_prenume = prenume.getText();
                Student student_log = new Student(user_name,user_prenume);
                Teacher teacher_log = new Teacher(user_name,user_prenume);
                Assistant assistant_log = new Assistant(user_name,user_prenume);
                Parent parent_log = new Parent(user_name,user_prenume);

                Catalog catalog = Catalog.getInstance();
                For:
                for(Course c : catalog.courses) {
                    //log student
                    for(Student s : c.getAllStudents()){
                        if(s.getFirstName().equals(user_name) && s.getLastName().equals(user_prenume))
                            Page_for_Student.open_page_student(s);
                        // mother
                        if(s.getMother() != null && s.getMother().getFirstName().equals(user_name) &&
                                s.getMother().getLastName().equals(user_prenume)) {
                            Page_for_Parent.open_page_parent(s.getMother());
                            break For;
                        }
                        //father
                        if(s.getFather() != null && s.getFather().getFirstName().equals(user_name) &&
                                s. getFather().getLastName().equals(user_prenume)){
                            Page_for_Parent.open_page_parent(s.getFather());
                            break For;
                        }
                    }
                    //asistent
                    for(Assistant myAssistant : c.assistants){
                        if(myAssistant.getFirstName().equals(user_name) && myAssistant.getLastName().equals(user_prenume))
                            Page_for_Teacher.open_page_teacher(myAssistant,v);
                    }
                    //profesor
                    if(c.titular.getFirstName().equals(user_name) && c.titular.getLastName().equals(user_prenume)){
                        Page_for_Teacher.open_page_teacher( c.titular, v);
                    }
                }
                //log parent
            }
        });
        p.add(intro_nume);
        p.add(nume);
        p.add(intro_prenume);
        p.add(prenume);
        p.add(b1);

        frame.add(p);
        frame.setSize(500,300);
        frame.show();


    }
}
