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

    public PolarCoords toPolar() {
        return toPolar(new CartesianCoords(0, 0));
    }

    public PolarCoords toPolar(CartesianCoords origin) {
        CartesianCoords offset = subOff(origin);
        double radius = Math.sqrt(Math.pow(offset.getX(), 2) + Math.pow(offset.getY(), 2));
        double theta = Math.atan2(offset.getY(), offset.getX());
        return new PolarCoords(radius, theta);
    }

    public CartesianCoords addOff(CartesianCoords cartesianCoords) {
        return new CartesianCoords(x + cartesianCoords.getX(), y + cartesianCoords.getY());
    }

    public CartesianCoords subOff(CartesianCoords cartesianCoords) {
        return new CartesianCoords(x - cartesianCoords.getX(), y - cartesianCoords.getY());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof CartesianCoords)) {
            return false;
        }

        CartesianCoords other = (CartesianCoords) obj;

        double xOffset = Math.abs(other.x - this.x);
        double yOffset = Math.abs(other.y - this.y);

        return xOffset < 0.005 && yOffset < 0.005;
    }

    @Override
    public String toString() {
        return "(X, Y) : " + "(" + x + ", " + y + ")";
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }
}
