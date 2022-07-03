package com.example.lagerapptest.database.allitems;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ItemDao {
    @Insert
    void insertSingleItem(Item item);

    @Query("SELECT * FROM storage_items")
    List<Item> getAllItems();

    @Query("SELECT * FROM storage_items WHERE capacity >0")
    List<Item> getAllStoredItems();

    @Query("SELECT * FROM storage_items WHERE code = :code")
    Item getSingleItem(String code);

    @Query("DELETE FROM storage_items WHERE code = :code")
    void deleteSingleItem(String code);

    @Query("DELETE FROM storage_items WHERE name = :name")
    void deleteSingleItemByName(String name);
}
