import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

class memberPage extends JFrame {
    private JLabel[] imageLabels; // อาเรย์สำหรับเก็บ JLabel ที่ใช้แสดงรูปภาพ

    MemBerPage() {
        setBounds(50, 10, 1450, 800); // กำหนดตำแหน่งและขนาดของ JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ปิดโปรแกรมเมื่อปิดหน้าต่าง
        setTitle("Member"); // ตั้งชื่อของหน้าต่าง

        // โหลดรูปภาพพื้นหลัง อ็อบเจ็กต์เก็บไฟล์ภาพ
        ImageIcon originalIcon = new ImageIcon("tem.jpg");
        // คำสั่งดึงภาพจาก ImageIcon
        Image scaledImage = originalIcon.getImage().getScaledInstance(getWidth(), getHeight(), // ปรับขนาดรูปภาพให้พอดีกับขนาดหน้าต่าง
                java.awt.Image.SCALE_SMOOTH);// SCALE_SMOOTH
                                             // เพื่อให้รูปภาพที่ขยายออกมาดูเรียบและมีคุณภาพดีหรือปรับรูปภาพให้ชัดขึ้น
        // สร้าง ImageIcon ใหม่จากรูปภาพที่ปรับขนาดแล้ว
        ImageIcon backgroundIMG = new ImageIcon(scaledImage);

        JLabel background = new JLabel(backgroundIMG); // สร้าง JLabel ที่มีภาพพื้นหลัง (backgroundIMG)
                                                       // เพื่อใช้เป็นพื้นหลังของหน้าต่าง.
        setContentPane(background); // ตั้งค่าพื้นหลังของหน้าต่าง (JFrame) ให้เป็น JLabel ที่เราสร้างไว้
                                    // (ซึ่งมีรูปภาพพื้นหลัง).
        background.setLayout(new BorderLayout()); // ตั้งค่าเลย์เอาต์เป็น BorderLayout

        background.add(createIMG(), BorderLayout.CENTER); // เพิ่ม JPanel แสดงรูปทั้ง3 กลาว
        // ที่มีรูปภาพลงในตำแหน่งกลาง
        background.add(createNameMember(), BorderLayout.SOUTH); // เพิ่ม JPanel แสดงกรอบข้อความชื่อสมาชิกลงในตำแหน่งล่าง
        background.add(createBack(), BorderLayout.NORTH); // เพิ่ม JPanel ที่มีปุ่ม แสดงปุ่มBackลงในตำแหน่งบน
    }

    private JPanel createIMG() {
        JPanel panel = new JPanel(new GridLayout(1, 3, 50, 50)); // 50 คือระยะห่างระหว่างพิเซล
        panel.setOpaque(false); // ตั้งค่าให้ JPanel ไม่มีพื้นหลัง

        // สร้างรูปภาพและปรับขนาด //คือการปรับรูปภาพให้ชัดขึ้น
        ImageIcon img1 = new ImageIcon(
                new ImageIcon("fiw.png").getImage().getScaledInstance(300, 510, java.awt.Image.SCALE_SMOOTH));
        ImageIcon img2 = new ImageIcon(
                new ImageIcon("po.png").getImage().getScaledInstance(240, 510, java.awt.Image.SCALE_SMOOTH));
        ImageIcon img3 = new ImageIcon(
                new ImageIcon("tangkwa.png").getImage().getScaledInstance(360, 510, java.awt.Image.SCALE_SMOOTH));
        // เพื่อให้รูปภาพที่ขยายออกมาดูเรียบและมีคุณภาพดีหรือปรับรูปภาพให้ชัดขึ้น
        // สร้าง JLabelเก็บรูปภาพทั้ง3
        imageLabels = new JLabel[] { new JLabel(img1), new JLabel(img2), new JLabel(img3) };

        // เพิ่ม JLabel ที่มีรูปภาพลงใน JPanel
        for (JLabel label : imageLabels) {
            panel.add(label);// สามารถแสดงผลรูปภาพที่เราเตรียมไว้ในหน้าต่างของโปรแกรมได้พร้อมกันในแนวที่กำหนดไว้
                             // (ในกรณีนี้คือแนวนอน)
        }
        return panel; // ส่งคืน JPanel ที่มีรูปภาพ
    }

    private JPanel createNameMember() {
        JPanel mainpanel = new JPanel(new GridLayout(1, 3, 150, 0)); // สร้าง JPanel ใช้ GridLayout แบบ 1 แถว 3 คอลัมน์
        mainpanel.setBorder(new EmptyBorder(0, 30, 70, 30)); // ไม่มีขอบเส้นหรือสีของข้อความ
        mainpanel.setOpaque(false); // ตั้งค่าให้ JPanel ไม่มีพื้นหลัง
        Font customFont = new Font("MV Boli", Font.BOLD, 20); // กำหนดฟอนต์สำหรับข้อความ

        // สร้าง JPanel และ JLabel สำหรับแต่ละสมาชิก
        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.PINK); // กำหนดสีพื้นหลัง
        JLabel fiw = new JLabel("Sathima kanlayasai 66011212141"); // ข้อความของสมาชิก
        fiw.setFont(customFont); // ตั้งค่าฟอนต์

        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.PINK); // กำหนดสีพื้นหลัง
        JLabel po = new JLabel("Wanuda Yeesarapat 66011212129"); // ข้อความของสมาชิก
        po.setFont(customFont); // ตั้งค่าฟอนต์

        JPanel panel3 = new JPanel();
        panel3.setBackground(Color.PINK); // กำหนดสีพื้นหลัง
        JLabel tang = new JLabel("Tullaya Duangmala 66011212021"); // ข้อความของสมาชิก
        tang.setFont(customFont); // ตั้งค่าฟอนต์

        // เพิ่ม JLabel ลงใน JPanel
        panel1.add(fiw);
        panel2.add(po);
        panel3.add(tang);

        // เพิ่ม JPanel ลงใน JPanel หลัก
        mainpanel.add(panel1);
        mainpanel.add(panel2);
        mainpanel.add(panel3);

        return mainpanel; // ส่งคืน JPanel ที่มีชื่อสมาชิก
    }

    private JPanel createBack() {
        JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // FlowLayout.LEFT คือการจัดเรียงส่วนประกอบ
                                                                        // ภายในmainPanel จากซ้ายไปขวา
        mainPanel.setOpaque(false); // ตั้งค่าให้ JPanel ไม่มีพื้นหลัง

        JButton bBack = new JButton("BACK"); // สร้างปุ่ม "Back"
        bBack.setPreferredSize(new java.awt.Dimension(100, 40)); // Dimension คือ กำหนดขนาดของปุ่ม
        bBack.setBackground(Color.PINK); // ตั้งค่าสีพื้นหลังของปุ่ม
        bBack.setForeground(Color.BLACK); // ตั้งค่าสีข้อความของปุ่ม
        bBack.setFont(new Font("Monospaced", Font.BOLD, 23)); // ตั้งค่าฟอนต์ของปุ่ม
        bBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuPage MenuPage = new menuPage();// เมื่อกดปุ่มBrackจะกลับไปหน้าเมนู
                MenuPage.setVisible(true);
                dispose();// เมธอดร่วมของJframe
            }
        });

        mainPanel.add(bBack); // เพิ่มปุ่มลงใน JPanel
        return mainPanel; // ส่งคืน JPanel ที่มีปุ่ม "Back"
    }
}
