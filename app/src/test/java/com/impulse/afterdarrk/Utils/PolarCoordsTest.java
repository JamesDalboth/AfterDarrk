package com.impulse.afterdarrk.Utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class PolarCoordsTest {

    @Test
    public void toStringIsRightFormat() {
        PolarCoords polarCoords = new PolarCoords(15, 10);

        String expected = "Radius, Theta : (15.0, 10.0)";

        assertEquals(expected, polarCoords.toString());
    }

    @Test
    public void deltaRadiusWorks() {
        PolarCoords polarCoords = new PolarCoords(15, 10);
        polarCoords = polarCoords.deltaRadius(-1);

        assertEquals(14, polarCoords.getRadius(), 0);
        assertEquals(10, polarCoords.getTheta(), 0);
    }

    @Test
    public void toCartesianWorks() {
        PolarCoords polarCoords = new PolarCoords(15, 10);

        CartesianCoords expected = new CartesianCoords(-12.586, -8.16);

        assertEquals(expected, polarCoords.toCartesian());
    }

    @Test
    public void toCartesianWorksWithOffsetOrigin() {
        PolarCoords polarCoords = new PolarCoords(15, 10);

        CartesianCoords expected = new CartesianCoords(-7.586, -3.16);

        assertEquals(expected, polarCoords.toCartesian(new CartesianCoords(5, 5)));
    }
}