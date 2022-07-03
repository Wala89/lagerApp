package com.example.lagerapptest.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.lagerapptest.database.allitems.Item;
import com.example.lagerapptest.database.allitems.ItemDao;

@Database(entities = {Item.class}, version = 1)
public abstract class StorageDatabase extends RoomDatabase {
    public abstract ItemDao itemDao();

    private static StorageDatabase instance;

    public static synchronized StorageDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, StorageDatabase.class, "storage_db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .addCallback(initialCallback)
                    .build();
        }

        return instance;
    }

    private static RoomDatabase.Callback initialCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

//            new PopulateInitialData(instance).execute();
        }
    };

//    // Eine Helfer Classe die die Datenbank im Hintergrund befüllt
//    private static class PopulateInitialData extends AsyncTask<Void, Void, Void> {
//
//        private ItemDao itemDao;
//
//        public PopulateInitialData(StorageDatabase db) {
//            this.itemDao = db.itemDao();
//        }
//
//        @Override
//        protected Void doInBackground(Void... voids) {
//
//            itemDao.insertSingleItem(new Item("testCode", "Nudeln", "2022-04-15", 3));
//            itemDao.insertSingleItem(new Item("testCode", "Salami", "2022-04-15", 1));
//            itemDao.insertSingleItem(new Item("testCode", "Käse", "2022-04-15", 2));
//            itemDao.insertSingleItem(new Item("testCode", "Eier", "2022-04-15", 5));
//
//            return null;
//        }
//    }
}
