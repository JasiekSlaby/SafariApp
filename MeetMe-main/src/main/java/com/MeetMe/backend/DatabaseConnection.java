package com.MeetMe.backend;

import org.springframework.stereotype.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseConnection {

    public void addToDB(Event event) {
        try
        {
            // create a mysql database connection
            String myUrl = "jdbc:mysql://localhost:3306/MeetMe?useSSL=false";
            Connection conn = DriverManager.getConnection(myUrl, "root", "qwerty123");

            // the mysql insert statement
            String query = "INSERT INTO event(title,date,location,description) "
                    + "VALUES(?,?,?,?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, event.getTitle());
            preparedStmt.setString(2, event.getDate());
            preparedStmt.setString(3, event.getLocation());
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
            String myUrl = "jdbc:mysql://localhost:3306/MeetMe?useSSL=false";
            Connection conn = DriverManager.getConnection(myUrl, "root", "qwerty123");

            // SQL command data stored in String datatype
            String sql = "select * from event";
            PreparedStatement p = conn.prepareStatement(sql);
            ResultSet rs = p.executeQuery();

            // Condition check
            while (rs.next()) {
                String title = rs.getString("title");
                String date = rs.getString("date");
                String location = rs.getString("location");
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
