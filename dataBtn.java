import javax.swing.JButton;
import java.util.Random;

public class dataBtn extends JButton {
    private int Population;
    private int sickPeople;
    private int goodPeople;
    private int sickPercentage;
    private Random random = new Random();
    private int dustData; // ตัวแปรสำหรับเก็บข้อมูลฝุ่น
    private int previousDustData;
    private boolean isPopulationSet = false; // ตัวแปรตรวจสอบการสุ่มประชากร

    // ตัวสร้าง
    public dataBtn() {
        super();
    }

    public void setDustData(int data) {
        if (this.dustData != data) {
            this.previousDustData = this.dustData; // เก็บค่าปัจจุบันของ dustData ก่อนที่จะอัพเดท
            this.dustData = data; // อัพเดทค่าของ dustData
            // สุ่มเปอร์เซ็นต์คนป่วยใหม่เมื่อข้อมูลฝุ่นเปลี่ยนแปลง
            setPerSickPeople(randomSick(data));
        }
    }

    public int getDustData() {
        return this.dustData;
    }

    // เมธอดตั้งค่าประชากรทั้งหมด
    public void setNumberOfPeople(int minPopulation, int maxPopulation) {
        if (!isPopulationSet) { // ตรวจสอบการสุ่มประชากร
            Population = random.nextInt(maxPopulation - minPopulation + 1) + minPopulation;
            isPopulationSet = true; // ตั้งค่าสถานะว่าได้สุ่มแล้ว
        }
    }

    public int getNumberOfPeople() {
        return Population;
    }

    // เมธอดตั้งค่าคนป่วย
    public void setSickPeople(int Population, int sickPercentage) {
        int sickPeople = (Population * sickPercentage) / 100;
        this.sickPeople = sickPeople;
    }

    public int getSickPeople() {
        return sickPeople;
    }

    // เมธอดตั้งค่าคนดี
    public void setGoodPeople(int Population, int sickPeople) {
        int goodPeople = Population - sickPeople;
        this.goodPeople = goodPeople;
    }

    public int getGoodPeople() {
        return goodPeople;
    }

    // เมธอดตั้งค่าคนป่วย
    public void setPerSickPeople(int sickPercentage) {
        this.sickPercentage = sickPercentage;
    }

    public int getPerSickPeople() {
        return sickPercentage;
    }

    public boolean isDustDataChanged() {
        return this.dustData != this.previousDustData;
    }
    
    // ฟังก์ชันสุ่มเปอร์เซ็นต์คนป่วย
    public int randomSick(int dustData) {
        if (dustData <= 50) {
            return (int) (Math.random() * 10); // 0-9
        } else if (dustData <= 100) {
            return (int) (Math.random() * 10) + 10; // 10-19
        } else if (dustData <= 150) {
            return (int) (Math.random() * 10) + 20; // 20-29
        } else if (dustData <= 250) {
            return (int) (Math.random() * 21) + 30; // 30-50
        }
        return 0; // ค่าป้องกัน
    }
}
