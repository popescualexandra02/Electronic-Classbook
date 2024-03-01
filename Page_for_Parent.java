import javax.swing.*;
import java.awt.*;

public class Page_for_Parent extends JFrame {
    public static void open_page_parent(Parent p) {
        Catalog catalog = Catalog.getInstance();
        JFrame frame = new JFrame("Page_Parent");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.GRAY);
        panel1.setBounds(0,0,300,50);

        JLabel nume_parinte = new JLabel("Numele parintelui :");
        JTextField nume = new JTextField();
        nume.setEditable(false);
        nume.setText(String.valueOf(p.getFirstName() + " " + p.getLastName()));

        JTextField notificare = new JTextField();
        notificare.setEditable(false);

        panel1.add(nume_parinte);
        panel1.add(nume);


        frame.add(panel1);
        frame.setSize(650,800);
        frame.show();




    }

}
