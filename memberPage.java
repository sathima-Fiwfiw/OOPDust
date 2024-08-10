import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

class memberPage extends JFrame {
    private Image originalImage;
    private JLabel[] imageLabels;
    private final Timer movementTimer;
    private int vibrationAmplitude = 5; // ความแรงของการสั่น
    private final int vibrationSpeed = 30; // ความเร็วในการสั่น (เพิ่มให้ช้าลง)

    @SuppressWarnings("removal")
    public memberPage() {
     // ขยายหน้าต่างให้เต็มจอ
        setBounds(10, 10, 900, 600);
        setTitle("Member");

        // สร้างพื้นหลัง
        originalImage = new ImageIcon("temP3.jpg").getImage();
        ImagePanel backgroundPanel = new ImagePanel(originalImage);

        // สร้างรูปภาพที่ต้องการซ้อนทับ
        String[] image = { "fiw.png", "po.png", "tangkwa.png" };
        imageLabels = new JLabel[image.length];
        int[][] bounds = { { 155, 100, 300, 510 }, { 405, 100, 260, 510 }, { 700, 100, 360, 510 } };
        String[] captions = { "Sathima kanlayasai 66011212141", "Wanuda Yeesarapat 66011212129",
                "Tullaya Duangmala 66011212021" };

        for (int i = 0; i < image.length; i++) {
            try {
                ImageIcon icon = new ImageIcon(image[i]);
                if (icon.getImage() == null) {
                    throw new RuntimeException("Cannot load image: " + image[i]);
                }
                Image adjustImage = icon.getImage().getScaledInstance(bounds[i][2], bounds[i][3], Image.SCALE_SMOOTH);
                imageLabels[i] = createLabel(adjustImage, bounds[i][2], bounds[i][3]);
                imageLabels[i].setBounds(bounds[i][0], bounds[i][1], bounds[i][2], bounds[i][3]);
                backgroundPanel.add(imageLabels[i]);

                // สร้าง JPanel สำหรับข้อความแนะนำตัว
                JPanel Panel = new JPanel();
                Panel.setBounds(bounds[i][0], bounds[i][1] + bounds[i][3], bounds[i][2], 40); // ตั้งค่าตำแหน่งและขนาด
                Panel.setBackground(new Color(200, 200, 200, 150)); // สีพื้นหลังโปร่งใส
                Panel.setLayout(new BorderLayout());

                // กำหนดสีของตัวหนังสือ
                JLabel infoLabel = new JLabel(captions[i]);
                infoLabel.setForeground(Color.BLACK);
                infoLabel.setHorizontalAlignment(JLabel.CENTER);

                // กำหนดฟอนต์ให้กับข้อความ
                Font customFont = new Font("Monospaced", Font.BOLD, 15); // ฟอนต์ที่เลือก: Monospaced, ตัวหนา, ขนาด 15
                infoLabel.setFont(customFont);

                Panel.add(infoLabel, BorderLayout.CENTER);

                // เพิ่ม JPanel ไปยัง backgroundPanel
                backgroundPanel.add(Panel);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // สร้างปุ่ม "Back"
        JButton button = new JButton("Back");
        button.setBounds(50, 300, 150, 50); // ตั้งค่าตำแหน่งและขนาดของปุ่ม
        button.setBackground(Color.PINK); // สีพื้นหลัง
        button.setForeground(Color.BLACK); // สีของข้อความบนปุ่ม
        button.setFont(new Font("Monospaced", Font.BOLD, 20));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuPage MenuPage = new menuPage();
                MenuPage.setVisible(true);
                dispose();
            }
        });
        backgroundPanel.add(button);

        setLayout(new BorderLayout());
        add(backgroundPanel, BorderLayout.CENTER);
        revalidate(); // ปรับปรุงการจัดการ layout
        repaint(); // แสดงผลการเปลี่ยนแปลง

        // สร้าง Timer สำหรับการเคลื่อนไหวแบบสั่น
        movementTimer = new Timer(vibrationSpeed, new ActionListener() {
            private int increase = 0;
            private boolean vibrate = true;

            @Override
            public void actionPerformed(ActionEvent e) {
                increase = (vibrate) ? increase + 1 : increase - 1;
                if (increase >= vibrationAmplitude || increase <= -vibrationAmplitude) {
                    vibrate = !vibrate;
                }

                for (JLabel label : imageLabels) {
                    moveImage(label, increase);
                }
            }
        });
        movementTimer.start();
    }

    private JLabel createLabel(Image image, int width, int height) {
        ImageIcon icon = new ImageIcon(image.getScaledInstance(width, height, Image.SCALE_SMOOTH));
        JLabel Jlabel = new JLabel(icon);
        Jlabel.setSize(width, height);
        return Jlabel;
    }

    private void moveImage(JLabel Jlabel, int offset) {
        int x = Jlabel.getLocation().x;
        int y = Jlabel.getLocation().y;
        Jlabel.setLocation(x + offset, y + offset);
    }
}

class ImagePanel extends JPanel {
    private Image image;

    public ImagePanel(Image image) {
        this.image = image;
        setLayout(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }
}
