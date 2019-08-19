package com.impulse.afterdarrk.Utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class CartesianCoordsTest {

    @Test
    public void toStringIsRightFormat() {
        CartesianCoords cartesianCoords = new CartesianCoords(15, 10);

        String expected = "(X, Y) : (15.0, 10.0)";

        assertEquals(expected, cartesianCoords.toString());
    }

    @Test
    public void addOffsetIsCorrect() {
        CartesianCoords cartesianCoords = new CartesianCoords(15, 10);
        CartesianCoords toAdd = new CartesianCoords(5, -10);

        cartesianCoords = cartesianCoords.addOff(toAdd);

        assertEquals(20.0, cartesianCoords.getX(), 0);
        assertEquals(0.0, cartesianCoords.getY(), 0);
    }

    @Test
    public void subOffsetIsCorrect() {
        CartesianCoords cartesianCoords = new CartesianCoords(15, 10);
        CartesianCoords toAdd = new CartesianCoords(5, -10);

        cartesianCoords = cartesianCoords.subOff(toAdd);

        assertEquals(10.0, cartesianCoords.getX(), 0);
        assertEquals(20.0, cartesianCoords.getY(), 0);
    }

    @Test
    public void toPolarIsCorrect() {
        CartesianCoords cartesianCoords = new CartesianCoords(15, 10);

        PolarCoords expected = new PolarCoords(18.028, 0.588);

        assertEquals(expected, cartesianCoords.toPolar());
    }

    @Test
    public void toPolarIsCorrectWithOffsetOrigin() {
        CartesianCoords cartesianCoords = new CartesianCoords(15, 10);

        PolarCoords expected = new PolarCoords(11.18, 0.46365);

        assertEquals(expected, cartesianCoords.toPolar(new CartesianCoords(5, 5)));
    }
}