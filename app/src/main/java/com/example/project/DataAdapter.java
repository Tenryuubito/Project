package com.example.project;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.*;
import java.util.ArrayList;

public class DataAdapter {

    private static File fileDirPath;
    public static String stringSplitter = "§/%&#";

    public static void setFileDir(File fileDir)
    {
        fileDirPath = fileDir;
    }
    public static String readSingle(int _ID) {

        BufferedReader bufferedReader = null;
        String ret = "";

        try {
            String inputLine;
            bufferedReader = new BufferedReader(new FileReader(fileDirPath.getPath() + String.valueOf(_ID)));
            ret = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return ret;
    }

    public static ArrayList<String> read(int _ID) {

        BufferedReader bufferedReader = null;
        ArrayList<String> listReturn = new ArrayList<>();

        try {
            String inputLine;
            bufferedReader = new BufferedReader(new FileReader(String.valueOf(_ID)));
            while ((inputLine = bufferedReader.readLine()) != null) {
                listReturn.add(inputLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return listReturn;
    }

    public static void writeSingle(String data, int _ID) {

        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileDirPath.getPath() + String.valueOf(_ID)))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void write(String data, int _ID) {

        String[] list = data.split(DataAdapter.stringSplitter);

        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileDirPath.getPath() + String.valueOf(_ID)))) {
            for(String line : list) {
                bufferedWriter.write(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
