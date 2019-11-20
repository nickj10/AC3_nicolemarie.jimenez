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
                    System.out.println("Error, opcio invalida. Introdueix una opcio valida (1-6).\n");
                }
            } while (!validOption(option));
            switch (option) {
                case 1:
                    calcularBegudesAlcoholiques();
                    break;
                case 2:
                    showDestilladesAmbCola();
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                default:
                    break;
            }
        } while (option != 6);
    }

    private boolean validOption(int option) {
        return (option >= 1 && option <= 6);
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

    public void calcularBegudesAlcoholiques() {
        int numTipus = this.types.length;
        int numAlcohols = 0;
        for (int i = 0; i < numTipus; i++) {
            numAlcohols = 0;
            for (Alcohol a: this.alcohols) {
                if (a.getType() == this.types[i].getId()) {
                    ++numAlcohols;
                }
            }
            System.out.println(this.types[i].getName() + ": " + numAlcohols);
        }
        System.out.println();
    }

    public void showDestilladesAmbCola() {
        for (Alcohol a: this.alcohols) {
            if(esDestillada(a) && canBeMixedWithCola(a)) {
                System.out.println(a.getNom());
            }
        }
        System.out.println();
    }

    private boolean esDestillada(Alcohol alcohol) {
        return (alcohol.getGraduation() >= 25 && alcohol.getGraduation() <= 60);
    }

    private boolean canBeMixedWithCola(Alcohol alcohol) {
        int[] combinations = alcohol.getConmbinations();
        int numCombinations = combinations.length;
        for (int i = 0; i < numCombinations; i++) {
            if (combinations[i] == 1)
                return true;
        }
        return false;
    }
}
