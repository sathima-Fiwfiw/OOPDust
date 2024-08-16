
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class dustPage extends JFrame {
    String inputTextField, populText;
    private List<Integer> dustData = new ArrayList<>();
    private JTextField text_Rpopulation;
    JLabel population, populationRandom, dust_Quantity, populationGood, populationSick, perSick;
    int minPopulation; // ค่าต่ำสุดของประชากร
    int maxPopulation; // ค่าสูงสุดของประชากร
    int row = 10, col = 20;
    dataBtn[][] dBtn = new dataBtn[row][col];

    dustPage() {
        setBounds(50, 10, 1450, 800);
        setLayout(new BorderLayout(10, 0));
        setTitle("DustPage");

        // เอาแม่สีมาสร้างสี
        Color pastelBlue = new Color(173, 216, 230);
        Color pastelPink = new Color(255, 182, 193);

        // MainPanel หลักซ้าย
        JPanel mainPanel_1 = new JPanel(new BorderLayout(10, 10));
        mainPanel_1.setBackground(pastelBlue);
        mainPanel_1.setBorder(new EmptyBorder(3, 0, 0, 10));

        // สร้าง panel ของช่องข้อความที่มีคนป่วย,คนสุภาพดี...
        JPanel MessagePanel = new JPanel();
        MessagePanel.setBackground(pastelPink);
        JPanel Message = createMessage();
        MessagePanel.add(Message);
        mainPanel_1.add(MessagePanel, BorderLayout.CENTER);

        // สร้าง panel ของปุ่มฝน
        JPanel rainPanel = new JPanel(new GridLayout(1, 2, 15, 15));
        JPanel rainButton = createRain();
        rainPanel.add(rainButton);
        mainPanel_1.add(rainPanel, BorderLayout.SOUTH);

        add(mainPanel_1, BorderLayout.WEST); // เพิ่มลงเฟรมหลัก

        // MainPanel หลักขวา
        JPanel mainPanel_2 = new JPanel(new BorderLayout(10, 10));
        mainPanel_2.setBackground(pastelBlue);
        mainPanel_2.setBorder(new EmptyBorder(3, 30, 0, 0)); // EmptyBorder กำหนดระยะห่างของ Panel

        // สร้าง panel ของกรอบที่บอกจำนวนคนป่วย
        JPanel colorPanel = new JPanel(new GridLayout(1, 4, 25, 0)); // ใช้ GridLayout เพื่อสร้างกรอบแต่ละสี
        colorPanel.setBorder(new EmptyBorder(10, 50, 10, 50));
        colorPanel.setBackground(pastelPink);

        JPanel redPanel = createColor(Color.RED, "มีจำนวนคนป่วย เกิน 30%");
        JPanel orangePanel = createColor(Color.ORANGE, "มีจำนวนคนป่วย 20%-29%");
        JPanel yellowPanel = createColor(Color.YELLOW, "มีจำนวนคนป่วย 10%-19%");
        JPanel greenPanel = createColor(Color.GREEN, "มีจำนวนคนป่วย 0%-9%");

        colorPanel.add(redPanel);
        colorPanel.add(orangePanel);
        colorPanel.add(yellowPanel);
        colorPanel.add(greenPanel);
        mainPanel_2.add(colorPanel, BorderLayout.NORTH);

        // สร้าง panel ของปุ่ม200ปุ่ม
        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setBorder(new EmptyBorder(10, 0, 10, 25));
        buttonPanel.setBackground(pastelPink);
        buttonPanel.add(createButton(), BorderLayout.CENTER);
        mainPanel_2.add(buttonPanel, BorderLayout.CENTER);

        // สร้าง panel เก็บที่อ่านไฟล์และปุ่มย้อนกลับ
        JPanel fANDbPanel = new JPanel(new BorderLayout());
        fANDbPanel.setBorder(new EmptyBorder(10, 0, 10, 30));
        fANDbPanel.setBackground(pastelPink);
        fANDbPanel.add(createFile(), BorderLayout.WEST);
        fANDbPanel.add(createBack(), BorderLayout.EAST);
        fANDbPanel.add(createReadpopulation(), BorderLayout.CENTER);
        mainPanel_2.add(fANDbPanel, BorderLayout.SOUTH);

        add(mainPanel_2, BorderLayout.EAST); // เพิ่มลงเฟรมหลัก

    }
    /*----------------------------------------------------------------------------------------------------------------------------------- */

    // เมธอดสำหรับสร้างกรอบสี
    private JPanel createColor(Color color, String text) {
        JPanel panel = new JPanel(); // สร้าง JPanel ใหม่
        panel.setBackground(color); // กำหนดสีพื้นหลังของ JPanel ตามที่ส่งเข้ามาในฟังก์ชัน
        panel.setBorder(new LineBorder(Color.BLACK, 2)); // กำหนดเส้นขอบสีดำ
        JLabel label = new JLabel(text, JLabel.CENTER); // กำหนดข้อความให้อยู่ตรงกลาง
        label.setFont(new Font("Tahoma", Font.PLAIN, 14)); // กำหนดฟอนต์ที่รองรับภาษาไทย มีขนาด14 เป็นฟอนต์แบบธรรมดา
        panel.add(label);
        return panel;
    }

    // เมธอดสร้าง 200 ปุ่ม
    private JPanel createButton() {
        JPanel panel = new JPanel(new GridLayout(row, col, 2, 2));
        panel.setBorder(new EmptyBorder(10, 40, 20, 6));
        Color pastelPink = new Color(255, 182, 193);
        panel.setBackground(pastelPink);

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                dBtn[i][j] = new dataBtn();

                // เพิ่ม ActionListener ให้กับปุ่ม
                dBtn[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        handleButtonClick(e); // เรียกใช้งานเมธอดจัดการเหตุการณ์
                    }
                });

                panel.add(dBtn[i][j]);

            }
        }
        return panel;
    }

    // เมธอด อ่านไฟล์
    private JPanel createFile() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 10, 10));
        panel.setBorder(new EmptyBorder(0, 49, 0, 0)); // เพิ่มระยะห่าง
        Color pastelPink = new Color(255, 182, 193);
        panel.setBackground(pastelPink);
        JButton readfile = new JButton("ReadFile");
        JTextField textField_read = new JTextField();

        readfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputTextField = textField_read.getText(); // ดึงเอาชื่อไฟล์จากที่ป้อนเข้ามา
                dataDust(inputTextField); // เอาไปทำในเมธอดdataDust
            }
        });

        panel.add(readfile);
        panel.add(textField_read);
        return panel;
    }

    // เก็บข้อมูลฝุ่น
    private void dataDust(String textfile) {
        List<String> lines = new ArrayList<>();
        // ใช้ BufferedReader เพื่ออ่านไฟล์ที่ระบุด้วย textfile ที่รับเข้ามา
        try (BufferedReader br = new BufferedReader(new FileReader(textfile))) {
            String line;
            while ((line = br.readLine()) != null) {
                // อ่านไฟล์บรรทัดต่อบรรทัดโดยใช้ readLine() เป็นเมธอด BufferedReader
                lines.add(line); // ถ้าอ่านบรรทัดได้ (ไม่ใช่ null), จะเพิ่มบรรทัดนั้นเข้าไปในลิสต์ lines
            }
        } catch (IOException e) {
            System.out.println("ไม่มีไฟล์");
            return;
        }

        // ใช้ลูป for เพื่อวนรอบแต่ละบรรทัดในลิสต์ lines
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i); // ดึงข้อมูลบรรทัดที่อยู่ที่ตำแหน่ง i จากลิสต์ lines และเก็บไว้ในตัวแปร line
            String[] numbers = line.trim().split("\\s+");
            // trim() ใช้ในการลบช่องว่างซ้ายสุดกับขวาสุด split()
            // ใช้ในการแยกสตริงออกเป็นอาร์เรย์
            // \\s+
            // จะทำการแยกข้อความตามช่องว่างที่มีหนึ่งตัวหรือมากกว่านั้นและจะไม่สร้างช่องว่างเปล่าในอาร์เรย์
            for (int j = 0; j < numbers.length; j++) {
                String numStr = numbers[j];

                try {
                    int num = Integer.parseInt(numStr);
                    dustData.add(num);
                    System.out.print(num + " ");
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number: " + numStr);
                }
            }
        }
        updateButtonColors();
    }

    // เมธอด set สีให้ปุ่ม
    private void updateButtonColors() {
        int buttonIndex = 0;
        for (int i = 0; i < dustData.size(); i++) {
            int num = dustData.get(i);

            if (buttonIndex >= row * col)
                break; // ถ้าไม่มีปุ่มเหลือให้ปรับสี ให้หยุดการวนลูป

            dataBtn button = dBtn[buttonIndex / col][buttonIndex % col]; // เข้าถึงปุ่มผ่านดัชนี
            if (num <= 50) {
                button.setBackground(Color.GREEN);
            } else if (num <= 100) {
                button.setBackground(Color.YELLOW);
            } else if (num <= 150) {
                button.setBackground(Color.ORANGE);
            } else if (num <= 250) {
                button.setBackground(Color.RED);
            } else {
                button.setBackground(Color.GRAY);
            }
            buttonIndex++;
        }
    }

    // เมธอดปุ่มฝน
    private JPanel createRain() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 50, 50));
        panel.setBorder(new EmptyBorder(25, 40, 50, 20)); // เพิ่มระยะห่าง
        Color pastelPink = new Color(255, 182, 193);
        panel.setBackground(pastelPink);

        JButton rain = new JButton("RAIN");
        JButton royalRain = new JButton("ROYAL  RAIN");
        rain.setPreferredSize(new Dimension(150, 55));
        Color buleColor = Color.decode("#69E5E1");
        rain.setBackground(buleColor);
        rain.setFont(new Font("Cooper Black", Font.BOLD, 26));
        rain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < dustData.size(); i++) {
                    dustData.set(i, (int) (dustData.get(i) - 50));
                }
                updateButtonColors();
            }
        });

        royalRain.setPreferredSize(new Dimension(150, 55));
        Color buleColor1 = Color.decode("#6AB7B5");
        royalRain.setBackground(buleColor1);
        royalRain.setFont(new Font("Cooper Black", Font.BOLD, 20));
        royalRain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addRoyalRainAction();
            }
        });
        panel.add(rain);
        panel.add(royalRain);
        return panel;
    }

    // เมธอด เมื่อกดปุ่มหลวงแล้วจะทำงานเงื่อนไข
    private void addRoyalRainAction() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                dataBtn button = dBtn[i][j];
                int index = i * col + j;

                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // ลดฝุ่น 50% สำหรับปุ่มที่ถูกกด
                        int currentDust = dustData.get(index);
                        dustData.set(index, (int) (currentDust * 0.5));

                        // ลดฝุ่น 30% สำหรับปุ่มที่อยู่รอบข้าง
                        int[] adjacentIndices = {
                                index - col - 1, index - col, index - col + 1,
                                index - 1, index + 1,
                                index + col - 1, index + col, index + col + 1
                        };

                        for (int i = 0; i < adjacentIndices.length; i++) {
                            int adjacentIndex = adjacentIndices[i];
                            if (adjacentIndex >= 0 && adjacentIndex < dustData.size()) {
                                int rowIndex = adjacentIndex / col;
                                int colIndex = adjacentIndex % col;
                                if (rowIndex >= 0 && rowIndex < row && colIndex >= 0 && colIndex < col) {
                                    int adjacentDust = dustData.get(adjacentIndex);
                                    dustData.set(adjacentIndex, (int) (adjacentDust - (adjacentDust * 0.3)));
                                }
                            }
                        }
                        updateButtonColors();
                    }
                });
            }
        }
    }

    // เมธอดช่องแสดงข้อความอย่างละเอียด
    private JPanel createMessage() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(15, 50, 20, 50));
        Color customColor = Color.decode("#BC9D6E");
        mainPanel.setBackground(customColor);

        Color customColor1 = Color.decode("#D4B483");
        JPanel panel = new JPanel(new GridLayout(6, 1, 50, 32));
        panel.setBorder(new EmptyBorder(40, 100, 40, 100)); // เพิ่มระยะห่าง
        panel.setBackground(customColor1);

        Color lightYellow = new Color(255, 255, 204);

        JPanel panel_1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel_1.setBorder(new EmptyBorder(13, 0, 10, 80));
        panel_1.setBackground(lightYellow);
        population = new JLabel("Population range: " + " 0"); // ช่วงจำนวนประชากร

        JPanel panel_2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel_2.setBorder(new EmptyBorder(13, 0, 10, 80));
        panel_2.setBackground(lightYellow);
        populationRandom = new JLabel("Population Random: " + " 0"); // จำนวนประชากรที่Random

        JPanel panel_3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel_3.setBorder(new EmptyBorder(13, 0, 10, 80));
        panel_3.setBackground(lightYellow);
        dust_Quantity = new JLabel("Dust Quantity : " + " 0"); // ปริมาณฝุ่น

        JPanel panel_4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel_4.setBorder(new EmptyBorder(13, 0, 10, 80));
        panel_4.setBackground(lightYellow);
        populationGood = new JLabel("Population Good : " + " 0"); // จำนวนประชากรสุขภาพดี

        JPanel panel_5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel_5.setBorder(new EmptyBorder(13, 0, 10, 80));
        panel_5.setBackground(lightYellow);
        populationSick = new JLabel("Population Sick : " + " 0"); // จำนวนประชากรป่วย

        JPanel panel_6 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel_6.setBorder(new EmptyBorder(13, 0, 10, 80));
        panel_6.setBackground(lightYellow);
        perSick = new JLabel("% Sick People  : " + " 0%"); // เปอร์เซ็นคนป่วย

        panel_1.add(population);
        panel_2.add(populationRandom);
        panel_3.add(dust_Quantity);
        panel_4.add(populationGood);
        panel_5.add(populationSick);
        panel_6.add(perSick);

        panel.add(panel_1);
        panel.add(panel_2);
        panel.add(panel_3);
        panel.add(panel_4);
        panel.add(panel_5);
        panel.add(panel_6);
        mainPanel.add(panel, BorderLayout.CENTER);
        return mainPanel;
    }

    // เมธอดรับจำนวนประชากร
    private JPanel createReadpopulation() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 10, 10));
        panel.setBorder(new EmptyBorder(0, 30, 0, 50)); // เพิ่มระยะห่าง
        panel.setOpaque(false);
        JButton Button_Rpopulation = new JButton("รับประชากร");
        text_Rpopulation = new JTextField(); // ใช้ตัวแปรของคลาสที่เราประกาศไว้
        Button_Rpopulation.setFont(new Font("Tahoma", Font.PLAIN, 12));
        Button_Rpopulation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                populText = text_Rpopulation.getText();
                population.setText("Population range: " + populText); // setข้อความใหม่ให้ populText
                String[] splitNum = populText.split("-");
                minPopulation = Integer.parseInt(splitNum[0]);
                maxPopulation = Integer.parseInt(splitNum[1]);
            }
        });
        panel.add(Button_Rpopulation);
        panel.add(text_Rpopulation);
        return panel;
    }

    // เมธอด กดกลับ
    private JPanel createBack() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 10, 10));
        panel.setOpaque(false);
        JButton back = new JButton("BACK");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuPage MenuPage = new menuPage();
                MenuPage.setVisible(true);
                dispose();
            }
        });
        JLabel returnM = new JLabel("Return to mainpage : ");
        panel.add(returnM);
        panel.add(back);
        return panel;
    }

    private void handleButtonClick(ActionEvent e) {
        JButton sourceButton = (JButton) e.getSource();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (dBtn[i][j] == sourceButton) {
                    dataBtn clickedButton = dBtn[i][j];
                    int index = i * col + j;

                    // ตรวจสอบว่าดัชนีถูกต้อง
                    if (index >= 0 && index < dustData.size()) {
                        int dust = dustData.get(index);
                        System.out.println("Index: " + index);
                        System.out.println("Dust Data: " + dust);

                        // ตั้งค่า PM2.5
                        clickedButton.setDustData(dust);

                        // ตรวจสอบการเปลี่ยนแปลง
                        if (clickedButton.isDustDataChanged()) {
                            System.out.println("Dust Data Changed");

                            // สุ่มเปอร์เซ็นต์คนป่วยใหม่
                            int newSickPercentage = clickedButton.getPerSickPeople();

                            // ทำการอัพเดทจำนวนประชากรตามที่ต้องการ
                            clickedButton.setNumberOfPeople(minPopulation, maxPopulation);
                            clickedButton.setSickPeople(clickedButton.getNumberOfPeople(), newSickPercentage);
                            clickedButton.setGoodPeople(clickedButton.getNumberOfPeople(),
                                    clickedButton.getSickPeople());

                            populationRandom.setText("Population Random: " + clickedButton.getNumberOfPeople());
                            dust_Quantity.setText("Dust Quantity : " + clickedButton.getDustData());
                            perSick.setText("% Sick People  : " + newSickPercentage);
                            populationSick.setText("Population Sick : " + clickedButton.getSickPeople());
                            populationGood.setText("Population Good : " + clickedButton.getGoodPeople());
                        } else {
                            System.out.println("Dust Data not changed");
                        }
                        return;
                    } else {
                        System.out.println("Index out of range: " + index);
                    }
                }
            }
        }
        System.out.println("Button not found.");
    }
}
