/* File: point.c */
/* Body ADT point */

#include <iostream>
#include <cstdlib>
#include "point.h"

point MakePoint (int X, int Y)
/* Membentuk sebuah point dari komponen-komponennya */
{
    // Kamus Lokal
    point P;
    // Algoritma
    Absis(P) = X;
    Ordinat(P) = Y;
    return P;
}


void MakeRandomPoint (point * P)
/* Membaca nilai absis dan ordinat dari keyboard dan membentuk
   point P berdasarkan dari nilai absis dan ordinat tersebut */
/* Komponen X dan Y dibaca dalam 1 baris, dipisahkan 1 buah spasi */
/* Contoh: 1 2
   akan membentuk point <1,2> */
/* I.S. Sembarang */
/* F.S. P terdefinisi */
{
    // Algoritma
    int X, Y;
    X = rand() % 2001 - 1000; // -1000 <= X <= 1000
    Y = rand() % 2001 - 1000; // -1000 <= Y <= 1000
    *P = MakePoint(X, Y);
}

bool EQ (point P1, point P2)
/* Mengirimkan true jika P1 = P2 : absis dan ordinatnya sama */
{
    return ((Absis(P1) == Absis(P2)) && (Ordinat(P1) == Ordinat(P2)));
}