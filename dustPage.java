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

class dustPage extends JFrame{
    private String inputText; // ตัวแปรสำหรับเก็บข้อความที่อ่านจาก JTextField
    private List<JButton> buttons;
    private List<Integer> dustData = new ArrayList<>();
    private JLabel population;
    private JTextField text_Rpopulation;

    dustPage(){
        setBounds(50,10,1220, 700); 
        setLayout(new BorderLayout(10,10));
        setTitle("DustPage");
        Color pastelBlue = new Color(173, 216, 230);
        Color pastelPink = new Color (255, 182, 193); 

        //Panel หลักซ้าย
       JPanel mainPanel_1=new JPanel(new BorderLayout(10,10));
       mainPanel_1.setBackground(pastelBlue);
       mainPanel_1.setBorder(new EmptyBorder(3, 0, 0, 0));

       // สร้าง panel หลัก ของฝน
       JPanel rainPanel = new JPanel(new GridLayout(1, 2, 15, 15));
       JPanel rainButton =  createRain( );
       rainPanel.add(rainButton);
       mainPanel_1.add(rainPanel,BorderLayout.SOUTH);

       // สร้าง panel หลัก ของข้อความ
       JPanel MessagePanel = new JPanel();
       MessagePanel.setBackground(pastelPink);
       JPanel Message=createMessage( );
       MessagePanel.add(Message);
       mainPanel_1.add(MessagePanel,BorderLayout.CENTER);

       add(mainPanel_1,BorderLayout.WEST);


        //Panel หลักขวา
       JPanel mainPanel_2=new JPanel(new BorderLayout(10,10));
       mainPanel_2.setBackground(pastelBlue);
       mainPanel_2.setBorder(new EmptyBorder(3, 750, 0, 0));

        // สร้าง panel หลัก ของกรอบสี
        JPanel colorPanel = new JPanel(new GridLayout(1, 4, 15, 15)); // ใช้ GridLayout เพื่อสร้างกรอบแต่ละสี
        colorPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        colorPanel.setBackground(pastelPink);
        JPanel redPanel = createColor(Color.RED, "มีจำนวนคนป่วย เกิน 30%");
        JPanel orangePanel = createColor(Color.ORANGE, "มีจำนวนคนป่วย 20%-29%");
        JPanel yellowPanel = createColor(Color.YELLOW, "มีจำนวนคนป่วย 10%-19%");
        JPanel greenPanel = createColor(Color.GREEN, "มีจำนวนคนป่วย 0%-9%");
        colorPanel.add(redPanel);
        colorPanel.add(orangePanel);
        colorPanel.add(yellowPanel);
        colorPanel.add(greenPanel);
       // เพิ่ม mainPanel เข้าไปใน mainPanel_2
       mainPanel_2.add(colorPanel, BorderLayout.NORTH);

       // สร้าง panel หลัก ของปุ่ม
       JPanel buttonPanel = new JPanel(new BorderLayout());
       buttonPanel.setBorder(new EmptyBorder(10, 0, 10,25));
       buttonPanel.setBackground(pastelPink);
        JPanel panel_button200 = createButton();
        buttonPanel.add(panel_button200, BorderLayout.CENTER);
        // เพิ่ม buttonPanel เข้าไปใน mainPanel_2
        mainPanel_2.add(buttonPanel, BorderLayout.CENTER);

        // สร้าง panel หลัก อ่านไฟล์และย้อนกลับ
        JPanel SPanel = new JPanel(new BorderLayout()); 
        SPanel.setBorder(new EmptyBorder(10, 0, 10, 30));
        SPanel.setBackground(pastelPink);
        JPanel panel_file = createFile( );
        JPanel back = createBack();
        JPanel panel_population =createpopulation();
        SPanel.add(panel_file,BorderLayout.WEST);
        SPanel.add(back,BorderLayout.EAST);
        SPanel.add(panel_population,BorderLayout.CENTER);
        mainPanel_2.add(SPanel, BorderLayout.SOUTH);

        // เพิ่ม mainPanel_2 เข้าไปใน JFrame
       add(mainPanel_2,BorderLayout.EAST);

    }



    // เมธอดสำหรับสร้างกรอบสี
    private JPanel createColor(Color color, String text) {
        JPanel panel = new JPanel(); // สร้าง JPanel ใหม่
        panel.setBackground(color); // กำหนดสีพื้นหลังของ JPanel ตามที่ส่งเข้ามาในฟังก์ชัน
        panel.setBorder(new LineBorder(Color.BLACK, 2)); // กำหนดเส้นขอบสีดำ
        JLabel label = new JLabel(text, JLabel.CENTER); //กำหนดข้อความให้อยู่ตรงกลาง
        label.setFont(new Font("Tahoma", Font.PLAIN, 14)); // กำหนดฟอนต์ที่รองรับภาษาไทย มีขนาด14 เป็นฟอนต์แบบธรรมดา
        panel.add(label);
    return panel;
    }

     // เมธอดสร้าง 200 ปุ่ม
     private JPanel createButton() {
        JPanel panel = new JPanel(new GridLayout(10, 20, 2, 2));
        panel.setBorder(new EmptyBorder(10, 40, 20, 6));
        Color pastelPink = new Color (255, 182, 193); 
        panel.setBackground(pastelPink);
        buttons = new ArrayList<>();
        int buttonCount = 200;
        for (int i = 0; i < buttonCount; i++) {
            JButton button = new JButton();
            buttons.add(button);
            panel.add(button);
        }
        return panel;
    }


     // เมธอด อ่านไฟล์
    private JPanel createFile() {
        JPanel panel = new JPanel(new GridLayout(1,2,10,10));
       panel.setBorder(new EmptyBorder(0, 49, 0, 0)); // เพิ่มระยะห่าง
        Color pastelPink = new Color (255, 182, 193); 
        panel.setBackground(pastelPink);
        JButton readfile = new JButton("ReadFile");
        JTextField textField_read = new JTextField();

        readfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputText = textField_read.getText();
                dataDust(inputText);
            }
        });

        panel.add(readfile);
        panel.add(textField_read);
    return panel;
    }

    //เก็บข้อมูลฝุ่น
    private void dataDust(String textfile) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(textfile))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("ไม่มีไฟล์");
            return;
        }
    
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] numbers = line.trim().split("\\s+");
            for (int j = 0; j < numbers.length; j++) {
                String numStr = numbers[j];

                try {
                    int num = Integer.parseInt(numStr);
                    dustData.add(num);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number: " + numStr);
                }
            }
        }
        updateButtonColors();    
    }


     //เมธอด set สีให้ปุ่ม 
     private void updateButtonColors() {
        int buttonIndex = 0;
        for (int i = 0; i < dustData.size(); i++) {
            int num = dustData.get(i);

            if (buttonIndex >= buttons.size()) break; // ถ้าไม่มีปุ่มเหลือให้ปรับสี ให้หยุดการวนลูป
            JButton button = buttons.get(buttonIndex);
            if (num <= 50) {
                button.setBackground(Color.GREEN);
            } else if (num <= 100) {
                button.setBackground(Color.YELLOW);
            } else if (num <= 150) {
                button.setBackground(Color.ORANGE);
            } else if (num <= 250) {
                button.setBackground(Color.RED);
            } else {
                button.setBackground(null);
            }
            buttonIndex++;
        }
    }


    // เมธอดปุ่มฝน
    private JPanel createRain( ){
        JPanel panel = new JPanel(new GridLayout(1,2,50,50));
        panel.setBorder(new EmptyBorder(25, 40, 50, 20)); // เพิ่มระยะห่าง
        Color pastelPink = new Color (255, 182, 193); 
        panel.setBackground(pastelPink);

        JButton rain = new JButton("RAIN");
        JButton royalRain = new JButton("ROYAL  RAIN");
        rain.setPreferredSize(new Dimension(150, 50));
        Color buleColor = Color.decode("#69E5E1");
        rain.setBackground(buleColor); 
        rain.setFont(new Font("Cooper Black", Font.BOLD, 22));
        rain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < dustData.size(); i++) {
                    dustData.set(i, (int)(dustData.get(i) * 0.5));
                }
                updateButtonColors();
            }
        });

        royalRain.setPreferredSize(new Dimension(150, 50));
        Color buleColor1 = Color.decode("#6AB7B5");
        royalRain.setBackground(buleColor1); 
        royalRain.setFont(new Font("Cooper Black", Font.BOLD, 14));
        royalRain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               addRoyalRainActionListeners();

                }
        });
        panel.add(rain);
        panel.add(royalRain);
    return panel;
    }


    private void addRoyalRainActionListeners() {
        for (JButton button : buttons) {
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton clickedButton = (JButton) e.getSource();
                    //ดึงปุ่มที่ถูกคลิกจาก ActionEvent และแปลงเป็น JButton e.getSource() คืนค่าออบเจ็กต์ที่เป็นแหล่งกำเนิดของเหตุการณ์ (ปุ่มที่ถูกคลิก).
                    int clickedIndex = buttons.indexOf(clickedButton);/*หาตำแหน่งของปุ่มที่ถูกคลิกในลิสต์ buttons.
                    buttons.indexOf(clickedButton) คืนค่าดัชนีของปุ่มที่ถูกคลิกในลิสต์ buttons. ถ้าไม่พบ, คืนค่า -1.*/

                    if (clickedIndex != -1) {
                        // ลดฝุ่น 50% สำหรับปุ่มที่ถูกกด
                        int currentDust = dustData.get(clickedIndex); //ดึงค่าฝุ่นจาก dustData ที่ตำแหน่งเดียวกับปุ่มที่ถูกคลิก.
                        dustData.set(clickedIndex, (int) (currentDust * 0.5)); 

                        // ลดฝุ่น 30% สำหรับปุ่มที่อยู่รอบข้าง
                        int[] adjacentIndices = {
                            //กำหนดดัชนีของปุ่มที่อยู่รอบๆ ปุ่มที่ถูกคลิก.
                            clickedIndex - 21, clickedIndex - 20, clickedIndex - 19,
                            clickedIndex - 1,                     clickedIndex + 1,
                            clickedIndex + 19, clickedIndex + 20, clickedIndex + 21
                        }; // โดยใช้รูปแบบการจัดเรียงในกริด 10x20.
                       

                        for (int i = 0; i < adjacentIndices.length; i++) {
                            int index = adjacentIndices[i];
                            if (index >= 0 && index < dustData.size()) {
                                int adjacentDust = dustData.get(index);
                                dustData.set(index, (int) (adjacentDust-(adjacentDust * 0.3)));
                            }
                        }
                        updateButtonColors();
                    }
                }
            });
        }
    }

    // เมธอดสำหรับสร้าง JPanel ที่มีข้อความ เมธอดนี้มีชื่อว่าcreateMessage รีเทินเป็นชนิดของ JPanel เป็นแบบ private ใช้ได้แต่ในคลาสนี้เท่านั้น
    private JPanel createMessage( ){
        JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        mainPanel.setBorder(new EmptyBorder(15, 30, 15, 30));
        Color customColor = Color.decode("#BC9D6E");
        mainPanel.setBackground(customColor); 

        Color customColor1 = Color.decode("#D4B483");
        JPanel panel = new JPanel(new GridLayout(5,1,10,50));
        panel.setBorder(new EmptyBorder(20, 40, 20, 50)); // เพิ่มระยะห่าง
        panel.setBackground(customColor1);

        Color lightYellow = new Color(255, 255, 204);

        JPanel panel_2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel_2.setBorder(new EmptyBorder(10, 0, 10, 80));
        panel_2.setBackground(lightYellow);
        population = new JLabel("Population : "+" 0"); //จำนวนประชากร

        JPanel panel_1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel_1.setBorder(new EmptyBorder(10, 0, 10, 80));
        panel_1.setBackground(lightYellow);
        JLabel dust_Quantity = new JLabel("Dust Quantity : "+" 0"); //ปริมาณฝุ่น

        JPanel panel_3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel_3.setBorder(new EmptyBorder(10, 0, 10, 80));
        panel_3.setBackground(lightYellow);
        JLabel populationGood = new JLabel("Population Good : "+" 0"); //จำนวนประชากรสุขภาพดี

        JPanel panel_4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel_4.setBorder(new EmptyBorder(10, 0, 10, 80));
        panel_4.setBackground(lightYellow);
        JLabel populationSick = new JLabel("Population Sick : "+" 0"); //จำนวนประชากรป่วย

        JPanel panel_5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel_5.setBorder(new EmptyBorder(10, 0, 10, 80));
        panel_5.setBackground(lightYellow);
        JLabel buttonNum = new JLabel("% Sick People  : "+" 0%"); //เปอร์เซ็นคนป่วย

        panel_1.add(population);
        panel_2.add(dust_Quantity);
        panel_3.add(populationGood);
        panel_4.add(populationSick);
        panel_5.add(buttonNum);
        panel.add(panel_1);
        panel.add(panel_2);
        panel.add(panel_3);
        panel.add(panel_4);
        panel.add(panel_5);
        mainPanel.add(panel);
    return mainPanel;
    }

    //เมธอด รับประชากร
    private JPanel createpopulation() {
        JPanel panel = new JPanel(new GridLayout(1,2,10,10));
        panel.setBorder(new EmptyBorder(0, 30, 0, 50)); // เพิ่มระยะห่าง
        panel.setOpaque(false);
        JButton Button_Rpopulation = new JButton("รับประชากร");
        text_Rpopulation = new JTextField();  //ใช้ตัวแปรของคลาสที่เราประกาศไว้
        Button_Rpopulation.setFont(new Font("Tahoma", Font.PLAIN, 12));
        Button_Rpopulation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String populText = text_Rpopulation.getText();
                population.setText("Population : " + populText); // อัปเดต population
                System.out.println("ประชากรที่รับเข้ามา"+populText);
            }
        });
        text_Rpopulation.setSize(20, 10);
        panel.add(Button_Rpopulation);
        panel.add(text_Rpopulation);
    return panel;
    }

    private void addTextMessage(){
        for (JButton button : buttons) {
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton clickedButton = (JButton) e.getSource();
            }
        });
    }

    }









        //เมธอด กดกลับ
    public JPanel createBack( ) {
        JPanel panel = new JPanel(new GridLayout(1,2,10,10));
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

}