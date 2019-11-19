package com.ac3.comandaAlcohol;
import com.google.gson.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Gson gson = new Gson();
        BufferedReader bufferedReader;
        GestioConsultes gestioConsultes;
        try {
            bufferedReader = new BufferedReader(new FileReader("data/C2H5OH.json"));
            gestioConsultes = gson.fromJson(bufferedReader,GestioConsultes.class);
            gestioConsultes.startProgram();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
