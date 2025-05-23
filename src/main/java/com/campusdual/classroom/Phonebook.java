package com.campusdual.classroom;

import java.util.Map;

public class Phonebook {
    private final Map<String,Contact> contacts;

    public Phonebook() {
        this.contacts = new java.util.HashMap<>();
    }


    public Map<String, Contact> getData() {
        return contacts;
    }

    public void addContact(Contact c) {
        contacts.put(c.getCode(), c);
    }

    public void deleteContact(String code) {
        contacts.remove(code);
    }

    public void showPhonebook() {
        System.out.println("Contact list:");
        for (Map.Entry<String, Contact> entry : contacts.entrySet()) {
            Contact value = entry.getValue();
            System.out.println(value);
        }
    }

    public void chooseContact(String code) {
        Contact contact = contacts.get(code);
        if (contact != null) {
            contact.showMenu();
        } else {
            System.out.println("Contact not found.");
        }
    }
}
