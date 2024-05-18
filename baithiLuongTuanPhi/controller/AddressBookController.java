package baithiLuongTuanPhi.controller;

import baithiLuongTuanPhi.dao.ContactDatabaseHelper;
import baithiLuongTuanPhi.model.Contact;
import baithiLuongTuanPhi.view.AddressBookView;

import java.util.List;

public class AddressBookController {
    private AddressBookView view;
    private ContactDatabaseHelper dbHelper;

    public AddressBookController(AddressBookView view) {
        this.view = view;
        this.dbHelper = new ContactDatabaseHelper();
    }

    public void start() {
        boolean exit = false;
        while (!exit) {
            int option = view.showMenu();
            switch (option) {
                case 1:
                    addContact();
                    break;
                case 2:
                    findContact();
                    break;
                case 3:
                    displayContacts();
                    break;
                case 4:
                    updateContact();
                    break;
                case 5:
                    deleteContact();
                    break;
                case 6:
                    exit = true;
                    break;
                default:
                    view.displayMessage("Invalid option. Please try again.");
            }
        }
    }

    private void addContact() {
        Contact contact = view.getContactDetails();
        dbHelper.addContact(contact);
        view.displayMessage("Contact added successfully.");
    }

    private void findContact() {
        String name = view.getContactName();
        Contact contact = dbHelper.findContactByName(name);
        view.displayContact(contact);
    }

    private void displayContacts() {
        List<Contact> contacts = dbHelper.getAll();
        view.displayAllContacts(contacts);
    }

    private void updateContact() {
        String oldName = view.getContactName();
        Contact newContact = view.getContactDetails();
        dbHelper.updateContact(oldName, newContact);
        view.displayMessage("Contact updated successfully.");
    }

    private void deleteContact() {
        String name = view.getContactName();
        dbHelper.delete(name);
        view.displayMessage("Contact deleted successfully.");
    }
}
