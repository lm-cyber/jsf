package com.example.jsf;


import com.example.jsf.beans.Point;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet(urlPatterns = "/sendCanvas")
public class SendCanvasDataServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        double x,y,r;

        x = Double.parseDouble(req.getParameter("xpoint"));
        y = Double.parseDouble(req.getParameter("ypoint"));
        r = Double.parseDouble(req.getParameter("rpoint"));
        Point point = new Point(x,y,r);
        System.out.println(x);

        point.submit();

        resp.getWriter().println("success");
    }
}
