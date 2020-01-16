/* Tony Eko Yuwono - 13518030 - K03 	*/
/* Tugas Kecil 1 - Strategi Algoritma	*/
/* Penyelesaian Persoalan Convex Hull dengan Algoritma Brute Force */

#include <iostream>
#include <cstdlib>
#include <vector>
#include <ctime>
#include <set>
#include <GL/gl.h>
#include <GL/glu.h>
// #include <GL/glut.h>
// #include <GL/glui.h>
#include "point.h"
using namespace std;

set<point> SearchConvexHull(vector<point> P) {
	/* Kamus Lokal */ 
	int a, b, c, i, j, k;
	bool stop, less;
	set<point> result;
	/* Algoritma */
	for (i = 0; i < P.size(); i++) {
		for (j = 0; j < P.size(); j++) {
			if (not(EQ(P[j], P[i]))) {
				// Membuat persamaan garis: ax + by = c
				a = Ordinat(P[j]) - Ordinat(P[i]);
				b = Absis(P[i]) - Absis(P[j]);
				c = Absis(P[i])*Ordinat(P[j]) - Ordinat(P[i])*Absis(P[j]);
				k = 0; stop = false;
				// Inisiasi pengecekan
				while (((EQ(P[k], P[i])) || (EQ(P[k], P[j]))) && k < P.size()) { k++; }
				if (a*Absis(P[k])+b*Ordinat(P[k]) < c) { less = true; } 
				else { less = false; }
				// Looping pengecekan 
				while (k < P.size() && !stop) {
					/* Stop saat ditemukan satu titik yang berada di sisi yang berbeda dengan titik yang lain pada garis ax+by=c */
					if (not(EQ(P[k], P[i])) && not(EQ(P[k], P[j]))) {
						if ((less && (a*Absis(P[k])+b*Ordinat(P[k]) > c)) || (!less && (a*Absis(P[k])+b*Ordinat(P[k]) < c))) {
							stop = true;
						} 
					}
					if (!stop) { k++; }
				}
				
				if (!stop) {
					// Jika !stop, semua titik berada di kiri atau di kanan garis (P[i] dan P[j] titik2 pembentuk convex hull)
					result.insert(P[i]);
					result.insert(P[j]);
				}
			}
		}	
	}
	return result;
}

void showImage(vector<point> P, set<point> result) {
	// glutInitDisplayMode(GLUT_SINGLE|GLUT_RGB);
	// glutInitWindowSize(1280,720);
	// glutInitWindowPosition(0,0);
 	// glutCreateWindow("Convex Hull - Brute Force");     	
	// glClearColor(0.392157, 0.584314, 0.929412, 0.0f);
	// glColor3f(0.0980392, 0.0980392, 0.439216);
	// glpointSize(4.0);
	// glMatrixMode(GL_PROJECTION);
	// glLoadIdentity();
	// gluOrtho2D(0,1280,0,720);
}

int main() {
	/* Kamus */
	vector<point> P;
	set<point> result;
	clock_t startTime, endTime;
	point X;
	int n;
	/* Algoritma */
	srand(time(NULL));
	printf("Masukkan banyaknya titik yang diinginkan: "); cin >> n;
	if (n > 0) {
		printf("\n%d Titik Acak:\n", n);
		for (int i = 0; i < n; i++) {
			// Absis(X) = i+1; Ordinat(X) = 0;
			MakeRandomPoint(&X);
			P.push_back(X);
			cout << "Titik ke-" << i+1 << ": " << "(" << Absis(P[i]) << "," << Ordinat(P[i]) << ")" << endl;
		}
		startTime = clock();
		result = SearchConvexHull(P);
		endTime = clock();
		cout << "\nTitik-titik Pembentuk Convex Hull:" << endl;
		for (const auto& e: result) {
			cout << "(" << Absis(e) << "," << Ordinat(e) << ")" << endl;
		}
		cout << "\nWaktu untuk menemukan Convex Hull: " << ((double) (endTime - startTime)) * 1000 / CLOCKS_PER_SEC << " ms" << endl;
		showImage(P, result);
		return 0;
	} else {
		cout << "Masukan tidak sesuai!" << endl;
	}
}

// g++ -std=c++11 main.cpp point.cpp -o main