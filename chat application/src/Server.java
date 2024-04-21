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
        Image i5 =i4.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT);
        i4= new ImageIcon(i5);
        JLabel pro = new JLabel(i4);
        pro.setBounds(30,10,50,50);
        p1.add(pro);

        ImageIcon i7= new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
        Image i8 =i7.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
        i7= new ImageIcon(i8);
        JLabel video = new JLabel(i7);
        video.setBounds(300,20,30,30);
        p1.add(video);

        ImageIcon i9= new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));
        Image i10 =i9.getImage().getScaledInstance(35,30,Image.SCALE_DEFAULT);
        i9= new ImageIcon(i10);
        JLabel phone = new JLabel(i9);
        phone.setBounds(360,20,35,30);
        p1.add(phone);

        ImageIcon i11= new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));
        Image i12 =i11.getImage().getScaledInstance(10,25,Image.SCALE_DEFAULT);
        i11= new ImageIcon(i12);
        JLabel  morevert= new JLabel(i11);
        morevert.setBounds(410,20,10,25);
        p1.add(morevert);

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
