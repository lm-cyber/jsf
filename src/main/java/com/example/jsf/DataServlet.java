package com.example.jsf;

import com.google.gson.Gson;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.example.jsf.beans.AttemptsManager;

import java.io.IOException;

@WebServlet(urlPatterns = "/attempts")
public class DataServlet extends HttpServlet {
    @Inject
    private AttemptsManager am;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson g = new Gson();
        System.out.println(am.getAttempts().toString());
        resp.getWriter().println(g.toJson(am.getAttempts()));
    }
}
