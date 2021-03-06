package contactManager;

import contactDataStructure.MyLinkedList;
import inputManager.Input;
import personDefinition.Person;

import java.util.Scanner;

public class ContactManager {
    Scanner scanner = new Scanner(System.in);
    MyLinkedList<Long> contactNumberList = null;
    Input input = new Input();
    Person contact = null;

    public Person addContact() {
        System.out.print("You have chosen to add a new contact: \n" +
                "Please enter the name of the Person\n" +
                "First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Last Name : ");
        String lastName = scanner.nextLine();
        // contactNumberList
        contactNumberList = new MyLinkedList();
        char choice = 'y';
        long contactNumber;
        while (choice == 'y') {
            System.out.print("Contact Number :");
            contactNumber = Long.parseLong(scanner.nextLine());
            if (input.contactNumber(contactNumber)) {
                contactNumberList.insert(contactNumber);
            } else {
                System.out.println("Invalid Contact Number");
                continue;
            }

            System.out.print("Would you like to add another contact number? (y/n): ");
            choice = (scanner.nextLine()).charAt(0);
        }
        System.out.print("Would you like to add email address? (y/n): ");
        char ch = scanner.nextLine().charAt(0);
        String emailAddress = "";
        if (ch == 'y') {
            System.out.print("Email Address: ");
            emailAddress = scanner.nextLine();
        }
        while (ch == 'y') {
            if (input.emailAddress(emailAddress)) {
                contact = new Person(firstName, lastName, contactNumberList, emailAddress);
                ch = 'n';
            } else {
                System.out.println("Invalid Email address");
                System.out.print("Email Address: ");
                emailAddress = scanner.nextLine();
            }
        }
        if (ch == 'n') {
            contact = new Person(firstName, lastName, contactNumberList, "");
        }
        return contact;
    }

    public void viewContact(MyLinkedList person) {
        System.out.println("---Here are all your contacts---");
        int size = person.getSize();
        for (int i = 0; i < size; i++) {
            Person contact = (Person) person.dataAtIndex(i);
            printContact(contact);
        }
    }

    private void printContact(Person contact) {
        System.out.print("-------- * -------- * -------- * --------\n" +
                "First Name: " + contact.getFirstName() + "\n" +
                "Last Name: " + contact.getLastName() + "\n");
        if (contact.getContactNumber().getSize() > 1) {
            System.out.print("Contact Number(s): ");
            for (int j = 0; j < contact.getContactNumber().getSize(); j++) {
                System.out.print(contact.getContactNumber().dataAtIndex(j) + (j < contact.getContactNumber().getSize() - 1 ? "," : ""));
            }
        } else {
            System.out.print("Contact Number: " + contact.getContactNumber().dataAtIndex(0));

        }
        if (contact.getEmailAddress().compareTo("") != 0) {
            System.out.print("\nEmail Address: " + contact.getEmailAddress());
        }
        System.out.println("\n-------- * -------- * -------- * --------");
    }

    public void searchContact(MyLinkedList person) {
        MyLinkedList<Person> matchedContact = new MyLinkedList();
        System.out.println("You could search for a contact from their first names:");
        String element = scanner.nextLine();
        int match = 0;
        for (int i = 0; i < person.getSize(); i++) {
            Person person1 = (Person) person.dataAtIndex(i);
            if (element.equals(person1.getFirstName())) {
                match++;
                matchedContact.insert(person1);
            }
        }
        if (match > 0) {
            System.out.println(match + " match found!");
            for (int i = 0; i < matchedContact.getSize(); i++) {
                Person name = matchedContact.dataAtIndex(i);
                printContact(name);
            }
        } else {
            System.out.println("NO RESULTS FOUND!");
        }
    }

    public void deleteContact(MyLinkedList person) {
        System.out.println("Here are all your contacts: ");
        for (int i = 0; i < person.getSize(); i++) {
            Person person1 = (Person) person.dataAtIndex(i);
            System.out.println(i + 1 + ". " + person1.getFirstName() + " " + person1.getLastName());
        }
        System.out.print("Press the number against the contact to delete it: ");
        int choice = Integer.parseInt(scanner.nextLine());
        Person person2 = (Person) person.dataAtIndex(choice - 1);
        System.out.println(person2.getFirstName() + "'s contact deleted from list!\n");
        person.delete(choice - 1);
    }
}
