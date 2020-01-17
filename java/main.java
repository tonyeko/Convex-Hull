import java.util.*;
import java.io.*;
import java.awt.Point;
import java.awt.Graphics;

class Main {
    // Atribut
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        // Kamus
        int N, x, y, i;
        Vector<Point> P = new Vector<Point>();
        Set<Point> result = new HashSet<Point>();
        // Algoritma
        System.out.print("Masukkan banyaknya titik yang diinginkan: "); N = input.nextInt();
        if (N > 0) {
            System.out.println("\n"+N+" Titik Acak:");
            for (i = 0; i < N; i++) {
                x = getRandomIntegerBetweenRange(10000,-10000);
                y = getRandomIntegerBetweenRange(10000,-10000);
                P.add(new Point(x,y));
                // P.add(new Point(i,0));
                System.out.println("Titik ke-" + (i+1) + ": " + "(" + (P.get(i)).getX() + "," + (P.get(i)).getY() + ")");
            }
            long startTime = System.currentTimeMillis();
            result.addAll(SearchConvexHull(P));
            long endTime = System.currentTimeMillis();
            System.out.println("\nTitik-titik Pembentuk Convex Hull:");
            for(Point X : result) {
                System.out.println("("+X.getX()+","+X.getY()+")");
            }
            System.out.println("\nWaktu untuk menemukan Convex Hull: " + (endTime - startTime) + " ms");
            // SHOW IMAGE
        } else {
            System.out.println("Masukkan tidak sesuai!");
        }
    }

    public static int getRandomIntegerBetweenRange(int min, int max) {
        int x = (int) (Math.random() * ((max - min) + 1)) + min;
        return x;
    }

    static Set<Point> SearchConvexHull(Vector<Point> P) {
        /* Kamus Lokal */ 
        int i, j, k;
        double a, b, c;
        boolean stop, less;
        Set<Point> result = new HashSet<Point>();
        /* Algoritma */
        for (i = 0; i < P.size(); i++) {
            for (j = 0; j < P.size(); j++) {
                if (!(P.get(j).equals(P.get(i)))) {
                    // Membuat persamaan garis: ax + by = c
                    a = (P.get(j)).getY() - (P.get(i)).getY();
                    b = (P.get(i)).getX() - (P.get(j)).getX();
                    c = (P.get(i)).getX() * (P.get(j)).getY() - (P.get(i)).getY() * (P.get(j)).getX();
                    k = 0; stop = false;
                    // Inisiasi pengecekan
                    while ( ((P.get(k).equals(P.get(i))) || (P.get(k).equals(P.get(j))) ) && k < P.size()) { k++; }
                    if (a*P.get(k).getX()+b*P.get(k).getY() < c) { less = true; } 
                    else { less = false; }
                    // Looping pengecekan 
                    while (k < P.size() && !stop) {
                        /* Stop saat ditemukan satu titik yang berada di sisi yang berbeda dengan titik yang lain pada garis ax+by=c */
                        if (!(P.get(k).equals(P.get(i))) && !(P.get(k).equals(P.get(j)))) {
                            if ((less &&  (a*P.get(k).getX()+b*P.get(k).getY() > c)) || (!less && (a*P.get(k).getX()+b*P.get(k).getY() < c))) {
                                stop = true;
                            } 
                        }
                        if (!stop) { k++; }
                    }
                    // Jika !stop, semua titik berada di kiri atau di kanan garis (P[i] dan P[j] titik2 pembentuk convex hull)
                    if (!stop) {
                        result.add(P.get(i));
                        result.add(P.get(j));
                    }
                }
            }	
        }
        return result;
    }
}
