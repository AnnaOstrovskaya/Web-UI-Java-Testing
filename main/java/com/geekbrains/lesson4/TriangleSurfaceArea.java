package com.geekbrains.lesson4;

public class TriangleSurfaceArea {

    public static double calculateGeroneSquare(double a, double b, double c) throws IllegalArgumentException {

        if (a <= 0 || b <= 0 || c <= 0) {
            throw new IllegalArgumentException("Invalid sides value");
        }

        if ((a <= b) && (b <= c) && (a + b == c))
            throw new IllegalArgumentException("Triangle is degenerate");

        if (!((a + b > c) && (a + c > b) && (b + c > a)))
            throw new IllegalArgumentException("Triangle can not exist");

        double p = (a + b + c) / 2;
        double geroneSquare = Math.sqrt(p * (p - a) + (p - b) * (p - c));
        return geroneSquare;
        }


}
