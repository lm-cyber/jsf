package com.example.jsf;


import com.example.jsf.beans.AreaCheker;
import com.example.jsf.beans.AttemptsManager;
import com.example.jsf.beans.Point;
import com.example.jsf.beans.PointAttempt;
import com.google.gson.Gson;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet(urlPatterns = "/sendCanvas")
public class SendCanvasDataServlet extends HttpServlet {

    @Inject
    AttemptsManager am;
    @Inject
    AreaCheker areaCheker;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        double x,y,r;

        x = Double.parseDouble(req.getParameter("xpoint"));
        y = Double.parseDouble(req.getParameter("ypoint"));
        r = Double.parseDouble(req.getParameter("rpoint"));
        Point point = new Point(x,y,r);
        final long start = System.nanoTime();
        final boolean res = areaCheker.cheakPoint(point);
        System.out.println(x);
        System.out.println(y);
        System.out.println(r);
        PointAttempt attempt = new PointAttempt();
        attempt.setPoint(point);
        attempt.setSuccess(res);
        attempt.setAttemptTime(System.currentTimeMillis());
        attempt.setProcessTime((System.nanoTime() - start) / 1000d);

        am.addAttempt(attempt);
        Gson g = new Gson();
        resp.getWriter().println(g.toJson(am.getAttempts()));


    }
}
