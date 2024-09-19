package db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.lang.Class.forName;

public class MyConnection {
   public static Connection connection=null;
   public static Connection getConnection()
   {
       try {
           Class.forName("com.mysql.cj.jdbc.Driver");
       } catch (ClassNotFoundException e) {
           throw new RuntimeException(e);
       }
       try {
           connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/Java_Project?useSSL=false","root","mahi@12");
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
       System.out.println("hogya connection");
    return connection;
   }
   public static void closeConnection(){
       if(connection!=null){
           try{
               connection.close();
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }
   }
}
