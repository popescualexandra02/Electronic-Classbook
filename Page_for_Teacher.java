import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Page_for_Teacher extends JFrame {
    public static void open_page_teacher(User a,ScoreVisitor v) {
        JFrame frame1 = new JFrame("Page_Teacher");
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setLayout(null);

        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.GRAY);
        panel1.setBounds(0,0,300,50);

        JTextField nume_teacher = new JTextField();

        nume_teacher.setText("Numele profesorului:");
        nume_teacher.setEditable(false);

        JTextField nume = new JTextField();
        nume.setText(String.valueOf(a));
        nume.setEditable(false);

        panel1.add(nume_teacher);
        panel1.add(nume);

        JPanel panel2 = new JPanel();
        panel2.setBounds(0,50,100,100);

        JPanel panel3 = new JPanel();
        panel3.setBounds(100,100,150,800);
        Catalog catalog = Catalog.getInstance();
        for(Course c : catalog.courses) {

            int ok = 0;
            for(Assistant assistant : c.assistants) {
                if(assistant.compareTo(a) == 0) {
                    ok = 1;
                }
            }
            if( c.titular.compareTo(a) == 0 ) {

                JTextField curs = new JTextField();
                curs.setText(c.name);
                curs.setEditable(false);
                panel2.add(curs);

//                JList note = new JList((ListModel) v.getExamScores());
//                panel3.add(note);
                //System.out.println(v.examScores.get(a));
                for(ScoreVisitor.Tuple tuple : v.examScores.get(a)){
                    JLabel note = new JLabel( tuple.getKey() +" " + tuple.getValue2() + "\n");
                    JButton button = new JButton("Validare");
                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            v.visit((Teacher) a);
                            Student student = (Student) tuple.getKey();
                            Parent p = student.getFather();
                            //Page_for_Parent.open_page_parent(p);

                        }
                    });

                    panel3.add(note);
                    panel3.add(button);

                }
            }

            if( ok == 1 ) {

                JTextField curs = new JTextField();
                curs.setText(c.name);
                curs.setEditable(false);
                panel2.add(curs);

//                JList note = new JList((ListModel) v.getExamScores());
//                panel3.add(note);
                //System.out.println(v.examScores.get(a));
                for(ScoreVisitor.Tuple tuple : v.partialScores.get(a)){
                    JLabel note = new JLabel( tuple.getKey() +": " + tuple.getValue2() + "\n");
                    JButton b = new JButton("Validare");
                    b.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            v.visit((Assistant) a);
                        }
                    });
                    panel3.add(note);
                    panel3.add(b);
                }
            }

        }
        frame1.add(panel3);
        frame1.add(panel2);
        frame1.add(panel1);
        frame1.setSize(650,800);
        frame1.show();


    }
}
