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


    public PolarCoords deltaRadius(double delta) {
        return new PolarCoords(radius + delta, theta);
    }

    public CartesianCoords toCartesian() {
        return toCartesian(new CartesianCoords(0, 0));
    }

    public CartesianCoords toCartesian(CartesianCoords origin) {
        double x = radius * Math.cos(theta);
        double y = radius * Math.sin(theta);
        return new CartesianCoords(x, y).addOff(origin);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof PolarCoords)) {
            return false;
        }

        PolarCoords other = (PolarCoords) obj;

        double radiusDelta = Math.abs(other.radius - this.radius);
        double thetaDelta = Math.abs(other.theta - this.theta);

        return radiusDelta < 0.005 && thetaDelta < 0.005;
    }

    @Override
    public String toString() {
        return "Radius, Theta : " + "(" + radius + ", " + theta + ")";
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }
}
