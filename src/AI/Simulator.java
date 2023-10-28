package AI;
import GameSpider.Pic;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;
public class Simulator {
    ArrayList<Pic> lights = new ArrayList<>(5);
    JLabel playerSpider, background2;
    AI ai;
    public Simulator(AI ai) {
        this.ai=ai;
        playerSpider = new JLabel(new ImageIcon("spiderman.png"));
        background2 = new JLabel(new ImageIcon("Frame.png"));
        playerSpider.setBounds(300, 500, 90, 90);
        background2.setBounds(0, 0, 1000, 600);
        for (int i = 0; i < 4; i++) {
            lights.add(createLights(i)); // Создаем объект "Pic"
            Random rand3 = new Random();
            int randomY = rand3.nextInt((-100 - (-300)) + 1) + (-300);
            int randomX = rand3.nextInt((800 - 100) + 1) + 100;
            lights.get(i).setBounds(randomX, randomY, 40, 40);// Устанавливаем положение и размер объекта
        }
    }
    Pic createLights(int nomer) {
        Pic pic = new Pic("light.png");
        Random rand = new Random();
        int randomX2 = rand.nextInt((600 - 100) + 1) + 100;
        pic.setBounds(0, 0, 40, 40);
        pic.setLocation(randomX2, 200);
        switch (nomer) {
            case 0 -> {
                pic = new Pic("light.png");
                pic.setLocation(randomX2, 200);
            }
            case 1 -> {
                pic = new Pic("light2.png");
                pic.setLocation(randomX2, 200);
            }
            case 2 -> {
                pic = new Pic("light3.png");
                pic.setLocation(randomX2, 200);
            }
            case 3 -> {
                pic = new Pic("bomb.png");
                pic.setLocation(randomX2, 200);
            }
        }
        return pic;
    }
    public float run() {
        int i = 0;
        boolean endd = true; // Флаг для завершения игры
        int g = 1;
        int time=0;
        while (endd && time<200000) {
            time+=10;
            if (i > 10) {g = (i / 10) + 1;}
            // Перемещение объектов (вращение) если они не пересекаются с пауком
            for (Pic l : lights) l.setLocation(l.getX(), l.getY() + g);
            Pic closest = lights.get(0);
            int close_dist = 10000;
            for (int p=0;p<3;p++) {
                if (lights.get(p).getY() < close_dist) {
                    closest = lights.get(p);
                    close_dist = lights.get(p).getY();
                }
            }
            if(ai.shouldMove(closest.getX(),closest.getY(), playerSpider.getX(), playerSpider.getY(),lights.get(3).getX(),lights.get(3).getY())){
                playerSpider.setLocation(playerSpider.getX()+5, playerSpider.getY());
            }
            else{
                playerSpider.setLocation(playerSpider.getX()-5, playerSpider.getY());
            }
            // Если паук пересекается с объектом light
            for (int u = 0; u < 3; u++) {
                if (lights.get(u).getBounds().intersects(playerSpider.getBounds())) {
                    i++;
                    Random rand3 = new Random();
                    int randomX3 = rand3.nextInt((600 - 100) + 1) + 100;
                    lights.get(u).setLocation(randomX3, 0);
                }
            }
            // Если паук пересекается с объектом bomb
            if (lights.get(3).getBounds().intersects(playerSpider.getBounds())) {
                i-=3;
                if(i<0) {endd = false;}
            }
            // Если объекты  выходят за пределы панели
            for (int u = 0; u < 4; u++) {
                if (lights.get(u).getY() > 550 ) {
                    Random rand1 = new Random();
                    int randomX = rand1.nextInt((800 - 100) + 1) + 100;
                    lights.get(u).setLocation(randomX, 0);
                }
            }
        }
        return i;
    }
}
