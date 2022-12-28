package com.SafariApp.backend;

import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseConnection {
    public static List<Guide> getGuides() {
        List<Guide> list = new ArrayList();
        try {


            String myUrl = "jdbc:mysql://mysql.agh.edu.pl:3306/jslaby?useSSL=false";
            Connection conn = DriverManager.getConnection(myUrl, "jslaby", "FenddnhA7zhry6X2");

            String sql = "select name,surname from Guide";

            PreparedStatement p = conn.prepareStatement(sql);
            ResultSet rs = p.executeQuery();

            // Condition check
            while (rs.next()) {
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                Guide guide = new Guide(name,surname);
                list.add(guide);
            }

        }
        catch (SQLException e){
            throw new RuntimeException();

        }

        return list;
    }

//    public static String getSafari() {
//        List<Event> listType = new ArrayList();
//        try {
//            // create a mysql database connection
//            String myUrl = "jdbc:mysql://mysql.agh.edu.pl:3306/jslaby?useSSL=false";
//            Connection conn = DriverManager.getConnection(myUrl, "jslaby", "FenddnhA7zhry6X2");
//
//            String query = " SELECT type FROM Safari";
//
//            PreparedStatement p = conn.prepareStatement(query);
//            ResultSet rs = p.executeQuery();
//
//            // Condition check
//            while (rs.next()) {
//                String type = rs.getString("type");
//                Event event = new Event(type);
//            }
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return null;
//    }

        public void addToDB(Event event) {
        try
        {
            // create a mysql database connection
            String myUrl = "jdbc:mysql://mysql.agh.edu.pl:3306/jslaby?useSSL=false";
            Connection conn = DriverManager.getConnection(myUrl, "jslaby", "FenddnhA7zhry6X2");

            // the mysql insert statement
            String query = "INSERT INTO (price,type,date,amount) "
                    + "VALUES(?,?,?,?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, event.getSafariType());
            preparedStmt.setString(2, event.getLocation());
            preparedStmt.setString(3, event.getDate());
            preparedStmt.setString(4, event.getDescription());

            // execute the preparedstatement
            preparedStmt.execute();
            conn.close();
        }
        catch (Exception e)
        {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
    }

    public List<Event> getEvents(){
        List<Event> list = new ArrayList();

        try {
            // create a mysql database connection
            String myUrl = "jdbc:mysql://mysql.agh.edu.pl:3306/jslaby?useSSL=false";
            Connection conn = DriverManager.getConnection(myUrl, "jslaby", "FenddnhA7zhry6X2");

            // SQL command data stored in String datatype
            String sql = "select * from event";
            PreparedStatement p = conn.prepareStatement(sql);
            ResultSet rs = p.executeQuery();

            // Condition check
            while (rs.next()) {
                String title = rs.getString("price");
                String date = rs.getString("type");
                String location = rs.getString("date");
                String description = rs.getString("description");
                Event event = new Event(title,date,location,description);

                if (event.isActual()) {
                    list.add(event);
                }
            }
        }
        catch (Exception e){
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
        return list;
    }
}
