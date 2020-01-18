import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.util.List;
import java.awt.Point;

public class Image extends JFrame { 
    Vector<Point> vectorOfPoints;
    Point[] arrayOfPoints;
    Set<List<Point>> SetListOfLine;
    // Set<Point> setOfPoints;
    public Image(Vector<Point> vector, Set<Point> set, Set<List<Point>> line) {
        super("Convex Hull (Brute Force Algorithm) - Tony Eko Yuwono");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280,720);
        setVisible(true);
        this.vectorOfPoints = vector; 
        // this.setOfPoints = set;
        this.arrayOfPoints = set.toArray(new Point[set.size()]);
        this.SetListOfLine = line;
    }

    public void paint(Graphics g) {
        super.paint(g);
        // int width = 1280; int height = 720; int scale = 1000;
        int numberOfConvexPoint = this.arrayOfPoints.length;
        System.out.println("RESULT SIZE: "+ numberOfConvexPoint);
        int[] ConvexX = new int[numberOfConvexPoint];
        int[] ConvexY = new int[numberOfConvexPoint];
        this.setBackground(Color.WHITE);
        g.setColor(Color.RED);
        for (int i = 0; i < this.vectorOfPoints.size(); i++) {
            g.fillOval((int)this.vectorOfPoints.get(i).getX(), (int)this.vectorOfPoints.get(i).getY(), 7, 7);
        }
        for (int i = 0; i < this.arrayOfPoints.length; i++) {
            ConvexX[i] = (int) this.arrayOfPoints[i].getX();
            ConvexY[i] = (int) this.arrayOfPoints[i].getY();
            // System.out.println(this.arrayOfPoints[i].getX() + " " + this.arrayOfPoints[i].getY());
        }
        g.setColor(Color.GREEN);
        g.drawPolygon(ConvexX, ConvexY, numberOfConvexPoint);
    }

    
}

            // UNTUK X,Y BISA NEGATIF
            // int newX = ((int) ((this.P.get(i).getX() + 1) * width/2) / scale);
            // int newY = ((int) ((this.P.get(i).getY() - 1) * -height/2) / scale);
            // int newX = width/2 +(int) P.get(i).getX()*width/20;
            // int newY = height/2 - (int) P.get(i).getY()*height/20;
            // System.out.println("("+newX+","+newY+")");
            // g.fillOval(newX, newY, 7, 7);