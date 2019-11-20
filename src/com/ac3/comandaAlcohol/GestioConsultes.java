package com.ac3.comandaAlcohol;

import java.util.Scanner;

public class GestioConsultes {
    private Alcohol[] alcohols;
    private Type[] types;
    private Mixer[] mixers;

    public void startProgram() {
        int option;
        do {
            do {
                printMenu();
                Scanner scan = new Scanner(System.in);
                option = scan.nextInt();
                if (!validOption(option)) {
                    System.out.println("Error, opcio invalida.\n");
                }
            } while (!validOption(option));
        } while (option != 6);
    }

    private boolean validOption(int option) {
        if (option >= 1 && option <= 6)
            return true;
        return false;
    }

    public void printMenu() {
        System.out.println("1. Calcular quantes begudes alcoholiques hi ha de cada tipus.");
        System.out.println("2. Mostrar una llista de begudes destilades (25-60) que es poden barrejar amb cola.");
        System.out.println("3. Mostrar tota la informacio de la ginebra on la suma de la mida del nom dels seus fundadors sigui major.");
        System.out.println("4. Mostrar un top 3 de les combinacions (alcohol + mixer) més repetides.");
        System.out.println("5. Mostrar un top 3 dels mixers on la mitjana dels graus de les begudes amb les que es barregen sigui major.");
        System.out.println("6. Sortir. \n");
        System.out.println("Selecciona una opcio: ");
    }
}
