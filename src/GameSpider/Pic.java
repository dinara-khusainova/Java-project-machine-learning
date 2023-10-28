package GameSpider;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
public class Pic extends JComponent {
    Image img;
    int angle;
    public Pic(String path){
        super();
        img=new ImageIcon(path).getImage();
    }
    @Override
    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        AffineTransform at=g2.getTransform();
        at.rotate(Math.toRadians(angle), (double) getWidth() /2, (double) getHeight() /2);
        g2.setTransform(at);
        g2.drawImage(img,0,0,getWidth(),getHeight(),null);
    }
}
