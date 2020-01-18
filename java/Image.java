import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.Point;

public class Image extends JFrame { 
    Vector<Point> vectorOfPoints;
    Point[] arrayOfPoints;
    Set<Point[]> SetTupleOfLine;
    public Image(Vector<Point> vector, Set<Point[]> line) {
        super("Convex Hull (Brute Force Algorithm) - Tony Eko Yuwono");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280,720);
        setVisible(true);
        this.vectorOfPoints = vector; 
        this.SetTupleOfLine = line;
        System.out.println("===============================================================");
        for (Point[] A : line) {
            System.out.println("=================");
            for (int it = 0; it < A.length; it++) {
                System.out.println(A[it].getX()+" "+A[it].getY());
            }
            System.out.println("=================");
        }
        System.out.println("===============================================================");
    }

    public void paint(Graphics g) {
        super.paint(g);
        // int width = 1280; int height = 720; int scale = 1000;
        int numberOfConvexPoint = this.SetTupleOfLine.size();
        int[] ConvexX = new int[numberOfConvexPoint+1];
        int[] ConvexY = new int[numberOfConvexPoint+1];
        this.setBackground(Color.WHITE);
        g.setColor(Color.RED);
        for (int i = 0; i < this.vectorOfPoints.size(); i++) {
            g.fillOval((int)this.vectorOfPoints.get(i).getX(), (int)this.vectorOfPoints.get(i).getY(), 7, 7);
        }
        g.setColor(Color.GREEN);
        // int i = 0;
        for (Point[] A : this.SetTupleOfLine) {
            System.out.println("("+(int)A[0].getX()+","+(int)A[0].getY()+") & ("+(int)A[1].getX()+","+(int)A[1].getY()+")");
            g.drawLine((int)A[0].getX(), (int)A[0].getY(), (int)A[1].getX(), (int)A[1].getY());
        }
        // g.setColor(Color.BLUE);
        // g.drawPolygon(ConvexX, ConvexY, numberOfConvexPoint);
    }

    
}

            // UNTUK X,Y BISA NEGATIF
            // int newX = ((int) ((this.P.get(i).getX() + 1) * width/2) / scale);
            // int newY = ((int) ((this.P.get(i).getY() - 1) * -height/2) / scale);
            // int newX = width/2 +(int) P.get(i).getX()*width/20;
            // int newY = height/2 - (int) P.get(i).getY()*height/20;
            // System.out.println("("+newX+","+newY+")");
            // g.fillOval(newX, newY, 7, 7);