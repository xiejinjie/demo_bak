package com.demo;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.*;

/**
 * 存储过程
 * @author jjxiek
 * @since 2019/12/19 11:20
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Procedure {
    private String driver = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/demo?useSSL=false"
        + "&noAccessToProcedureBodies=true";
    private String user = "root";
    private String password = "123456";
    private Connection con;

    @BeforeAll
    public void init() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        con = DriverManager.getConnection(url, user, password);
    }

    /**
     * 存储过程基本调用
     * @throws SQLException
     */
    @Test
    public void fun1() throws SQLException {
        CallableStatement cs = con.prepareCall("{call fun1(?,?)}");
        cs.setInt(1,1);
        cs.registerOutParameter(2, Types.VARCHAR);
        ResultSet rs = cs.executeQuery();
        String s = cs.getString(2);
        System.out.println(s);
    }

    /**
     * 存储过程返回结果集
     * @throws SQLException
     */
    @Test
    public void fun2() throws SQLException {
        CallableStatement cs = con.prepareCall("{call fun2()}");
        ResultSet rs = cs.executeQuery();
        while (rs.next()){
            System.out.print(rs.getInt(1) + " ");
            System.out.print(rs.getString(2) + " ");
            System.out.println(rs.getInt(3));
        }

    }



    @Test
    public void testCon() throws SQLException {
        Statement statement  = con.createStatement();
        ResultSet rs = statement.executeQuery("select 'ok' from dual");
        rs.next();
        String a = rs.getString(1);
        System.out.println(a);
        rs.close();
        statement.close();
    }
    @AfterAll
    public void destory() throws SQLException {
        con.close();
    }
}
