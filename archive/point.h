/* File: point.h */
/* *** Definisi ABSTRACT DATA TYPE point *** */

typedef struct point {
	int X; /* absis   */
	int Y; /* ordinat */
    bool operator < (const point& rhs) const {return X < rhs.X; }
    bool operator > (const point& rhs) const {return Y > rhs.Y; }
} point;

/* *** Notasi Akses: Selektor point *** */
#define Absis(P) (P).X
#define Ordinat(P) (P).Y

point MakePoint (int X, int Y);
/* Membentuk sebuah point dari komponen-komponennya */

void MakeRandomPoint (point * P);
/* Membaca nilai absis dan ordinat dari keyboard dan membentuk
   point P berdasarkan dari nilai absis dan ordinat tersebut */
/* Komponen X dan Y dibaca dalam 1 baris, dipisahkan 1 buah spasi */
/* Contoh: 1 2
   akan membentuk point <1,2> */
/* I.S. Sembarang */
/* F.S. P terdefinisi */

bool EQ (point P1, point P2);
/* Mengirimkan true jika P1 = P2 : absis dan ordinatnya sama */