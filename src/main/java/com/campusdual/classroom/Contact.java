package com.campusdual.classroom;

import com.campusdual.util.Utils;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Contact implements ICallActions{

    private final String name;
    private final String surname;
    private String phone;
    private final String code;
    private final List<String> phones = new ArrayList<>();

    public Contact(String name, String surname, String phone){
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.code = generateCode();
        phones.add(phone);
    }

    private String generateCode() {
        StringBuilder codeBuilder = new StringBuilder();
        codeBuilder.append(getName().toLowerCase().charAt(0));
        String[] surnameParts = getSurnames().toLowerCase().split(" ");
        if (surnameParts.length == 1) {
            codeBuilder.append(surnameParts[0]);
        } else {
            codeBuilder.append(surnameParts[0].charAt(0));
            for (int i = 1; i < surnameParts.length; i++) {
                codeBuilder.append(surnameParts[i].replaceAll(" ", ""));
            }
        }
        return removeDiacritics(codeBuilder.toString());
    }

    private String removeDiacritics(String input) {
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        return normalized.replaceAll("\\p{InCombiningDiacriticalMarks}", "");
    }

    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        int option;
        do {
            System.out.println("\nMenú de acciones para el contacto: " + this.getName() + " " + this.getSurnames());
            System.out.println("1. Llamar a mi número");
            System.out.println("2. Llamar a otro número");
            System.out.println("3. Mostrar detalles del contacto");
            System.out.println("4. Cambiar número de contacto principal");
            System.out.println("5. Agregar número de contacto");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (option) {
                case 1:
                    callMyNumber();
                    break;
                case 2:
                    System.out.print("Ingrese el número al que desea llamar: ");
                    String otherNumber = scanner.nextLine();
                    callOtherNumber(otherNumber);
                    break;
                case 3:
                    showContactDetails();
                    break;
                case 4:
                    changeContactNumber();
                    break;
                case 5:
                    addNewPhoneNumber();
                    break;
                case 0:
                    System.out.println("Saliendo del menú...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (option != 0);
    }

    private void addNewPhoneNumber() {
        System.out.print("Ingrese el nuevo número de contacto: ");
        String newPhone = Utils.string();
        if (!phones.contains(newPhone)) {
            phones.add(newPhone);
            System.out.println("Número de contacto agregado.");
        } else {
            System.out.println("El número de contacto ya existe.");
        }
    }

    private void changeContactNumber() {
        System.out.println("Seleccione el número de contacto que desea cambiar:");
        for (int i = 0; i < phones.size(); i++) {
            System.out.println("Número de contacto " + (i + 1) + ": " + phones.get(i));
        }
        int numberIndex = Utils.integer()+1;
        if (numberIndex > 0 && numberIndex < phones.size()) {
            this.setPhone(phones.get(numberIndex));
            System.out.println("Número de contacto actualizado.");
        } else {
            System.out.println("Índice no válido.");
        }
    }

    @Override
    public void callMyNumber() {
        System.out.println(this.getName()+" "+ this.getSurnames()+" llamando a : " + this.getPhone());
    }

    @Override
    public void callOtherNumber(String number) {
        System.out.println(this.getName()+" "+ this.getSurnames()+" llamando a : " + number);
    }

    @Override
    public void showContactDetails() {
        System.out.println(this);
    }

    public String getName() {
        return name;
    }

    public String getSurnames() {
        return surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<String> getPhones() {
        return phones;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone='" + phone + '\'' +
                ", code='" + code + '\'' +
                ", phones=" + phones +
                '}';
    }
}
