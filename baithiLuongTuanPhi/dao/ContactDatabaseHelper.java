package baithiLuongTuanPhi.dao;

import baithiLuongTuanPhi.model.Contact;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactDatabaseHelper extends DatabaseHelper<Contact> {

    public ContactDatabaseHelper() {
        super("Contacts",
                rs -> {
                    try {
                        return new Contact(rs.getString("name"), rs.getString("company"), rs.getString("email"), rs.getString("phone"));
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                },
                "INSERT INTO Contacts (name, company, email, phone) VALUES (?, ?, ?, ?)",
                "UPDATE Contacts SET name = ?, company = ?, email = ?, phone = ? WHERE name = ?");
    }

    public void addContact(Contact contact) {
        add(contact, pstmt -> {
            try {
                pstmt.setString(1, contact.getName());
                pstmt.setString(2, contact.getCompany());
                pstmt.setString(3, contact.getEmail());
                pstmt.setString(4, contact.getPhone());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        });
    }

    public Contact findContactByName(String name) {
        return find(name, "SELECT * FROM Contacts WHERE name = ?", pstmt -> {
            try {
                pstmt.setString(1, name);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        });
    }

    public void updateContact(String oldName, Contact newContact) {
        update(oldName, newContact, pstmt -> {
            try {
                pstmt.setString(1, newContact.getName());
                pstmt.setString(2, newContact.getCompany());
                pstmt.setString(3, newContact.getEmail());
                pstmt.setString(4, newContact.getPhone());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        });
    }
}
