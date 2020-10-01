package com.xxu.dao;

import com.xxu.entity.City;
import com.xxu.entity.Province;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CityDao {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    public List<City> SelectCity(Integer id) {
        List<City> cities = new ArrayList<>();
        try {
            City city = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/ajax?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai";
            String root = "root";
            String password = "123456789";
            conn = DriverManager.getConnection(url, root, password);
            String sql = "select id,name from city where provinceid = ?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1,id);
            rs =  ps.executeQuery();
            while (rs.next()){
                city = new City();
                city.setId(rs.getInt("id"));
                city.setName(rs.getString("name"));

                cities.add(city);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null){
                    conn.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }


        return cities;
    }
}
