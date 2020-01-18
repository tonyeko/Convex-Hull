import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.Point;

public class Image extends JFrame { 
    Vector<Point> vectorOfPoints;
    Point[] arrayOfPoints;
    Set<Point[]> SetTupleOfLine;
    int CenterX, CenterY;
    public Image(Vector<Point> vector, Set<Point[]> line) {
        super("Convex Hull (Brute Force Algorithm) - Tony Eko Yuwono");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.BLACK);
        setSize(1280,720);
        this.CenterX = 1280/2;
        this.CenterY = 720/2;
        // setSize(1000,1000);
        setVisible(true);
        this.vectorOfPoints = vector; 
        this.SetTupleOfLine = line;
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        // this.setBackground(new Color(7,241,163));
        g2.drawLine(this.CenterX, 0, this.CenterX, 720);
        g2.drawLine(0, this.CenterY, 1280, this.CenterY);
        g.setColor(Color.GREEN);
        g2.setStroke(new BasicStroke(3));
        for (Point[] A : this.SetTupleOfLine) {
            // g2.drawLine((int)A[0].getX(), (int)A[0].getY(), (int)A[1].getX(), (int)A[1].getY());
            int x1 = CenterX + (int)A[0].getX(); int y1 = CenterY - (int)A[0].getY();
            int x2 = CenterX + (int)A[1].getX(); int y2 = CenterY - (int)A[1].getY();
            g2.drawLine(x1, y1, x2, y2);
        }
        g.setColor(Color.RED);
        // int displ = 500;
        for (int i = 0; i < this.vectorOfPoints.size(); i++) {
            // g.fillOval((int)this.vectorOfPoints.get(i).getX(), (int)this.vectorOfPoints.get(i).getY(), 6, 6);
            int x = CenterX + (int)this.vectorOfPoints.get(i).getX();
            int y = CenterY - (int)this.vectorOfPoints.get(i).getY();
            g.fillOval(x-2, y-2, 5, 5);
        }
    }

    
}

            // UNTUK X,Y BISA NEGATIF
            // int newX = ((int) ((this.P.get(i).getX() + 1) * width/2) / scale);
            // int newY = ((int) ((this.P.get(i).getY() - 1) * -height/2) / scale);
            // int newX = width/2 +(int) P.get(i).getX()*width/20;
            // int newY = height/2 - (int) P.get(i).getY()*height/20;
            // System.out.println("("+newX+","+newY+")");
            // g.fillOval(newX, newY, 7, 7);