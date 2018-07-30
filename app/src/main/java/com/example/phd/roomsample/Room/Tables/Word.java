package com.example.phd.roomsample.Room.Tables;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.UUID;

@Entity(tableName = "wordTable")
public class Word {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private String mId;

    @NonNull
    @ColumnInfo(name = "word")
    private String mWord;

    public Word(String mWord) {
        this.mId = UUID.randomUUID().toString();
        this.mWord = mWord;
    }

    public String getId() {
        return mId;
    }

    public void setId(String mId) {
        this.mId = mId;
    }

    @NonNull
    public String getWord() {
        return mWord;
    }

    public void setWord(@NonNull String mWord) {
        this.mWord = mWord;
    }
}