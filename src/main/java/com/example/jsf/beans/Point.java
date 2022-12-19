package com.example.jsf.beans;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@RequestScoped
@Named
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Point implements Serializable {
    @Inject
    transient private AttemptsManager am;
    @Inject
    transient private AreaCheker areaCheker;
    private double x;
    private double y;
    private double r;

    public void setValueM2(boolean xBool) {
        x = xBool ? -2D : x;
    }

    public void setValueM15(boolean xBool) {
        x = xBool ? -1.5D : x;
    }

    public void setValueM1(boolean xBool) {
        x = xBool ? -1D : x;
    }

    public void setValueM05(boolean xBool) {
        x = xBool ? -0.5D : x;
    }

    public void setValue0(boolean xBool) {
        x = xBool ? 0D : x;
    }

    public void setValue05(boolean xBool) {
        x = xBool ? 0.5D : x;
    }

    public void setValue1(boolean xBool) {
        x = xBool ? 1D : x;
    }

    public void setValue15(boolean xBool) {
        x = xBool ? 1.5D : x;
    }

    public void setValue2(boolean xBool) {
        x = xBool ? 2D : x;
    }

    public boolean getValueM2() {
        return x == -2D;
    }

    public boolean getValueM15() {
        return x == -1.5D;
    }

    public boolean getValueM1() {
        return x == -1D;
    }

    public boolean getValueM05() {
        return x == -0.5D;
    }


    public boolean getValue0() {
        return x == 0D;
    }

    public boolean getValue05() {
        return x == 0.5D;
    }

    public boolean getValue1() {
        return x == 1D;
    }

    public boolean getValue15() {
        return x == 1.5D;
    }

    public boolean getValue2() {
        return x == 2D;
    }

    @PostConstruct
    public void init() {
        this.r = 1D;
    }

    public void r1() {
        this.r = 1D;
    }

    public void r15() {
        this.r = 1.5D;
    }

    public void r2() {
        this.r = 2D;
    }

    public void r25() {
        this.r = 2.5D;
    }

    public void r3() {
        this.r = 3D;
    }


    public Point(double x, double y, double r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }



    public void submit() {
        final long start = System.nanoTime();
        final boolean res = areaCheker.cheakPoint(this);

        PointAttempt attempt = new PointAttempt();
        attempt.setPoint(this);
        attempt.setSuccess(res);
        attempt.setAttemptTime(System.currentTimeMillis());
        attempt.setProcessTime((System.nanoTime() - start) / 1000d);

        am.addAttempt(attempt);
    }
}
