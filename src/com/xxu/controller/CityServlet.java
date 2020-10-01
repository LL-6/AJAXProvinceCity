package com.xxu.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xxu.dao.CityDao;
import com.xxu.entity.City;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class CityServlet  extends HttpServlet {

    String json="{}";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pid=req.getParameter("pid");
        if (pid !=null && !"".equals(pid)){
            CityDao cityDao = new CityDao();
            List<City> cities = cityDao.SelectCity(Integer.valueOf(pid));
            ObjectMapper objectMapper = new ObjectMapper();
            json = objectMapper.writeValueAsString(cities);


        }
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.print(json);
        writer.flush();
        writer.close();

    }
}
