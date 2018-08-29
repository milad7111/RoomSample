package com.example.phd.roomsample.room._main;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.phd.roomsample.room.daos.DefinitionDao;
import com.example.phd.roomsample.room.daos.WordDao;
import com.example.phd.roomsample.room.tables.Definition;
import com.example.phd.roomsample.room.tables.Word;

@Database(entities = {Word.class, Definition.class}, version = 1, exportSchema = false)
public abstract class WordRoomDatabase extends RoomDatabase {

    //region declare Objects
    private static WordRoomDatabase INSTANCE;

    public abstract WordDao wordDao();

    public abstract DefinitionDao definitionDao();
    //endregion declare Objects

    public static WordRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (WordRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WordRoomDatabase.class, "wordDataBase")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback() {

                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                }

                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                    super.onCreate(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private WordDao mWordDao;
        private DefinitionDao mDefinitionDao;

        PopulateDbAsync(WordRoomDatabase db) {
            mWordDao = db.wordDao();
            mDefinitionDao = db.definitionDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mWordDao.deleteAll();
            mDefinitionDao.deleteAll();

            Word mWordObject;

            mWordObject = new Word("Hello");
            mWordDao.insert(mWordObject);
            mDefinitionDao.insert(new Definition("سلام", "persian", mWordObject.getId()));
            mDefinitionDao.insert(new Definition("Chao", "china", mWordObject.getId()));

            mWordObject = new Word("Bye");
            mWordDao.insert(mWordObject);
            mDefinitionDao.insert(new Definition("خداحافظ", "persian", mWordObject.getId()));
            mDefinitionDao.insert(new Definition("فی امان ا...", "arabic", mWordObject.getId()));

            return null;
        }
    }
}