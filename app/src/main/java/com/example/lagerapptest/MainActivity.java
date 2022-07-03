package com.example.lagerapptest;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lagerapptest.codegenerator.Codegenerator;
import com.example.lagerapptest.database.allitems.Item;
import com.example.lagerapptest.database.StorageDatabase;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.util.ArrayList;

// TODO: DataBase all Items
// TODO: DataBase storageItems
// TODO: Unique code generator for items witch no barcode
// TODO: Design concept

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private TextView txtErgebnis;
    private EditText etxtProduktname;
    private CheckBox cbAllItems;

    private RecyclerView recyclerView;
    private ItemRecViewAdapter adapter;

    private StorageDatabase database;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = StorageDatabase.getInstance(this);

        // TODO: in eine eigene Methode auslagern
        txtErgebnis = findViewById(R.id.txtErgebnis);
        etxtProduktname = findViewById(R.id.etxtProduktname);
        cbAllItems = findViewById(R.id.cbAllItems);
        recyclerView = findViewById(R.id.recViewItems);

        adapter = new ItemRecViewAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL,false));

        getAllItemsFromDatabase();

        //TODO: in eine eigene Methode auslagern
        cbAllItems.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    getAllItemsFromDatabase();
                } else {
                    getAllStoredItemsFromDatabase();
                }
            }
        });

        Log.e(TAG, Codegenerator.getCode()); // REMOVE: Entfernen wenn Codegenerator funktioniert
    }

    // Register the launcher and result handler
    private final ActivityResultLauncher<ScanOptions> barcodeLauncher = registerForActivityResult(new ScanContract(),
            result -> {
                if (result.getContents() == null) {
                    Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show();
                } else {
                    //Toast.makeText(this, "Scanned" + database.itemDao().getSingleItem(result.getContents()), Toast.LENGTH_SHORT).show();
                    txtErgebnis.setText(result.getContents());
                    //database.itemDao().getSingleItem(result.getContents());

                    if (database.itemDao().getSingleItem(result.getContents()) != null) {
                        ArrayList<Item> list = new ArrayList<>();
                        list.add(database.itemDao().getSingleItem(result.getContents()));
                        adapter.setArrayListItems(list);
                    }
                }
            });

    // Button Scann to lunch the Scanning prozess
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void scannedItem(View view) {
        barcodeLauncher.launch(new ScanOptions());
        Log.e(TAG, Codegenerator.getCode()); // REMOVE: Entfernen wenn Codegenerator funktioniert
    }

    // Button add to lunch the Scanning prozess
    public void addScanndItem(View view) {
        database.itemDao().insertSingleItem(new Item(txtErgebnis.getText().toString() , etxtProduktname.getText().toString(), "2022-05-05", 0));
        getAllItemsFromDatabase();
    }

    // Button Delete to lunch the Scanning prozess
    public void deleteScanndItem(View view) {
        database.itemDao().deleteSingleItem(txtErgebnis.getText().toString());
        getAllItemsFromDatabase();
    }

    private void getAllItemsFromDatabase() {
        ArrayList<Item> newItems = (ArrayList<Item>) database.itemDao().getAllItems();
        adapter.setArrayListItems(newItems);
    }

    private void getAllStoredItemsFromDatabase() {
        ArrayList<Item> list = (ArrayList<Item>) database.itemDao().getAllStoredItems();
        adapter.setArrayListItems(list);
    }

    // Button Test do what ever to do
    public void testButton(View view) {
        database.itemDao().deleteSingleItem(etxtProduktname.getText().toString());
        getAllItemsFromDatabase();
    }
}