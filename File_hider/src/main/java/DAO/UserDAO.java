package DAO;

import db.MyConnection;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    public static boolean isExists(String email) throws SQLException {
        Connection connection = MyConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement("select email from users");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String e = rs.getString(1);
            if (email.equals(e)) {
                return true;
            }
        }
        return false;

    }
    // if the above function returns false then what to do
    public static int saveUser(User user) throws SQLException {
        Connection connection = MyConnection.getConnection();
        PreparedStatement ps=connection.prepareStatement("insert into users(id,name,email) values(default,?,?)");
        ps.setString(1,user.getName());
        ps.setString(2,user.getEmail());
         return ps.executeUpdate();
    }
}
