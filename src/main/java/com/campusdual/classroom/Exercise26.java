package com.campusdual.classroom;


public class Exercise26 {
    public static void main(String[] args) {
        Contact contact = new Contact("Fernando Miguel", "Juan de los Santos Requejo León", "123456789");
        Contact contact2 = new Contact("Jose Manuel", "Soria", "987654321");
        System.out.println(contact);
        Phonebook phonebook = new Phonebook();
        phonebook.addContact(contact);
        phonebook.addContact(contact2);
        phonebook.showPhonebook();
        phonebook.chooseContact("fjdelossantosrequejoleon");
    }
}
