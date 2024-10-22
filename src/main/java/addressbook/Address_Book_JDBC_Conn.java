package addressbook;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class Address_Book_JDBC_Conn {

    private static final String URL =
            "jdbc:mysql://localhost:3306/address_book_01";

    private static final String USER="root";
  private static final String PASSWORD="Root@1234";

  public static Connection getConnection() {
      Connection connection=null;
      try{

          connection= DriverManager.getConnection(URL,USER,PASSWORD);
          System.out.println("connected to database");
      }catch (SQLException e) {
          System.out.println("error connecting to database:- "+e.getMessage());
      }
      return connection;
  }
}
