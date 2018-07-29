package com.example.phd.roomsample.Room.Daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.phd.roomsample.Room.Tables.Word;

import java.util.List;

@Dao
public interface WordDao {

    @Insert
    void insert(Word word);

    @Delete
    void delete(Word word);

    @Query("DELETE FROM wordTable")
    void deleteAll();

    @Query("SELECT * from wordTable ORDER BY word ASC")
    LiveData<List<Word>> getAllWords();
}