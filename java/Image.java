import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.Point;

public class Image extends JFrame { 
    Vector<Point> P;
    Set<Point> result;
    public Image(Vector<Point> P, Set<Point> result) {
        super("Convex Hull (Brute Force Algorithm) - Tony Eko Yuwono");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280,720);
        setVisible(true);
        this.P = P; 
        this.result = result;
    }

    public void paint(Graphics g) {
        super.paint(g);
        // int width = 1280; int height = 720; int scale = 1000;
        this.setBackground(Color.WHITE);
        g.setColor(Color.RED);
        for (int i = 0; i < this.P.size(); i++) {
            // int newX = ((int) ((this.P.get(i).getX() + 1) * width/2) / scale);
            // int newY = ((int) ((this.P.get(i).getY() - 1) * -height/2) / scale);
            // int newX = width/2 +(int) P.get(i).getX()*width/20;
            // int newY = height/2 - (int) P.get(i).getY()*height/20;
            // System.out.println("("+newX+","+newY+")");
            // g.fillOval(newX, newY, 7, 7);
            g.fillOval((int)this.P.get(i).getX(), (int)this.P.get(i).getY(), 7, 7);
        }
    }
}
