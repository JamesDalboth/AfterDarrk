package com.impulse.afterdarrk.Utils;

public final class PolarCoords {
    private final double radius;
    private final double theta;

    public PolarCoords(double radius, double theta) {
        this.radius = radius;
        this.theta = theta;
    }

    public double getRadius() {
        return radius;
    }

    public double getTheta() {
        return theta;
    }

    @Override
    public String toString() {
        return "Radius, Theta : " + "(" + radius + ", " + theta + ")";
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    public PolarCoords deltaRadius(double delta) {
        return new PolarCoords(radius + delta, theta);
    }

    public CartesianCoords toCartesian(CartesianCoords origin) {
        double x = radius * Math.cos(theta);
        double y = radius * Math.sin(theta);
        return new CartesianCoords(x, y).addOff(origin);
    }
}
