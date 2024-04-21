import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Server extends JFrame implements ActionListener {
    Server(){
        setLayout(null);

        JPanel p1= new JPanel();
        p1.setBackground(new Color(7,94,84));
        p1.setBounds(0,0,450,70);
        p1.setLayout(null);
        add(p1);

        ImageIcon i1= new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));
        Image i2 =i1.getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT);
        i1= new ImageIcon(i2);
        JLabel back = new JLabel(i1);
        back.setBounds(5,20,25,25);
        p1.add(back);
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent ae) {
                System.exit(0);
            }
        });
        ImageIcon i4= new ImageIcon(ClassLoader.getSystemResource("icons/1.png"));
        Image i5 =i4.getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT);
        i4= new ImageIcon(i5);
        JLabel pro = new JLabel(i4);
        pro.setBounds(30,10,50,50);
        p1.add(pro);

        setSize(450,700);
        setLocation(200,50);
        getContentPane().setBackground(Color.green);

        setVisible(true);
    }
    public static void main(String[] args)
    {
        new Server();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

    }
}
