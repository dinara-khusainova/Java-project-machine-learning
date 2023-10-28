package GameBird;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
public class GameBird implements Runnable{
    public JLabel playerBird;
    JTextArea textArea=new JTextArea();
    JTextArea score=new JTextArea();
    ArrayList<Bar> pipes= new ArrayList<>(4);
    public GameBird(JPanel gamePanel, JComponent componentListener){
        textArea.setBounds(500,300,80,30);
        gamePanel.add(textArea);
        textArea.setText("You're loser!");
        textArea.setFont(new Font("Arial",Font.BOLD,14));
        textArea.setEditable(false);// Нельзя вносить правки
        textArea.setVisible(false);// Пока невидимый

        score.setBounds(1100,30,80,30);
        gamePanel.add(score);
        score.setText("Score: " );
        score.setFont(new Font("Arial",Font.BOLD,14));
        score.setEditable(false);//нельзя вносить поправки

        playerBird =new JLabel(new ImageIcon("Bird.gif"));
        playerBird.setBounds(320,70,55,32);
        gamePanel.add(playerBird);
        for (int i=0;i<4;i++){
            pipes.add(createPipes(i));
            pipes.get(i).setVisible(false); //видимость трубы (не видим)
            gamePanel.add(pipes.get(i)); //добавили невидимые на панель
        }
        pipes.add(createPipes(4));
        pipes.get(4).setVisible(false);
        gamePanel.add(pipes.get(4));

        JLabel background=new JLabel(new ImageIcon("sky.gif"));
        background.setBounds(20,40,1000,600);
        gamePanel.add(background);
        ControllerBird controller=new ControllerBird(playerBird);
        componentListener.addKeyListener(controller);
    }
    Bar createPipes(int nomer){
        Bar bar=new Bar("pipeup.png");
        switch (nomer) {
            case 0 -> {
                bar = new Bar("pipeup.png");
                bar.setBounds(1000, 340, 100, 300);
                bar.setVisible(false);
            }
            case 1 -> {
                bar = new Bar("pipedown.png");
                bar.setBounds(2200, 38, 100, 300);
            }
            case 2 -> {
                bar = new Bar("pipedown.png");
                bar.setBounds(1300, 38, 100, 300);
            }
            case 3 -> {
                bar = new Bar("pipedownlong.png");
                bar.setBounds(1900, 40, 100, 400);
            }
            case 4 -> {
                bar = new Bar("pipeuplong.png");
                bar.setBounds(1600, 230, 100, 400);
            }
        }
        return bar;
    }
    boolean collision_with_pipe(int u, boolean notstolknovenie){
        if (pipes.get(u).getBounds().intersects(playerBird.getBounds())) {
            textArea.setVisible(true);
            notstolknovenie = false;
        }
        return notstolknovenie;
    }
    int scoring_points(int u, int ii){
        if (playerBird.getX() > pipes.get(u).getX() && pipes.get(u).forPipe) {
            ii++;
            score.setText("Score: " + ii);
            pipes.get(u).forPipe = false;
        }
        return ii;
    }
    void disappearance_of_outgoing_pipes(int u){
        if (pipes.get(1).getX() <= -5) {
            pipes.get(1).setLocation(2000, pipes.get(1).getY());
            pipes.get(1).forPipe = true;
        }
        if (pipes.get(u).getX() <= -5) {
            pipes.get(u).setLocation(1400, pipes.get(u).getY());
            pipes.get(u).forPipe = true;
        }
    }
    void moving_pipes_left(int u){
        pipes.get(u).setLocation(pipes.get(u).getX() - 2, pipes.get(u).getY());
    }
    void displaying_or_hiding_pipes(int u){
        pipes.get(u).setVisible(pipes.get(u).getX() <= 995);
    }
    @Override
    public void run() {
        boolean notstolknovenie = true;
        int ii = 0; // Счётчик пройденных труб
        while (notstolknovenie) {
            for (int u = 0; u < 5; u++) {
                displaying_or_hiding_pipes(u);
                moving_pipes_left(u);
                disappearance_of_outgoing_pipes(u);
                ii = scoring_points(u, ii);
                notstolknovenie = collision_with_pipe(u,notstolknovenie);
            }
            playerBird.setLocation(playerBird.getX(), playerBird.getY() + 1);
            try {
                Thread.sleep(10);
            } catch (Exception e) {return;}
        }
    }
}

