package com.example.lagerapptest.database.allitems;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "storage_items")
public class Item {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String code;
    private String name;
    private String durabilityDate;
    private int capacity;

    public Item(String code, String name, String durabilityDate, int capacity) {
        this.code = code;
        this.name = name;
        this.durabilityDate = durabilityDate;
        this.capacity = capacity;
    }

    // *** Getter *********************************************************************************

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDurabilityDate() {
        return durabilityDate;
    }

    public int getCapacity() {
        return capacity;
    }

    // *** Setter *********************************************************************************

    public void setId(int id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDurabilityDate(String durabilityDate) {
        this.durabilityDate = durabilityDate;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
