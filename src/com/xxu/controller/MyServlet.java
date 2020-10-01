package com.xxu.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xxu.dao.Query;
import com.xxu.entity.Province;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class MyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String json = "{}";
        Query dao = new Query();
        List<Province> provinces = dao.SelectAll();

        if (provinces != null){
            //实现list--》json
            ObjectMapper om = new ObjectMapper();
             json = om.writeValueAsString(provinces);

        }
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.print(json);
        writer.flush();
        writer.close();
    }
}
