package addressbook;
import java.sql.*;
import  java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;


public class AddressBook {
    public ArrayList<Contact> contacts;
     Scanner scanner=new Scanner(System.in);
    public AddressBook(){
        contacts=new ArrayList<>();
    }

    public void  add_contact(Contact contact){
        contacts.add(contact);
    }

    // method to find a contact by first name
    public Contact find_contact_by_first_name(String first_name){
        for(Contact contact:contacts){
            if(contact.get_first_name().equalsIgnoreCase(first_name)){
                return contact;
            }
        }
        return null;
    }

    // method to edit contact return by find_cntact_by_first_name

    public boolean edit_contact(int edit_id){
        Connection connection=Address_Book_JDBC_Conn.getConnection();
        String query="select * from contacts where id="+edit_id;
        Statement statement;
        ResultSet resultSet;

        try{
             statement=connection.createStatement();
            resultSet=statement.executeQuery(query);
            if(resultSet.next()){

                return edit_contact_DB(resultSet,edit_id,connection);
            }else{
                return false;
            }
        } catch(SQLException e){
            System.out.println("error whiile executing the query :- "+e.getMessage()+" "+e.getSQLState()+" "+e.getStackTrace());
        }
        return false;
    }
  public boolean edit_contact_DB
          (ResultSet resultSet,int id,Connection connection){
      System.out.println("Editing contact for ID no" + id);
      System.out.println("Choose detail to edit:");
      System.out.println("1. First Name");
      System.out.println("2. Last Name");
      System.out.println("3. Address");
      System.out.println("4. City");
      System.out.println("5. State");
      System.out.println("6. ZIP Code");
      System.out.println("7. Phone Number");
      System.out.println("8. Email");
      System.out.println("9. Exit");

      int edit_choice=scanner.nextInt();
      scanner.nextLine();
      String update_query=null;

      try{
          switch (edit_choice){
              case 1:
                  System.out.println("Enter the first name");
                  String new_first_name=scanner.nextLine();
                  update_query="update contacts set first_name= ? where id= ?";
                  try(PreparedStatement preparedStatement=connection.prepareStatement(update_query)){

                      preparedStatement.setString(1,new_first_name);
                      preparedStatement.setInt(2,id);
                      preparedStatement.executeUpdate();
                      System.out.println("name updated successfully");
                      break;
                  }catch (SQLException e){
                      System.out.println("error in updating first name :- "+e.getMessage()+" and error code is :"+e.getErrorCode());
                  }
                  break;

              case 2:
                  System.out.println("Enter the last name to update ");
                  String new_last_name=scanner.nextLine();
                  update_query="update contacts set last_name= ? where id= ?";
                  try(PreparedStatement preparedStatement=connection.prepareStatement(update_query)){
                      preparedStatement.setString(1,new_last_name);
                      preparedStatement.setInt(2,id);
                      preparedStatement.executeUpdate();
                      System.out.println("last name updated successfully");
                      break;
                  }catch (SQLException e){
                      System.out.println("error in updating last name :- "+e.getMessage());
                  }
                  break;

              case 3:
                  System.out.println("Enter the address to update");
                  String new_address=scanner.nextLine();
                  update_query="update contacts set address= ? where id= ?";
                  try(PreparedStatement preparedStatement=connection.prepareStatement(update_query)){
                      preparedStatement.setString(1,new_address);
                      preparedStatement.setInt(2,id);
                      preparedStatement.executeUpdate();
                      System.out.println("address updated successfully");
                      break;
                  }catch (SQLException e){
                      System.out.println("error in updating address :- "+e.getMessage());
                  }
                  break;
              case 4:
                  System.out.println("Enter the city to update");
                  String new_city=scanner.nextLine();
                  update_query="update contacts set city= ? where id= ?";
                  try(PreparedStatement preparedStatement=connection.prepareStatement(update_query)){
                      preparedStatement.setString(1,new_city);
                      preparedStatement.setInt(2,id);
                      preparedStatement.executeUpdate();
                      System.out.println("city updated successfully");
                      break;
                  } catch (SQLException e){
                      System.out.println("error in updating city :- "+e.getMessage());
                  }
                  break;

              case 5:
                  System.out.println("Enter the state to update");
                  String new_state=scanner.nextLine();
                  update_query="update contacts set state= ? where id= ?";
                  try(PreparedStatement preparedStatement=connection.prepareStatement(update_query)){
                      preparedStatement.setString(1,new_state);
                      preparedStatement.setInt(2,id);
                      preparedStatement.executeUpdate();
                      System.out.println("state updated successfully");
                      break;
                  }catch (SQLException e){
                      System.out.println("error in updating state :- "+e.getMessage());
                  }
                  break;
              case 6:
                  System.out.println("Enter Zip code to update :-");
                  String new_zip=scanner.nextLine();
                  update_query="update contacts set zip=? where id=?";
                  try(PreparedStatement preparedStatement = connection.prepareStatement(update_query)){
                      preparedStatement.setString(1,new_zip);
                      preparedStatement.setInt(2,id);
                      preparedStatement.executeUpdate();
                      System.out.println("zip updated successfully");
                      break;
                  }catch (SQLException e){
                      System.out.println("Error in updating zip code :- "+e.getMessage());
                  }

              case 7:
                  System.out.println("Enter phone number to update :-");
                  String new_phone=scanner.nextLine();
                  update_query="update contacts set phone= ? where id= ?";
                  try(PreparedStatement preparedStatement=connection.prepareStatement(update_query)){
                      preparedStatement.setString(1,new_phone);
                      preparedStatement.setInt(2,id);
                      preparedStatement.executeUpdate();
                      System.out.println("phone number updated successfully");
                      break;
                  }catch (SQLException e){
                      System.out.println("Error in updating phone number :- "+e.getMessage());
                  }
                  break;
              case 8:
                  System.out.println("Enter email to update :-");
                  String new_email=scanner.nextLine();
                  update_query="update contacts set email= ? where id= ?";
                  try(PreparedStatement preparedStatement=connection.prepareStatement(update_query)){
                      preparedStatement.setString(1,new_email);
                      preparedStatement.setInt(2,id);
                      preparedStatement.executeUpdate();
                      System.out.println("email updated successfully");
                      break;
                  }catch (SQLException e){
                      System.out.println("Error in updating email :- "+e.getMessage());
                  }
                  break;
          }
      } catch (Exception e){
          System.out.println("error in try and switch statement:- "+e.getMessage());
      }
        return false;
  }
    // methid to delete contact
    public boolean delete_contact(int id_to_delete){

        Connection connection=Address_Book_JDBC_Conn.getConnection();
        String query="select * from contacts where id="+id_to_delete;
        Statement statement=null;
        ResultSet resultSet=null;
        try{
            statement=connection.createStatement();
            resultSet=statement.executeQuery(query);
            if(resultSet.next()){
                System.out.println("You are deleting the contact with id "+id_to_delete+" " +
                        "and name:-"+resultSet.getString("first_name")+" "
                        +resultSet.getString("last_name"));
                System.out.println("enter 1 to proceed or enter 0 to cancel: ");
                int user_dec=scanner.nextInt();
                if(user_dec==1){
                    String query_to_delete="delete from contacts where id=?";
                    try(PreparedStatement preparedStatement=connection.prepareStatement(query_to_delete)){
                        preparedStatement.setInt(1,id_to_delete);
                        preparedStatement.executeUpdate();
                        System.out.println("contact deleted successfully");
                        return true;
                    }catch (SQLException e){
                        System.out.println("Error in deleting contact :- "+e.getMessage());
                    }
                }else{
                    System.out.println("user canceled the deletation operation");
                    return false;
                }
            }else{
                System.out.println("Contact not found with ID :- "+id_to_delete);
            }

        }catch(SQLException e){
            System.out.println("error in finding the contact to delete "+e.getMessage());
        }
        return false; // contact not found
    }


    public void display_contact(){
        Connection connection=Address_Book_JDBC_Conn.getConnection();
        Statement statement=null;
        ResultSet resultSet=null;

        String query="select * from contacts";
        try{
            statement=connection.createStatement();
            resultSet=statement.executeQuery(query);

            boolean hashContacts=false;
            // print table header
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("| %-15s | %-15s | %-20s | %-10s | %-10s | %-6s | %-15s | %-30s |\n",
                    "First Name", "Last Name", "Address", "City", "State", "ZIP", "Phone", "Email");
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");

            while(resultSet.next()){
                hashContacts=true;

                System.out.printf("| %-15s | %-15s | %-20s | %-10s | %-10s | %-6d | %-15d | %-30s |\n",
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("address"),
                        resultSet.getString("city"),
                        resultSet.getString("state"),
                        resultSet.getInt("zip"),
                        resultSet.getLong("phone_number"),
                        resultSet.getString("email"));
            }
            if(!hashContacts){
                System.out.println("Contact not found or DB is empty");
            }
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------");


        }catch(SQLException e){
            System.out.println("error in display conatct method :- "+e.getMessage());
        }finally {
            try {
                if(resultSet!=null) resultSet.close();
                if (statement !=null) statement.close();
                if(connection != null) connection.close();
            }catch (SQLException e){
                System.out.println("error in display finally block closing all resources method :- "+e.getMessage());
            }
        }
    }

    public void add_contact_to_database(Contact contact){
        Connection connection=Address_Book_JDBC_Conn.getConnection();
        if(connection!=null){
            String query="INSERT INTO contacts ( first_name,last_name,address,city,state,zip,phone_number,email) VALUES (?,?,?,?,?,?,?,?)";

            try{
                PreparedStatement preparedStatement=connection.prepareStatement(query);
               preparedStatement.setString(1,contact.get_first_name());
                preparedStatement.setString(2, contact.get_last_name());
                preparedStatement.setString(3, contact.get_address());
                preparedStatement.setString(4, contact.getCity());
                preparedStatement.setString(5, contact.getState());
                preparedStatement.setInt(6, contact.getZip());
                preparedStatement.setLong(7, contact.getPhoneNumber());
                preparedStatement.setString(8, contact.getEmail());

                int row_inserted=preparedStatement.executeUpdate();
                if(row_inserted>0){
                    System.out.println("contact addedd successfully");
                }
            }catch (SQLException e){
                System.out.println("error adding contact:- "+e.getMessage());
            }

        }


    }

}

