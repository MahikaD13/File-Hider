package DAO;

import db.MyConnection;
import model.data;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataDAO {
    public static List<data> findallfiles(String email) throws SQLException {
        Connection connection= MyConnection.getConnection();
        PreparedStatement ps=connection.prepareStatement("select * from data where email=?");
        ps.setString(1, email);
        ResultSet rs=ps.executeQuery();
        List<data> list=new ArrayList<>();
        while(rs.next()){
            int id=rs.getInt("id");
            String name=rs.getString("name");
            String path=rs.getString("path");
            list.add(new data(id,name,path));
        }
        return list;
    }
    public static int hidefile(data file) throws SQLException, IOException {
        Connection connection= MyConnection.getConnection();
        PreparedStatement ps=connection.prepareStatement("insert into data(name,path,email,bin_data) values(?,?,?,?)");
        ps.setString(1, file.getFilename());
        ps.setString(2, file.getPath());
        ps.setString(3, file.getEmail());
        File f=new File(file.getPath());
        FileReader fr=new FileReader(f);
        ps.setCharacterStream(4, fr,f.length());//data saved in form of characters
        int ans=ps.executeUpdate();
        fr.close();
        f.delete();
        return ans;




    }
    public static void unhidefile(int id)throws SQLException,IOException{
        Connection connection= MyConnection.getConnection();
        PreparedStatement ps=connection.prepareStatement("select path,bin_data from data where id=?");
        ps.setInt(1, id);
        ResultSet rs=ps.executeQuery();
        rs.next();
        String path=rs.getString("path");
        Clob c=rs.getClob("bin_data");
        Reader r=c.getCharacterStream();
        FileWriter fw=new FileWriter(path);
        int i;//ascii value of file is received for each character
        while((i=r.read())!=-1){
            fw.write((char)(i));
        }
        fw.close();
        ps=connection.prepareStatement("delete from data where id=?");
        ps.setInt(1, id);
        ps.executeUpdate();
        System.out.println("File unhidden successfully");

    }
}
