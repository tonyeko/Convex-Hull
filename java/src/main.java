import java.util.*;
import java.awt.*;

class Main {
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        // Kamus
        int N, x, y, i;
        double startTime, endTime;
        Vector<Point> P = new Vector<Point>();
        Set<Point> result = new HashSet<Point>();
        Set<Point[]> ConvexHull = new HashSet<Point[]>();
        // Algoritma
        System.out.print("Masukkan banyaknya titik yang diinginkan: "); N = input.nextInt();
        if (N > 1) {
            System.out.println("\n"+N+" Titik Acak:");
            for (i = 0; i < N; i++) {
                x = getRandomInteger(-630,630); // -630 <= x <= 630
                y = getRandomInteger(-340,320); // -340 <= y <= 320
                P.add(new Point(x,y));
                System.out.println("Titik ke-" + (i+1) + ": " + "(" + x + "," + y + ")");
            }
            /* ================================= CONVEX HULL ================================ */
            startTime = System.nanoTime();
            ConvexHull = SearchConvexHull(P);
            endTime = System.nanoTime();
            /* ============================================================================== */
            result.addAll(ConvertSetTupleToSet(ConvexHull));
            System.out.print("\nHimpunan Titik Pembentuk Convex Hull: ");
            System.out.print("{");
            for (Point A : result) {
                System.out.print("(" + (int) A.getX() + "," + (int) A.getY() + "),");
            }
            System.out.println("\b}");
            System.out.println("\nWaktu untuk menemukan Convex Hull: "+ (endTime - startTime) / 1000000 +" ms");
            // Plotting titik-titik dan menggambar convex hull
            new Image(P, ConvexHull);
        } else {
            // N valid jika N > 1
            System.out.println("Masukan tidak sesuai!");
        }
    }

    static Set<Point[]> SearchConvexHull(Vector<Point> P) {
        /* Kamus Lokal */
        int i, j, k;
        double a, b, c;
        boolean stop, less;
        Set<Point[]> result = new HashSet<Point[]>();  // set berisi array yang berisi 2 point (membentuk garis convex hull)
        Point[] line;
        /* Algoritma */
        if (P.size() > 2) {
            for (i = 0; i < P.size(); i++) {
                for (j = i + 1; j < P.size(); j++) {
                    // Membuat persamaan garis: ax + by = c
                    a = (P.get(j)).getY() - (P.get(i)).getY(); b = (P.get(i)).getX() - (P.get(j)).getX();
                    c = (P.get(i)).getX() * (P.get(j)).getY() - (P.get(i)).getY() * (P.get(j)).getX();
                    k = 0;
                    stop = false;
                    // Inisiasi pengecekan
                    while (((P.get(k).equals(P.get(i))) || (P.get(k).equals(P.get(j)))) && k < P.size()) { k++; }
                    if (a * P.get(k).getX() + b * P.get(k).getY() < c) { less = true; } 
                    else { less = false; }
                    // Looping pengecekan
                    while (k < P.size() && !stop) {
                        // Stop saat ditemukan satu titik yang berada di sisi yang berbeda dengan titik yang lain pada garis ax+by=c
                        if (!(P.get(k).equals(P.get(i))) && !(P.get(k).equals(P.get(j)))) {
                            if ((less && (a * P.get(k).getX() + b * P.get(k).getY() > c)) || (!less && (a * P.get(k).getX() + b * P.get(k).getY() < c))) {
                                stop = true;
                            }
                        }
                        if (!stop) { k++; }
                    }
                    // Jika !stop, semua titik berada di kiri atau di kanan garis (P[i] dan P[j] titik2 pembentuk convex hull)
                    if (!stop) {
                        // add ke Set sesuai dengan urutan p1.x < p2.x 
                        line = new Point[]{P.get(i), P.get(j)};
                        result.add(line);
                    }
                }
            }
        } else {
            // Kasus untuk 2 titik, 2 titik tersebut adalah pembentuk convex hull
            line = new Point[]{P.get(0), P.get(1)};
            result.add(line);
        }
        return result;
    }

    static int getRandomInteger(int min, int max) {
        return (int) (Math.random() * ((max - min) + 1)) + min;
    }

    static Set<Point> ConvertSetTupleToSet(Set<Point[]> SetTupleOfLine) {
        Set<Point> result = new HashSet<Point>();
        for(Point[] A : SetTupleOfLine) { //Iterate Set
            for (int it = 0; it < A.length; it++) { //Iterate Array
                result.add(A[it]);
            }
        }
        return result;
    }
}