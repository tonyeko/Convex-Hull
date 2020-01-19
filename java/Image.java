import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.Point;

public class Image extends JFrame { 
    Vector<Point> vectorOfPoints;
    Point[] arrayOfPoints;
    Set<Point[]> SetTupleOfLine;
    int CenterX, CenterY;
    ImageIcon img = new ImageIcon("img/icon.png");
    public Image(Vector<Point> vector, Set<Point[]> line) {
        super("Convex Hull (Brute Force Algorithm) - 13518030");
        getContentPane().setBackground(new Color(239,252,239));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(img.getImage());
        setSize(1280,720);
        setVisible(true);
        // Pusat Koordinat
        this.CenterX = 1280/2;
        this.CenterY = 720/2;
        this.vectorOfPoints = vector; 
        this.SetTupleOfLine = line;
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        // Draw Sumbu X, Y, koordinat batas 
        g.setColor(new Color(148,211,172));
        g2.setStroke(new BasicStroke(2));
        g2.drawLine(this.CenterX, 0, this.CenterX, 720);
        g2.drawLine(0, this.CenterY, 1280, this.CenterY);
        g.drawString("(0,360)", this.CenterX-45, 45);
        g.drawString("(0,-360)", this.CenterX-50, 705);
        g.drawString("(640,0)", 1230, this.CenterY+15);
        g.drawString("(-640,0)", 10, this.CenterY+15);
        // Draw X+ dan Y+
        g.drawString("(0,0)", this.CenterX+3, this.CenterY-5);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 20)); 
        g.drawString("x+", 1250, this.CenterY-5);
        g.drawString("y+", this.CenterX+5, 45);
        // Draw Convex Hull
        g.setColor(new Color(255,182,119));
        g2.setStroke(new BasicStroke(3));
        for (Point[] A : this.SetTupleOfLine) {
            int x1 = CenterX + (int)A[0].getX(); int y1 = CenterY - (int)A[0].getY();
            int x2 = CenterX + (int)A[1].getX(); int y2 = CenterY - (int)A[1].getY();
            g2.drawLine(x1, y1, x2, y2);
        }
        // Draw All Points
        g.setColor(new Color(212,93,121));
        for (int i = 0; i < this.vectorOfPoints.size(); i++) {
            int x = CenterX + (int)this.vectorOfPoints.get(i).getX();
            int y = CenterY - (int)this.vectorOfPoints.get(i).getY();
            g.fillOval(x-2, y-2, 5, 5);
        }
    }   
}