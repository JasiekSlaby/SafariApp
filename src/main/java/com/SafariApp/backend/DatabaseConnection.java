package com.SafariApp.backend;

import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Component
public class DatabaseConnection {
    public static List<Guide> getGuide() {
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
                Guide guide = new Guide(name, surname);
                list.add(guide);
            }

        } catch (SQLException e) {
            throw new RuntimeException();

        }

        return list;
    }

    public static List<Safari> getSafari() {
        List<Safari> list = new ArrayList();

        try {


            String myUrl = "jdbc:mysql://mysql.agh.edu.pl:3306/jslaby?useSSL=false";
            Connection conn = DriverManager.getConnection(myUrl, "jslaby", "FenddnhA7zhry6X2");

            String sql = "select type from Safari";

            PreparedStatement p = conn.prepareStatement(sql);
            ResultSet rs = p.executeQuery();

            // Condition check
            while (rs.next()) {
                String type = rs.getString("type");
                Safari safari = new Safari(type);
                list.add(safari);
            }

        } catch (SQLException e) {
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
        try {
            // create a mysql database connection
            String myUrl = "jdbc:mysql://mysql.agh.edu.pl:3306/jslaby?useSSL=false";
            Connection conn = DriverManager.getConnection(myUrl, "jslaby", "FenddnhA7zhry6X2");

            // the mysql insert statement
            String query = "INSERT INTO Test (safariType,date,location,description) "
                    + "VALUES(?,?,?,?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, event.getSafariType());  //String.valueOf()
            preparedStmt.setString(2, event.getDate());
            preparedStmt.setString(3, event.getLocation());
            preparedStmt.setString(4, event.getDescription());

            // execute the preparedstatement
            preparedStmt.execute();
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
    }

    public void addToDBClient(Client client) {
        try {
            // create a mysql database connection
            String myUrl = "jdbc:mysql://mysql.agh.edu.pl:3306/jslaby?useSSL=false";
            Connection conn = DriverManager.getConnection(myUrl, "jslaby", "FenddnhA7zhry6X2");

            // the mysql insert statement
            String query = "INSERT INTO Client (name,surname,phone_number,email) "
                    + "VALUES(?,?,?,?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, client.getName());  //String.valueOf()
            preparedStmt.setString(2, client.getSurname());
            preparedStmt.setString(3, client.getPhoneNumber());
            preparedStmt.setString(4, client.getEmail());

            // execute the preparedstatement
            preparedStmt.execute();
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
    }

    public List<Event> getEvents() {
        List<Event> list = new ArrayList();

        try {
            // create a mysql database connection
            String myUrl = "jdbc:mysql://mysql.agh.edu.pl:3306/jslaby?useSSL=false";
            Connection conn = DriverManager.getConnection(myUrl, "jslaby", "FenddnhA7zhry6X2");

            // SQL command data stored in String datatype
            String sql = "select * from Test ";
            PreparedStatement p = conn.prepareStatement(sql);
            ResultSet rs = p.executeQuery();

            // Condition check
            while (rs.next()) {
                String safariType = rs.getString("safariType");
                String date = rs.getString("date");
                String location = rs.getString("location");
                String description = rs.getString("description");
                Event event = new Event(safariType, date, location, description);

                if (event.isActual()) {
                    list.add(event);
                }
            }
        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
        return list;
    }
}