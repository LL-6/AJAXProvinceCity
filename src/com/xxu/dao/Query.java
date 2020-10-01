package com.xxu.dao;



import com.xxu.entity.Province;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Query {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    public List<Province> SelectAll() {
        List<Province> provinces = new ArrayList<>();
        try {
            Province province = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/ajax?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai";
            String root = "root";
            String password = "123456789";
            conn = DriverManager.getConnection(url, root, password);
            String sql = "select id,name,jiancheng,shenghui from province order by id";
            ps = conn.prepareStatement(sql);
            rs =  ps.executeQuery();
            while (rs.next()){
                province = new Province();
                province.setId(rs.getInt("id"));
                province.setName(rs.getString("name"));
                province.setJiancheng(rs.getString("jiancheng"));
                province.setShenghui(rs.getString("shenghui"));
                provinces.add(province);
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


        return provinces;
    }


}
