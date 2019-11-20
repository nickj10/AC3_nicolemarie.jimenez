package com.ac3.comandaAlcohol;

import java.util.LinkedList;
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
                    showFullInformationAlcohol(getMaxMidaFundadors());
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

    // Imprimir el menu principal per pantalla
    public void printMenu() {
        System.out.println("1. Calcular quantes begudes alcoholiques hi ha de cada tipus.");
        System.out.println("2. Mostrar una llista de begudes destilades (25-60) que es poden barrejar amb cola.");
        System.out.println("3. Mostrar tota la informacio de la ginebra on la suma de la mida del nom dels seus fundadors sigui major.");
        System.out.println("4. Mostrar un top 3 de les combinacions (alcohol + mixer) més repetides.");
        System.out.println("5. Mostrar un top 3 dels mixers on la mitjana dels graus de les begudes amb les que es barregen sigui major.");
        System.out.println("6. Sortir. \n");
        System.out.println("Selecciona una opcio: ");
    }

    // Calcular el número de begudes alcoholiques de cada tipus
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

    // Imprimir totes les begudes destillades que es poden combinar amb cola
    public void showDestilladesAmbCola() {
        for (Alcohol a: this.alcohols) {
            if(esDestillada(a) && canBeMixedWithCola(a)) {
                System.out.println(a.getNom());
            }
        }
        System.out.println();
    }

    // Verifica si una beguda és destil·lada o no
    private boolean esDestillada(Alcohol alcohol) {
        return (alcohol.getGraduation() >= 25 && alcohol.getGraduation() <= 60);
    }

    // Verifica si una beguda es pot combinar amb cola
    private boolean canBeMixedWithCola(Alcohol alcohol) {
        int[] combinations = alcohol.getCombinations();
        int numCombinations = combinations.length;
        for (int i = 0; i < numCombinations; i++) {
            if (combinations[i] == 1)
                return true;
        }
        return false;
    }

    // Verifica si una beguda és de tipus Gin
    private boolean isGinebra(Alcohol alcohol) {
        return getNomTipus(alcohol.getType()).equals("Gin");
    }

    // Agafa totes les begudes alcohòliques de tipus Gin
    private LinkedList<Alcohol> getGins() {
        LinkedList<Alcohol> gins = new LinkedList<Alcohol>();
        for(int i = 0; i < this.alcohols.length; i++) {
            if (isGinebra(this.alcohols[i])) {
                gins.add(this.alcohols[i]);
            }
        }
        return gins;
    }

    // Calcula quina beguda de tipus Gin té la major suma de la mida dels noms dels fundadors
    public Alcohol getMaxMidaFundadors() {
        LinkedList<Alcohol> gins = getGins();
        Alcohol max = gins.get(0);
        for (Alcohol a : gins) {
            if (getMidaFundadors(max) < getMidaFundadors(a)) {
                max = a;
            }
        }
        return max;
    }

    // Calcula la mida total dels noms dels fundadors de l'alcohol
    private int getMidaFundadors(Alcohol alcohol) {
        int total = 0;
        int numFounders = alcohol.getFounders().length;
        for (int i = 0; i < numFounders; i++) {
            total += alcohol.getFounders()[i].getName().length();
        }
        return total;
    }

    // Mostra per pantalla la informacio de la beguda alcoholica
    public void showFullInformationAlcohol(Alcohol alcohol) {
        System.out.println("Nom: " + alcohol.getNom());
        System.out.println("Graduacio: " + alcohol.getGraduation());
        System.out.println("Procedencia: " + alcohol.getProcedence());
        System.out.println("Any: " + alcohol.getYear());
        System.out.println("Tipus: " + getNomTipus(alcohol.getType()));
        System.out.print("Fundadors: ");
        int numFounders = alcohol.getFounders().length;
        for (int i = 0; i < numFounders; i++) {
            System.out.print(alcohol.getFounders()[i].getName());
            if (i != numFounders - 1)
                System.out.print(", ");
        }
        System.out.println();
        System.out.print("Combinacions: ");
        int numMixers = alcohol.getCombinations().length;
        for(int i = 0; i < numMixers; i++) {
            System.out.print(getNomMixer(alcohol.getCombinations()[i]));
            if (i != numMixers - 1)
                System.out.print(", ");
        }
        System.out.println("\n");
    }


    private String getNomTipus (int id) {
        int numTipus = this.types.length;
        for (int i = 0; i < numTipus; i++) {
            if (this.types[i].getId() == id) {
                return this.types[i].getName();
            }
        }
        return "";
    }

    private String getNomMixer (int id) {
        int numTipus = this.mixers.length;
        for (int i = 0; i < numTipus; i++) {
            if (this.mixers[i].getId() == id) {
                return this.mixers[i].getName();
            }
        }
        return "";
    }


}
