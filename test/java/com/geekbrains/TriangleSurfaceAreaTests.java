package com.geekbrains;

import com.geekbrains.lesson4.TriangleSurfaceArea;

import org.junit.jupiter.api.*;



public class TriangleSurfaceAreaTests {

    @Test
    void successfullCalcTest() throws Exception {
        Assertions.assertEquals(TriangleSurfaceArea.calculateGeroneSquare(1, 2, 2), 2);
    }

    @Test
    void isTriangleDegenerate() {
        try {
            double geroneCalculationResult = TriangleSurfaceArea.calculateGeroneSquare(1, 1, 2);
            Assertions.fail("The triangle is valid");
        } catch (IllegalArgumentException e) {
            Assertions.assertEquals("Triangle is degenerate", e.getMessage());
        }
    }

    @Test
    void isTriangleExists() throws Exception {
        try {
            double geroneCalculationResult = TriangleSurfaceArea.calculateGeroneSquare(6, 6, 9);
        } catch (IllegalArgumentException e) {
            Assertions.assertEquals("Triangle can not exist", e.getMessage());
        }

    }
}