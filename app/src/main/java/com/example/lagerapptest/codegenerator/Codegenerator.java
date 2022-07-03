package com.example.lagerapptest.codegenerator;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*
* Die Klasse soll eine einzigartigen Code erzeugen um Items ohne Barcode eindeutig zuweisen zu können
* Dafür wird das aktuelle Datum genommen und ein Tageszähler hinten drangehängt zB. 2022-05-08-0001
* Wenn das neue Datum nicht mit dem zuletzt verwendeten übereinstimmt, dann wird der Zähler zurückgesetzt
* */

// TODO: Zuletzt verwendetes Datum Dauerhaft speichern

public class Codegenerator {
    private static final String TAG = "Codegenerator";

    private static String lastUseDate = "";
    private static int dailyCount = 0;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String getCode() {
        String date = getActualDate();

        if (date.equals(lastUseDate)) {
            dailyCount++;
        } else {
            lastUseDate = date;
            dailyCount = 0;
        }
        String count = String.format("%04d", dailyCount);
        return lastUseDate + "-" + count;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private static String getActualDate() {
        DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return date.format(LocalDateTime.now());
    }
}
