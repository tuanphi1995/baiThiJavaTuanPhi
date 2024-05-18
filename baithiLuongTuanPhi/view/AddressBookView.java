package baithiLuongTuanPhi.view;

import baithiLuongTuanPhi.model.Contact;

import java.util.Scanner;

public class AddressBookView {
    private Scanner scanner;

    public AddressBookView() {
        scanner = new Scanner(System.in);
    }

    public int showMenu() {
        System.out.println("Address Book Menu:");
        System.out.println("1. Add new contact");
        System.out.println("2. Find a contact by name");
        System.out.println("3. Display contacts");
        System.out.println("4. Update contact");
        System.out.println("5. Delete contact");
        System.out.println("6. Exit");
        System.out.print("Choose an option: ");
        return scanner.nextInt();
    }

    public Contact getContactDetails() {
        scanner.nextLine(); // consume newline
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Company: ");
        String company = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Phone: ");
        String phone = scanner.nextLine();

        return new Contact(name, company, email, phone);
    }

    public String getContactName() {
        scanner.nextLine(); // consume newline
        System.out.print("Enter Name: ");
        return scanner.nextLine();
    }

    public void displayContact(Contact contact) {
        if (contact != null) {
            System.out.println(contact);
        } else {
            System.out.println("Not found");
        }
    }

    public void displayAllContacts(java.util.List<Contact> contacts) {
        if (contacts.isEmpty()) {
            System.out.println("No contacts found.");
        } else {
            for (Contact contact : contacts) {
                System.out.println(contact);
            }
        }
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }
}
