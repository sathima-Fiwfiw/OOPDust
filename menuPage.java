import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


class menuPage extends JFrame{

    menuPage() {
        setBounds(150, 40, 900, 650);
        Color colorDust = Color.decode("#d5bdaf");
        setTitle("MenuPage");

        // เป็น panel หลัก
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(colorDust);
        JPanel mainButton = createMainButton();
        mainPanel.add(mainButton, BorderLayout.CENTER);

        JPanel topic = createTopic();
        mainPanel.add(topic, BorderLayout.NORTH);

        add(mainPanel);
    }

    public JPanel createMainButton (){
        JPanel panel = new JPanel(new GridLayout(4,1,10,35));
        panel.setBorder(new EmptyBorder(100, 320, 70, 320));
        panel.setOpaque(false); 
        Color pastelBrown1 = Color.decode("#b6ad90");
        Color pastelBrown = Color.decode("#6c584c");

        JPanel panel_menu = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel_menu.setOpaque(false); // ทำให้ panel_menu โปร่งแสง
        JLabel menu = new JLabel(" MENU ");
        menu.setFont(new Font("Cooper Black", Font.BOLD, 40));
        menu.setForeground(pastelBrown); // เปลี่ยนสีฟอนต์ของข้อความเป็นสีแดง
        panel_menu.add(menu);
        
        JPanel panel_start = new JPanel(new BorderLayout());
        JButton start = new JButton("Start");
        start.setFont(new Font("Cooper Black", Font.BOLD, 30));
        start.setBackground(pastelBrown1);
        start.setForeground(pastelBrown);
        start.setBorderPainted(false); // ปิดการวาดขอบปุ่ม
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dustPage DustPage = new dustPage();
                DustPage.setVisible(true);
                dispose();
            }
        });
        panel_start.add(start,BorderLayout.CENTER);  

        JPanel panel_member = new JPanel(new BorderLayout());
        JButton member = new JButton("Member List");
        member.setFont(new Font("Cooper Black", Font.BOLD, 26)); 
        member.setBackground(pastelBrown1);
        member.setForeground(pastelBrown);
        member.setBorderPainted(false); 
        member.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                memberPage MemberPage = new memberPage();
                MemberPage.setVisible(true);
                dispose();
            }
        });
        panel_member.add(member,BorderLayout.CENTER);

        JPanel panel_exit = new JPanel(new BorderLayout());
        JButton B_exit = new JButton("Exit");
        B_exit.setFont(new Font("Cooper Black", Font.BOLD, 26)); 
        B_exit.setBackground(pastelBrown1);
        B_exit.setForeground(pastelBrown);
        B_exit.setBorderPainted(false); 
        B_exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);  //ใช้ในการปิดโปรแกรม
            }
        });
        panel_exit.add(B_exit,BorderLayout.CENTER);

        panel.add(panel_menu);
        panel.add(panel_start);
        panel.add(panel_member);
        panel.add(panel_exit);
        return panel;
    }

    public JPanel createTopic (){
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.setBorder(new EmptyBorder(30, 0, 10, 0));
        panel.setOpaque(false);
        JLabel pm = new JLabel(" DUST  PM 2.5 ");
        pm.setFont(new Font("Jokerman", Font.BOLD, 74)); 
        panel.add(pm);
        return panel;
    }

}