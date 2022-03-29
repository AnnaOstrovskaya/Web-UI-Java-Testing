package com.geekbrains.lesson4;

public class TriangleSurfaceArea {

    public static void main(String[] args) {

        System.out.printf("Surface area is %.2f", geroneSquare(14, 12, 12));

    }

    public static double geroneSquare(int a, int b, int c) {
        double p = (a + b + c) / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

    public static boolean isNegativeOrZero(int number) {
        if( number <= 0){
        }
        return false;
    }

}
