import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

public class testPage {
     int buttonCount = 200;
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(textfile))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int buttonIndex = 0;
        for (int i = 0; i < lines.size() && buttonIndex < buttonCount; i++) {
            String line = lines.get(i);
            String[] numbers = line.trim().split("\\s+");

            for (int j = 0; j < numbers.length && buttonIndex < buttonCount; j++) {
                JButton button = new JButton();
                String numStr = numbers[j];
                try {
                    int num = Integer.parseInt(numStr);

                    if (num <= 50) {
                        button.setBackground(Color.GREEN);
                    } else if (num <= 100) {
                        button.setBackground(Color.YELLOW);
                    } else if (num <= 150) {
                        button.setBackground(Color.ORANGE);
                    } else if (num <= 250) {
                        button.setBackground(Color.RED);
                    } else {
                        button.setBackground(Color.GRAY); // Default color for out of range
                    }
                } catch (NumberFormatException e) {
                    button.setBackground(Color.GRAY); // Default color for invalid number
                }
                panel.add(button);
                buttonIndex++;
                }
            }
}
