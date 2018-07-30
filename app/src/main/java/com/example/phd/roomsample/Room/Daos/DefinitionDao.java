package com.example.phd.roomsample.Room.Daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.phd.roomsample.Room.Tables.Definition;
import com.example.phd.roomsample.Room.Tables.Word;

import java.util.List;

@Dao
public interface DefinitionDao {

    @Insert
    void insert(Definition definition);

    @Delete
    void delete(Definition definition);

    @Query("DELETE FROM definitionTable")
    void deleteAll();

    @Query("SELECT * FROM definitionTable ORDER BY definition ASC")
    LiveData<List<Definition>> getAllDefinitions();

    @Query("SELECT * FROM definitionTable WHERE word_id = :word_id")
    LiveData<List<Definition>> getDefinitionsByWord(String word_id);
}