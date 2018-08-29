package com.example.phd.roomsample.room.tables;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.UUID;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "definitionTable",
        foreignKeys = @ForeignKey(entity = Word.class, parentColumns = "id", childColumns = "word_id", onDelete = CASCADE),
        indices = {@Index("word_id")})
public class Definition {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private String mId;

    @NonNull
    @ColumnInfo(name = "definition")
    private String mDefinition;

    @NonNull
    @ColumnInfo(name = "language")
    private String mLanguage;

    @ColumnInfo(name = "word_id")
    private String mForeignWordId;

    public Definition(String mDefinition, String mLanguage, String mForeignWordId) {
        this.mId = UUID.randomUUID().toString();
        this.mDefinition = mDefinition;
        this.mLanguage = mLanguage;
        this.mForeignWordId = mForeignWordId;
    }

    public String getId() {
        return mId;
    }

    public void setId(String mId) {
        this.mId = mId;
    }

    @NonNull
    public String getDefinition() {
        return mDefinition;
    }

    public void setDefinition(@NonNull String mDefinition) {
        this.mDefinition = mDefinition;
    }

    @NonNull
    public String getLanguage() {
        return mLanguage;
    }

    public void setLanguage(@NonNull String mLanguage) {
        this.mLanguage = mLanguage;
    }

    public String getForeignWordId() {
        return mForeignWordId;
    }
}