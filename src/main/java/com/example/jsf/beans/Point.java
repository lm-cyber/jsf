package com.example.jsf;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;

@RequestScoped
@Named
public class Point implements Serializable {
    @Inject
    transient private AttemptsManager am;
    @Inject
    transient private AreaCheker areaCheker;
    private double x;
    private double y;
    private double r;

    public Point() {}

    public Point(double x, double y, double r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public void submit() {
        final long start = System.nanoTime();
        final boolean res = areaCheker.cheakPoint(this);
        System.out.println(x);
        System.out.println(y);
        System.out.println(r);
        System.out.println(res);

        PointAttempt attempt = new PointAttempt();
        attempt.setPoint(this);
        attempt.setSuccess(res);
        attempt.setAttemptTime(System.currentTimeMillis());
        attempt.setProcessTime((System.nanoTime() - start) / 1000d);

        am.addAttempt(attempt);
    }
}
