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
        ResultData resultData;
        try {
            bufferedReader = new BufferedReader(new FileReader("data/C2H5OH.json"));
            resultData = gson.fromJson(bufferedReader,ResultData.class);
            resultData.startProgram();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
