import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Student_Page extends JFrame {
    static JTextField t;
    static JButton b;
    static JFrame f;
    static JFrame f2 = new JFrame("Second");;

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

        Catalog catalog = Catalog.getInstance();
        catalog.addCourse(course);
        //catalog.addCourse(course1);

        f = new JFrame("textfield");
        f.setLayout(null);

        t = new JTextField(16);
        t.setText(String.valueOf(studentA));
        t.setEditable(false);
        JPanel p = new JPanel();
        p.setBackground(Color.blue);
        p.setBounds(0,0,300,100);
        p.add(t);

        JPanel p1 = new JPanel();
        p1.setBackground(Color.GRAY);
        p1.setBounds(0,100,300,100);
        for(Course c : catalog.courses){
            for(Student a : c.getAllStudents()) {
                if(a == studentA) {
                    b = new JButton(c.name);
                    b.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            f2.setSize(400, 400);
                            f2.setVisible(true);
                            JPanel panel1, panel2, panel3, panel4;
                            panel1 = new JPanel();
                            panel2 = new JPanel();
                            panel3 = new JPanel();
                            panel4 = new JPanel();
                            JLabel l1,l2,l3,l4;
                            JTextField text1;
                            JTextField text2, text3, text4;

                            l1 = new JLabel("Profesor titular");
                            text1 = new JTextField(c.titular.toString());
                            text1.setEditable(false);
                            panel1.setBounds(0,0,400,100);
                            panel1.add(l1);
                            panel1.add(text1);
                            panel1.setBackground(Color.pink);

                            l2 = new JLabel("Asistenti");
                            text3 = new JTextField(c.assistants.toString());
                            panel2.setBounds(0,100,400,100);
                            panel2.add(l2);
                            panel2.add(text3);
                            panel2.setBackground(Color.CYAN);

                            l3 = new JLabel("Asistentul studentului");
                            //text4 = new JTextField()
                            panel3.setBounds(0,200,400,100);
                            panel3.add(l3);
                            //panel3.add(text4);
                            panel3.setBackground(Color.lightGray);

                            l4 = new JLabel("Note");
                            panel4.setBounds(0,300,100,100);
                            text2 = new JTextField(String.valueOf(c.getGrade(a)));
                            text2.setEditable(false);
                            panel4.add(l4);
                            panel4.add(text2);
                            panel4.setBackground(Color.orange);

                            f2.add(panel1);
                            f2.add(panel2);
                            f2.add(panel3);
                            f2.add(panel4);
                        }
                    });
                    p1.add(b);
                }
            }

        }


        f.add(p);
        f.add(p1);
        f.setSize(300, 300);

        f.show();
    }

}
