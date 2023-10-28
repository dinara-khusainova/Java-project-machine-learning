package GameSpider;
import AI.AI;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;
public class GameSpider implements Runnable {
    ArrayList<Pic> lights = new ArrayList<>(5);
    JLabel playerSpider, background2;
    JTextArea textArea3, textArea2;
    public GameSpider(JPanel gamePanel2, JComponent componentListener) {
        playerSpider = new JLabel(new ImageIcon("spiderman.png"));
        background2 = new JLabel(new ImageIcon("Frame.png"));
        textArea3 = new JTextArea();
        gamePanel2.add(textArea3);
        textArea2 = new JTextArea();
        textArea2.setText("You're dead!");
        textArea3.setText("Points: ");
        gamePanel2.add(textArea2);
        textArea2.setBounds(500,300,80,30);
        playerSpider.setBounds(300, 500, 90, 90);
        textArea3.setBounds(1010, 40, 100, 30);
        textArea3.setEditable(false);
        textArea2.setBounds(500, 300, 80, 30);
        textArea2.setEditable(false);
        textArea2.setVisible(false);
        gamePanel2.add(playerSpider);
        background2.setBounds(0, 0, 1000, 600);
        for (int i = 0; i < 4; i++) {
            lights.add(createLights(i));
            Random rand3 = new Random();
            int randomY = rand3.nextInt((-100 - (-300)) + 1) + (-300);
            int randomX = rand3.nextInt((800 - 100) + 1) + 100;
            lights.get(i).setBounds(randomX, randomY, 40, 40);
            gamePanel2.add(lights.get(i));
        }
        Controller controller=new Controller(playerSpider);
        componentListener.addKeyListener(controller);
        gamePanel2.add(background2);
    }
    Pic createLights(int nomer) {
        Pic pic = new Pic("light.png");
        Random rand2 = new Random();
        int randomX2 = rand2.nextInt((600 - 100) + 1) + 100;
        pic.setBounds(0, 0, 40, 40);
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
    int scoring_points(int i){
        for (int u = 0; u < 3; u++) {
            if (lights.get(u).getBounds().intersects(playerSpider.getBounds())) {
                i++;
                textArea3.setText("Points: " + i);
                Random rand3 = new Random();
                int randomX3 = rand3.nextInt((600 - 100) + 1) + 100;
                lights.get(u).setLocation(randomX3, 0);
            }
        }
        return i;
    }
    boolean crossing_with_bomb(int i, boolean endd){
        if (lights.get(3).getBounds().intersects(playerSpider.getBounds())) {
            textArea2.setVisible(true);
            i-=3;
            if(i<0) {endd = false;}
        }
        return endd;
    }
    void border_crossing(){
        for (int u = 0; u < 4; u++) {
            if (lights.get(u).getY() > 550 ) {
                Random rand1 = new Random();
                int randomX = rand1.nextInt((800 - 100) + 1) + 100;
                lights.get(u).setLocation(randomX, 0);
            }
        }
    }
    void repainting(){
        for (Pic pic : lights) {
            pic.angle += 3;
            pic.repaint();
        }
    }
    void moving_to_closest(AI ai){
        Pic closest = lights.get(0);
        int close_dist = 10000;
        for (int p=0;p<3;p++)
            if (Math.pow(playerSpider.getX()-lights.get(p).getX(),2)+Math.pow(playerSpider.getY()-lights.get(p).getY(),2)<close_dist){
                closest = lights.get(p);
                close_dist=(int)(Math.pow(playerSpider.getX()-lights.get(p).getX(),2)+Math.pow(playerSpider.getY()-lights.get(p).getY(),2));
            }

        if(ai.shouldMove(closest.getX(),closest.getY(), playerSpider.getX(), playerSpider.getY(),lights.get(3).getX(),lights.get(3).getY())){
            playerSpider.setLocation(playerSpider.getX()+5, playerSpider.getY());
        }
        else{
            playerSpider.setLocation(playerSpider.getX()-5, playerSpider.getY());
        }
    }
    void moving_objects(int g){
        for (Pic l : lights) l.setLocation(l.getX(), l.getY() + g);
    }
    int acceleration_of_fall(int i, int g){
        if (i > 10) {g = (i / 10) + 1;}
        return g;
    }
    @Override
    public void run() {
        int i = 0;
        AI ai = new AI(new float[]{-0.2836951f,  0.3401295f,  3.013947f,  -0.111403465f,  -2.1147878f,  -2.0710797f,  -8.5993f});
        boolean endd = true; // Флаг для завершения игры
        int g = 1;
        while (endd) {
            //поворот объектов и отрисовка
            repainting();
            //Ускорение падения объектов
            g = acceleration_of_fall(i,g);
            moving_objects(g);
            moving_to_closest(ai);
            // Если паук пересекается с объектом light
            i = scoring_points(i);
            // Если паук пересекается с объектом bomb
            endd = crossing_with_bomb(i, endd);
            // Если объекты  выходят за пределы панели
            border_crossing();
            try {
                Thread.sleep(10);
            } catch (Exception e) {return;}
        }
        for (int u = 0; u < 4; u++) {lights.get(u).setVisible(false);}
    }
}