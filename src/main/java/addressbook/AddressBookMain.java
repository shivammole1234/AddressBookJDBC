package addressbook;
import java.util.Scanner;
public class AddressBookMain {

    public static void main(String[] args) {

        AddressBook addressBook=new AddressBook();
        Scanner scanner = new Scanner(System.in);

        addressBook.add_contact(new Contact("John", "Doe", "123 Elm Street", "Springfield", "IL", 62701, 5551234, "john.doe@example.com"));
        addressBook.add_contact(new Contact("Jane", "Smith", "456 Oak Avenue", "Springfield", "IL", 62701, 5555678, "jane.smith@example.com"));


        while(true){

            System.out.println("Choose an option:");
            System.out.println("1. Add New Contact");
            System.out.println("2. Edit Contact");
            System.out.println("3. Delete Contact");
            System.out.println("4. Display All Contacts");
            System.out.println("5. Exit");
            System.out.println("Enter the choice:- ");
            int choise = scanner.nextInt();
            scanner.nextLine();
            switch (choise){
                case 1:
                    // add new contact
                    System.out.print("First Name: ");
                    String firstName = scanner.nextLine();
                    System.out.print("Last Name: ");
                    String lastName = scanner.nextLine();
                    System.out.print("Address: ");
                    String address = scanner.nextLine();
                    System.out.print("City: ");
                    String city = scanner.nextLine();
                    System.out.print("State: ");
                    String state = scanner.nextLine();
                    System.out.print("ZIP Code: ");
                    int zip = scanner.nextInt();
                    System.out.print("Phone Number: ");
                    long phoneNumber = scanner.nextLong();
                    scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();

                    Contact newContact = new Contact(firstName, lastName, address, city, state, zip, phoneNumber, email);
                    addressBook.add_contact(newContact);
                    addressBook.add_contact_to_database(newContact);
                    System.out.println("contact added successfully!");
                    break;

                case 2:
                    // edit contact
                    System.out.println("emter the ID to search contact for edit");
                    int edit_id=scanner.nextInt();

                    boolean is_edited= addressBook.edit_contact(edit_id);
                    if(is_edited)
                        System.out.println("contact updated");
                    else
                        System.out.println("contact not found");

                    break;

                case 3:
                    // delete contact
                    System.out.println("Enter the ID to delete : ");
                    int id_to_delete = scanner.nextInt();

                    boolean is_deleted = addressBook.delete_contact(id_to_delete);
                    if(is_deleted){
                        System.out.println("contact deleted successfully in switch case msg");
                    }else {
                        System.out.println("contact not found");
                    }
                    break;

                case 4:
                    // display all contact

                    System.out.println("All Contact");
                    addressBook.display_contact();
                    break;

                case 5:
                    // exit the program
                    System.out.println("exiting the program");
                    scanner.close();
                    return; // this statement exit the main method

                default:
                    System.out.println("invalid coise.please select a valid option");
                    break;
            }
        }
    }
}
