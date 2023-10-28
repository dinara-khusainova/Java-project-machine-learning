package GameMain;
import GameBird.*;
import GameSpider.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Main extends JFrame {
    Main(){
        super("Appp");
        setSize(3000,3000);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);
        JPanel gamePanel=new JPanel();
        JPanel gamePanel2=new JPanel();
        gamePanel.setBounds(0,40,Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height-50);//на окно ставим панельку как вкладка в браузере
        gamePanel2.setBounds(0,50,Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height-50);
        gamePanel.setLayout(null);
        gamePanel2.setLayout(null);
        add(gamePanel);
        add(gamePanel2);
        gamePanel2.setVisible(false);
        JButton gameButton=new JButton("Game 1");
        JButton gameButton2=new JButton("Game 2");
        gameButton.setBounds(0,0,100,30);
        add(gameButton);
        gameButton2.setBounds(500,0,100,30);
        add(gameButton2);
        gameButton2.addActionListener(new ActionListener() {
            boolean visible = true;
            @Override
            public void actionPerformed(ActionEvent e) {
                visible = !(visible);
                gamePanel2.setVisible(visible);
            }
        });
        JButton button=new JButton("START");
        JButton button2=new JButton("START");

        GameBird game=new GameBird (gamePanel, button);
        GameSpider game2=new GameSpider (gamePanel2, button2);
        gameButton.addActionListener(new ActionListener() {
            boolean visible=true;
            @Override
            public void actionPerformed(ActionEvent e) {
                visible=!visible;
                gamePanel.setVisible(visible);
            }
        });
        button.setBounds(20,20,180,30);
        gamePanel.add(button);
        button.addActionListener(e -> new Thread(game).start());
        button2.setBounds(1010,0,180,30);
        gamePanel2.add(button2);
        button2.addActionListener(e -> new Thread(game2).start());
        setVisible(true);
    }
    public static void main(String[] args) {
        new Main();
        System.out.println("Program work");
    }
}
