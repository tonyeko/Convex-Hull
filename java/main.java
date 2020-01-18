import java.util.*;
import java.util.List;
import java.awt.*;

class Main {
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        // Kamus
        int N, x, y, i;
        long startTime, endTime;
        Vector<Point> P = new Vector<Point>();
        Set<Point> result = new HashSet<Point>();
        Set<List<Point>> ConvexHull = new HashSet<List<Point>>();
        // Algoritma
        System.out.print("Masukkan banyaknya titik yang diinginkan: "); N = input.nextInt();
        if (N > 0) {
            System.out.println("\n"+N+" Titik Acak:");
            for (i = 0; i < N; i++) {
                x = getRandomIntegerBetweenRange(1260,10);
                y = getRandomIntegerBetweenRange(700,35);
                P.add(new Point(x,y));
                // P.add(new Point(i,0));
                System.out.println("Titik ke-" + (i+1) + ": " + "(" +(int)(P.get(i)).getX() + "," + (int)(P.get(i)).getY() + ")");
            }
            /* ================================= CONVEX HULL ================================ */
            startTime = System.currentTimeMillis();
            ConvexHull = SearchConvexHull(P);
            endTime = System.currentTimeMillis();
            /* ============================================================================== */
            result.addAll(ConvertSetListToSet(ConvexHull));
            System.out.print("\nHimpunan Titik Pembentuk Convex Hull: ");
            System.out.print("{");
            for (Point A : result) {
                System.out.print("(" + (int) A.getX() + "," + (int) A.getY() + "),");
            }
            System.out.println("\b}");
            System.out.println("\nWaktu untuk menemukan Convex Hull: " + (endTime - startTime) + " ms");
            new Image(P, result, ConvexHull);
        } else {
            // N valid jika N > 1
            System.out.println("Masukkan tidak sesuai!");
        }
    }

    static int getRandomIntegerBetweenRange(int min, int max) {
        int x = (int) (Math.random() * ((max - min) + 1)) + min;
        return x;
    }

    static Set<List<Point>> SearchConvexHull(Vector<Point> P) {
        /* Kamus Lokal */
        int i, j, k;
        double a, b, c;
        boolean stop, less;
        Set<List<Point>> result = new HashSet<List<Point>>();
        List<Point> ConvexHullLine = new ArrayList<Point>();
        /* Algoritma */
        if (P.size() > 2) {
            for (i = 0; i < P.size(); i++) {
                for (j = 0; j < P.size(); j++) {
                    if (!(P.get(j).equals(P.get(i)))) {
                        // Membuat persamaan garis: ax + by = c
                        a = (P.get(j)).getY() - (P.get(i)).getY();
                        b = (P.get(i)).getX() - (P.get(j)).getX();
                        c = (P.get(i)).getX() * (P.get(j)).getY() - (P.get(i)).getY() * (P.get(j)).getX();
                        k = 0;
                        stop = false;
                        // Inisiasi pengecekan
                        while (((P.get(k).equals(P.get(i))) || (P.get(k).equals(P.get(j)))) && k < P.size()) {
                            k++;
                        }
                        if (a * P.get(k).getX() + b * P.get(k).getY() < c) {
                            less = true;
                        } else {
                            less = false;
                        }
                        // Looping pengecekan
                        while (k < P.size() && !stop) {
                            /*
                             * Stop saat ditemukan satu titik yang berada di sisi yang berbeda dengan titik
                             * yang lain pada garis ax+by=c
                             */
                            if (!(P.get(k).equals(P.get(i))) && !(P.get(k).equals(P.get(j)))) {
                                if ((less && (a * P.get(k).getX() + b * P.get(k).getY() > c))
                                        || (!less && (a * P.get(k).getX() + b * P.get(k).getY() < c))) {
                                    stop = true;
                                }
                            }
                            if (!stop) {
                                k++;
                            }
                        }
                        // Jika !stop, semua titik berada di kiri atau di kanan garis (P[i] dan P[j]
                        // titik2 pembentuk convex hull)
                        if (!stop) {
                            ConvexHullLine.add(P.get(i));
                            ConvexHullLine.add(P.get(j));
                            result.add(ConvexHullLine);
                        }
                    }
                }
            }
        } else {
            // Kasus untuk 2 titik, 2 titik tersebut adalah pembentuk convex hull
            ConvexHullLine.addAll(P);
            result.add(ConvexHullLine);
        }
        return result;
    }

    static Set<Point> ConvertSetListToSet(Set<List<Point>> SetListOfPoint) {
        Set<Point> result = new HashSet<Point>();
        for(List<Point> A : SetListOfPoint) {
            for (int it = 0; it < A.size(); it++) {
                result.add(A.get(it));
            }
        }
        return result;
    }
}