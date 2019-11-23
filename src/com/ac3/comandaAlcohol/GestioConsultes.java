package com.ac3.comandaAlcohol;

import java.text.DecimalFormat;
import java.util.*;
import static java.util.stream.Collectors.toMap;

/**
 * Aquesta classe gestiona les consultes relacionades amb les begudes alcohòliques, les combinacions i els seus tipus.
 * @author Nicole Marie Jimenez
 */
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
                    getTop3Combinacions();
                    break;
                case 5:
                    getTop3Mixers();
                    break;
                default:
                    break;
            }
        } while (option != 6);
    }

    /**
     * Aquest mètode verifica si és una opció valida o no.
     * @param option l'opció introduïda per l'usuari.
     * @return true si està dins del rang 1 - 6.
     */
    private boolean validOption(int option) {
        return (option >= 1 && option <= 6);
    }

    /**
     *  Aquest mètode imprimeix el menu principal per pantalla.
     */
    public void printMenu() {
        System.out.println("1. Calcular quantes begudes alcoholiques hi ha de cada tipus.");
        System.out.println("2. Mostrar una llista de begudes destilades (25-60) que es poden barrejar amb cola.");
        System.out.println("3. Mostrar tota la informacio de la ginebra on la suma de la mida del nom dels seus fundadors sigui major.");
        System.out.println("4. Mostrar un top 3 de les combinacions (alcohol + mixer) més repetides.");
        System.out.println("5. Mostrar un top 3 dels mixers on la mitjana dels graus de les begudes amb les que es barregen sigui major.");
        System.out.println("6. Sortir. \n");
        System.out.println("Selecciona una opcio: ");
    }

    /**
     * Aquest mètode calcula el número de begudes alcoholiques de cada tipus.
     */
    public void calcularBegudesAlcoholiques() {
        int numTipus = this.types.length;
        int numAlcohols;
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

    /**
     * Imprimir totes les begudes destillades que es poden combinar amb cola.
     */
    public void showDestilladesAmbCola() {
        for (Alcohol a: this.alcohols) {
            if(esDestillada(a) && canBeMixedWithCola(a)) {
                System.out.println(a.getNom());
            }
        }
        System.out.println();
    }

    /**
     * Aquest mètode verifica si una beguda és destil·lada o no.
     * @param alcohol la beguda alcohòlica a verificar.
     * @return true si la beguda és destil·lada
     */
    private boolean esDestillada(Alcohol alcohol) {
        return (alcohol.getGraduation() >= 25 && alcohol.getGraduation() <= 60);
    }

    /**
     * Aquest mètode verifica si una beguda es pot combinar amb cola.
     * @param alcohol la beguda alcohòlica a verificar.
     * @return true si la beguda es pot barrejar amb cola.
     */
    private boolean canBeMixedWithCola(Alcohol alcohol) {
        int[] combinations = alcohol.getCombinations();
        int numCombinations = combinations.length;
        for (int i = 0; i < numCombinations; i++) {
            if (combinations[i] == 1)
                return true;
        }
        return false;
    }

    /**
     * Aquest mètode verifica si una beguda alcohòlica és de tipus Gin.
     * @param alcohol la beguda alcohòlica que es verificarà.
     * @return true si el tipus de l'alcohol és Gin.
     */
    private boolean isGinebra(Alcohol alcohol) {
        return getNomTipus(alcohol.getType()).equals("Gin");
    }

    /**
     * Aquest mètode agafa totes les begudes alcohòliques de tipus Gin del catàleg d'alcohols.
     * @return una llista de begudes alcohòliques de tipus Gin.
     */
    private LinkedList<Alcohol> getGins() {
        LinkedList<Alcohol> gins = new LinkedList<Alcohol>();
        for(int i = 0; i < this.alcohols.length; i++) {
            if (isGinebra(this.alcohols[i])) {
                gins.add(this.alcohols[i]);
            }
        }
        return gins;
    }

    /**
     * Aquest mètode calcula quina beguda de tipus Gin té la major suma de la mida dels noms dels fundadors.
     * @return la beguda alcohòlica que té la major suma de la mida dels noms dels fundadors.
     */
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

    /**
     * Aquest mètode calcula el total de les mides dels noms dels fundadors de l'alcohol.
     * @param alcohol la beguda alcohòlica de la qual calculem la mida dels noms dels fundadors.
     * @return la mida total dels tots els noms dels fundadors de l'alcohol.
     */
    private int getMidaFundadors(Alcohol alcohol) {
        int total = 0;
        if (alcohol != null) {
            int numFounders = alcohol.getFounders().length;
            for (int i = 0; i < numFounders; i++) {
                total += alcohol.getFounders()[i].getName().length();
            }
        }
        return total;
    }

    /**
     * Aquest mètode mostra per pantalla la tota informació de la beguda alcoholica.
     * @param alcohol la beguda alcoholica de la qual es mostra la informació.
     */
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

    /**
     * Aquest mètode agafa el nom del tipus d'alcohol segons l'id.
     * @param id el identificador del tipus.
     * @return el nom del tipus d'alcohol.
     */
    private String getNomTipus (int id) {
        int numTipus = this.types.length;
        for (int i = 0; i < numTipus; i++) {
            if (this.types[i].getId() == id) {
                return this.types[i].getName();
            }
        }
        return "";
    }

    /**
     * Aquest mètode agafa el nom del mixer segons l'id.
     * @param id el identificador del mixer.
     * @return el nom del mixer.
     */
    private String getNomMixer (int id) {
        int numTipus = this.mixers.length;
        for (int i = 0; i < numTipus; i++) {
            if (this.mixers[i].getId() == id) {
                return this.mixers[i].getName();
            }
        }
        return "";
    }

    /**
     * Aquest mètode mostra el top 3 de les combinacions (alcohol + mixer) més repetides.
     */
    public void getTop3Combinacions() {
        Map<String,Integer> mapCombinacions = new HashMap<String,Integer>();

        // Calcular les repeticions per cada combinacio
        for (Alcohol a : this.alcohols) {
            int[] combinacions = a.getCombinations();
            for (int i = 0; i < combinacions.length; i++) {
                String combinacio = a.getType() + "-" + combinacions[i];
                mapCombinacions.put(combinacio,
                        !mapCombinacions.containsKey(combinacio) ? 1 : mapCombinacions.get(combinacio) + 1
                );
            }
        }

        // Ordenar pel nombre de repeticions
        Map<String, Integer> sorted = mapCombinacions
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));

        // Mostrar top 3 combinacions per pantalla
        int j = 0;
        System.out.println("Nom de combinacio - Numero de repeticions");
        for (Map.Entry<String, Integer> entry : sorted.entrySet()) {
            System.out.println(getNomTipus(atoi(entry.getKey().substring(0,1)))
                    + " + " + getNomMixer(atoi(entry.getKey().substring(entry.getKey().length() - 1)))
                    + " - "
                    + entry.getValue());
            if (j == 2)
                break;
            j++;
        }
        System.out.println();
    }

    /**
     * Aquest mètode converteix un string a un nombre enter.
     * @param num el string que representa un nombre enter.
     * @return el nombre enter convertit. Si no es converteix, retorna un zero.
     */
    private int atoi(String num) {
        int result = 0;
        for (int i = 0; i < num.length(); i++)
            result = result * 10 + num.charAt(i) - '0';
        return result;
    }

    /**
     * Aquest mètode mostra el top 3 dels mixers on la mitjana dels graus de les begudes amb les que
     * es barregen sigui major.
     */
    public void getTop3Mixers() {
        Map<Integer,Double> mapMixers = new HashMap<Integer,Double>();
        Map<Integer,Integer> mapQuantity = new HashMap<Integer, Integer>();

        // Calcula el total dels graus i calcula la quantitat de begudes per a cada mixer
        for (Alcohol a: this.alcohols) {
            int[] combinacions = a.getCombinations();
            for (int i = 0; i < combinacions.length; i++) {
                mapMixers.put(
                        combinacions[i],
                        !mapMixers.containsKey(combinacions[i]) ?
                        a.getGraduation() : mapMixers.get(combinacions[i]) + a.getGraduation()
                );
                mapQuantity.put(combinacions[i],
                        !mapQuantity.containsKey(combinacions[i]) ? 1 : mapQuantity.get(combinacions[i]) + 1
                );
            }
        }

        // Calcular la mitjana dels graus per cada mixer
        for (Map.Entry<Integer, Double> entry : mapMixers.entrySet()) {
            mapMixers.put(entry.getKey(), entry.getValue() / mapQuantity.get(entry.getKey()));
        }

        // Ordenar per la mitjana dels graus de begudes
        Map<Integer, Double> sorted = mapMixers
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));

        // Mostrar top 3 mixers amb la mitjana major de graus
        int j = 0;
        DecimalFormat df = new DecimalFormat("#.##");
        System.out.println("Nom de mixer - Mitjana de graus");
        for (Map.Entry<Integer, Double> entry : sorted.entrySet()) {
            System.out.println(getNomMixer(entry.getKey()) + " - " + df.format(entry.getValue()));
            if (j == 2)
                break;
            j++;
        }
        System.out.println();
    }

}
