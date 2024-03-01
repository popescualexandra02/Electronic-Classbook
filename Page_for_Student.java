import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;

public class Page_for_Student extends JFrame {
    public static void open_page_student( Student a){
        JFrame frame1 = new JFrame("Page_Student");
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setLayout(null);

        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.GRAY);
        panel1.setBounds(0,0,300,50);

        JTextField nume_stud = new JTextField();
        nume_stud.setText("Numele studentului:");
        nume_stud.setEditable(false);

        JTextField nume = new JTextField();
        nume.setText(String.valueOf(a));
        nume.setEditable(false);

        panel1.add(nume_stud);
        panel1.add(nume);

        Catalog catalog = Catalog.getInstance();

        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.black);
        panel2.setBounds(0,50,300,300);
        JButton b ;
        for(Course c : catalog.courses) {
            String name = c.name;

            for(Student this_stud : c.getAllStudents()) {
                if(this_stud.compareTo(a) == 0) {
                    b = new JButton(name);
                    b.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            JFrame frame2 = new JFrame("Curs_Student");
                            frame2.setSize(650,650);
                            frame2.setVisible(true);

                            JPanel panel3 = new JPanel();
                            panel3.setLayout(new BoxLayout(panel3, BoxLayout.PAGE_AXIS));
                            JLabel label1 = new JLabel("Profesor titular: ");
                            JTextField text2 = new JTextField();
                            text2.setText(c.titular.toString());
                            text2.setEditable(false);
                            panel3.setBounds(10,10,300,200);
                            panel3.add(label1);
                            panel3.add(text2);

                            JLabel label2 = new JLabel("Asistenti: ");
                            JTextField text3 = new JTextField();
                            text3.setText(c.assistants.toString());
                            text3.setEditable(false);
                            panel3.add(label2);
                            panel3.add(text3);
                            panel3.setBackground(Color.PINK);

                            JPanel panel5 = new JPanel();
                            JLabel label3 = new JLabel("Asistentul studentului: ");
                            JTextField text4 = new JTextField();
                            for(Group g  : c.groups.values()) {
                                for(Student s : g){
                                    if(s.compareTo(a) == 0){
                                        text4.setText(g.assistant.toString());
                                    }
                                }

                            }
                            text4.setEditable(false);
                            panel3.add(label3);
                            panel3.add(text4);

                            JLabel label4 = new JLabel("Note: ");
                            JTextField text5 = new JTextField();
                            text5.setEditable(false);
                            text5.setText(c.getGrade(a).toString());
                            panel3.add(label4);
                            panel3.add(text5);

                            frame2.add(panel3);
                        }
                    });
                    panel2.add(b);
                }
            }
        }


        frame1.add(panel1);
        frame1.add(panel2);
        frame1.setSize(300,300);
        frame1.show();


    }
}
