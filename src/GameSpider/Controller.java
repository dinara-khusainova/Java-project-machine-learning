package GameSpider;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class Controller implements KeyListener {
    public JLabel label;
    public Controller(JLabel label){
        this.label = label;
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_D -> label.setLocation(label.getX() + 5, label.getY());
            case KeyEvent.VK_A -> label.setLocation(label.getX() - 5, label.getY());
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }
}
