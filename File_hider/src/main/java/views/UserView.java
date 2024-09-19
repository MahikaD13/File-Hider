package views;

import DAO.DataDAO;
import model.data;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class UserView {
    private String email;
    UserView(String email) {
        this.email = email;
    }
    public void home(){
      do{
          System.out.println("Welcome " + email);
          System.out.println("Press 1 to show hidden files");
          System.out.println("Press 2 to hide files");
          System.out.println("Press 3 to unhide files");
          System.out.println("Press 0 to exit");
          System.out.print("Enter your choice: ");
          Scanner sc=new Scanner(System.in);
          int ch=Integer.parseInt(sc.nextLine());
          switch (ch) {
              case 1:
                  try {
                      List<data> files = DataDAO.findallfiles(this.email);
                      System.out.println("ID - FileName");
                      for (data f : files) {
                          System.out.println(f.getId() + " - " + f.getFilename());
                      }
                  } catch (SQLException e) {
                      throw new RuntimeException(e);
                  }
                  break;
              case 2:
                  System.out.println("Enter file path");
                  String path = sc.nextLine();
                  File f = new File(path);
                  data file = new data(0, f.getName(), path, this.email);
                  try {
                      DataDAO.hidefile(file);
                  } catch (SQLException e) {
                      throw new RuntimeException(e);
                  } catch (IOException e) {
                      throw new RuntimeException(e);
                  }
                  break;
              case 3:
                  List<data> files = null;
                  try {
                      files = DataDAO.findallfiles(this.email);
                      System.out.println("ID - FileName");
                      for (data fi : files) {
                          System.out.println(fi.getId() + " - " + fi.getFilename());
                      }
                      System.out.println("Enter the id of file to unhide");
                      int id = Integer.parseInt(sc.nextLine());
                      boolean isvalidID = false;
                      for (data fi : files) {
                          if (fi.getId() == id) {
                              isvalidID = true;
                              break;
                          }
                      }
                      if (isvalidID) {
                          DataDAO.unhidefile(id);
                      } else {
                          System.out.println("ID not valid");
                      }
                  }      catch (SQLException e) {
                          throw new RuntimeException(e);
                      } catch (IOException e) {
                       e.printStackTrace();
                    }
                      break;
                          case 0:
                              System.exit(0);
                      }
      }while(true);


    }
}
