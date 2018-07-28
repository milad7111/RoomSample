package com.example.phd.roomsample;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface WordDao {

    @Insert
    void insert(Word word);

    @Query("DELETE FROM wordTable")
    void deleteAll();

    @Query("SELECT * from wordTable ORDER BY word ASC")
    LiveData<List<Word>> getAllWords();
}
