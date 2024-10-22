package addressbook;
import  java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class AddressBook {
    public ArrayList<Contact> contacts;

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

    public boolean edit_contact(String first_name){
        Contact contact = find_contact_by_first_name(first_name);
        if(contact != null){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Editing contact for " + first_name);

            System.out.println("Choose detail to edit:");
            System.out.println("1. Last Name");
            System.out.println("2. Address");
            System.out.println("3. City");
            System.out.println("4. State");
            System.out.println("5. ZIP Code");
            System.out.println("6. Phone Number");
            System.out.println("7. Email");
            System.out.println("8. Exit");

            int editChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (editChoice) {
                case 1:
                    System.out.print("Enter new Last Name: ");
                    contact.setLast_name(scanner.nextLine());
                    break;
                case 2:
                    System.out.print("Enter new Address: ");
                    contact.setAddress(scanner.nextLine());
                    break;
                case 3:
                    System.out.print("Enter new City: ");
                    contact.setCity(scanner.nextLine());
                    break;
                case 4:
                    System.out.print("Enter new State: ");
                    contact.setState(scanner.nextLine());
                    break;
                case 5:
                    System.out.print("Enter new ZIP Code: ");
                    contact.setZip(scanner.nextInt());
                    scanner.nextLine(); // Consume newline
                    break;
                case 6:
                    System.out.print("Enter new Phone Number: ");
                    contact.setPhoneNumber(scanner.nextLong());
                    scanner.nextLine(); // Consume newline
                    break;
                case 7:
                    System.out.print("Enter new Email: ");
                    contact.setEmail(scanner.nextLine());
                    break;
                case 8:
                    return false; // Exit the method
                default:
                    System.out.println("Invalid choice. No changes made.");
                    break;
            }
            return true; // updated
        } else{
            return false;  // updatation failed
        }
    }

    // methid to delete contact
    public boolean delete_contact(String first_name){
        Iterator<Contact> iterator = contacts.iterator();
        while (iterator.hasNext()){
            Contact contact  = iterator.next();
            if(contact.get_first_name().equalsIgnoreCase(first_name)){
                iterator.remove();
                return true;// contact delted
            }
        }
        return false; // contact not found
    }


    public void display_contact(){
        for(Contact contact :contacts){
            System.out.println("Name: " + contact.get_first_name() + " " + contact.get_last_name());
            System.out.println("Address: " + contact.get_address());
            System.out.println("City: " + contact.getCity());
            System.out.println("State: " + contact.getState());
            System.out.println("ZIP: " + contact.getZip());
            System.out.println("Phone: " + contact.getPhoneNumber());
            System.out.println("Email: " + contact.getEmail());
            System.out.println("------------------------------");
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

