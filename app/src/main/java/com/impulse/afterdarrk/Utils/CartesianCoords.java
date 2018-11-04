package com.impulse.afterdarrk.Utils;

public final class CartesianCoords {
    private final double x;
    private final double y;

    public CartesianCoords(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return "(X,Y) : " + "(" + x + "," + y + ")";
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    public PolarCoords toPolar() {
        double radius = Math.sqrt(Math.pow(x,2) + Math.pow(y,2));
        double theta = Math.atan2(y,x);
        return new PolarCoords(radius, theta);
    }

    public CartesianCoords addOff(CartesianCoords cartesianCoords) {
        return new CartesianCoords(x + cartesianCoords.getX(), y + cartesianCoords.getY());
    }

    public CartesianCoords subOff(CartesianCoords cartesianCoords) {
        return new CartesianCoords(x - cartesianCoords.getX(), y - cartesianCoords.getY());
    }
}
