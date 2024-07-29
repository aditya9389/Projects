import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.net.*;

public class Server implements ActionListener {
    static DataOutputStream dout;
    JTextField text;
    JPanel a1;
    static Box vertical =Box.createVerticalBox();
    static JFrame f= new JFrame();
    Server(){
        f.setLayout(null);

        JPanel p1= new JPanel();
        p1.setBackground(new Color(7,94,84));
        p1.setBounds(0,0,450,70);
        p1.setLayout(null);
        f.add(p1);

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
        ImageIcon i4= new ImageIcon(ClassLoader.getSystemResource("icons/1.jpg"));
        Image i5 =i4.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT);
        i4= new ImageIcon(i5);
        JLabel pro = new JLabel(i4);
        pro.setBounds(40,10,50,50);
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
        phone.setBounds(350,20,35,30);
        p1.add(phone);

        ImageIcon i11= new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));
        Image i12 =i11.getImage().getScaledInstance(10,25,Image.SCALE_DEFAULT);
        i11= new ImageIcon(i12);
        JLabel  morevert= new JLabel(i11);
        morevert.setBounds(410,20,10,25);
        p1.add(morevert);

        JLabel name= new JLabel("Server");
        name.setBounds(110,15,100,25);
        name.setForeground(Color.WHITE);
        name.setFont(new Font("SAN_SERIF",Font.BOLD,14));
        p1.add(name);

        JLabel status= new JLabel("Active Now");
        status.setBounds(110,35,100,20);
        status.setForeground(Color.WHITE);
        status.setFont(new Font("SAN_SERIF",Font.BOLD,8));
        p1.add(status);

        a1= new JPanel();
        a1.setBounds(5,75,425,500);
        f.add(a1);

        text= new JTextField();
        text.setBounds(5,580,310,40);
        text.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
        f.add(text);

        JButton send = new JButton("send");
        send.setBounds(320,582,110,36);
        send.setBackground(new Color(7,94,84));
        send.addActionListener(this);
        send.setForeground(Color.WHITE);
        f.add(send);

        f.setSize(450,660);
        f.setLocation(200,50);
        f.getContentPane().setBackground(Color.white);

        f.setVisible(true);
    }
    public static void main(String[] args)
    {
        new Server();
        try{
            ServerSocket skt= new ServerSocket(6001);
            while(true)
            {
                Socket s= skt.accept();
                DataInputStream din = new DataInputStream(s.getInputStream());
                dout= new DataOutputStream(s.getOutputStream());

                while(true)
                {
                    String msg=din.readUTF();
                    JPanel panel = formatLabel(msg);
                    JPanel left= new JPanel(new BorderLayout());
                    left.add(panel,BorderLayout.LINE_START);
                    vertical.add(left);
                    f.validate();

                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae)
    {
        String out=text.getText();
        JPanel p2= formatLabel(out);
        a1.setLayout(new BorderLayout());
        JPanel right= new JPanel(new BorderLayout());
        right.add(p2,BorderLayout.LINE_END);
        vertical.add(right);
        vertical.add(Box.createVerticalStrut(15));
        a1.add(vertical,BorderLayout.PAGE_START);

        try {
            dout.writeUTF(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        text.setText("");

        f.repaint();
        f.invalidate();
        f.validate();
    }
    public static JPanel formatLabel(String out){
    JPanel panel= new JPanel();
    panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));

    JLabel output= new JLabel("<html><p style=\"width:150px\">" + out + "</p></html>");
    output.setFont(new Font("Tahoma",Font.PLAIN,16));
    output.setOpaque(true);
    output.setBorder(new EmptyBorder(15,15,15,50));
    output.setBackground(new Color(37,211,102));

        Calendar cal=Calendar.getInstance();
        SimpleDateFormat sdf= new SimpleDateFormat("HH:mm");

        JLabel time= new JLabel();
        time.setText(sdf.format(cal.getTime()));

        panel.add(output);
        panel.add(time);


    return panel;
    }

}
