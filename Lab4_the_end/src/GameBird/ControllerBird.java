package GameBird;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class ControllerBird implements KeyListener {
    JLabel label;
    public ControllerBird(JLabel label) {
        this.label = label;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> label.setLocation(label.getX(), label.getY() - 10);
            case KeyEvent.VK_S -> label.setLocation(label.getX(), label.getY() + 10);
            case KeyEvent.VK_A -> label.setLocation(label.getX() - 10, label.getY());
            case KeyEvent.VK_D -> label.setLocation(label.getX() + 10, label.getY());
            default -> {
            }
        }
    }
    @Override
    public void keyReleased (KeyEvent e){

    }
}