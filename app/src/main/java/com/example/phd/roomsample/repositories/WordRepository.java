package com.example.phd.roomsample.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.phd.roomsample.executer.CustomThreadPoolExecutor;
import com.example.phd.roomsample.room._main.WordRoomDatabase;
import com.example.phd.roomsample.room.daos.DefinitionDao;
import com.example.phd.roomsample.room.daos.WordDao;
import com.example.phd.roomsample.room.tables.Definition;
import com.example.phd.roomsample.room.tables.Word;

import java.util.List;

public class WordRepository {

    //region Declare Objects
    private WordDao mWordDao;
    private DefinitionDao mDefinitionDao;
    //endregion Declare Objects

    //region Declare Values
    //endregion Declare Values

    public WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);

        mWordDao = db.wordDao();
        mDefinitionDao = db.definitionDao();
    }

    //region Word table
    public LiveData<List<Word>> getAllWords() {
        return mWordDao.getAllWords();
    }

    public void insertWord(Word word) {
        CustomThreadPoolExecutor.getInstance().execute(
                new Runnable() {
                    @Override
                    public void run() {
                        new insertWordAsyncTask(mWordDao).execute(word);
                    }
                });
    }

    public void deleteWord(Word word) {
        CustomThreadPoolExecutor.getInstance().execute(
                new Runnable() {
                    @Override
                    public void run() {
                        new deleteWordAsyncTask(mWordDao).execute(word);
                    }
                }
        );
    }

    private static class insertWordAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao mAsyncTaskWordDao;

        insertWordAsyncTask(WordDao dao) {
            mAsyncTaskWordDao = dao;
        }

        @Override
        protected Void doInBackground(final Word... params) {
            mAsyncTaskWordDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteWordAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao mAsyncTaskWordDao;

        deleteWordAsyncTask(WordDao dao) {
            mAsyncTaskWordDao = dao;
        }

        @Override
        protected Void doInBackground(final Word... params) {
            mAsyncTaskWordDao.delete(params[0]);
            return null;
        }
    }
    //endregion Word table

    //region Definition table
    public LiveData<List<Definition>> getAllDefinitions() {
        return mDefinitionDao.getAllDefinitions();
    }

    public void insertDefinition(Definition definition) {
        new insertDefinitionAsyncTask(mDefinitionDao).execute(definition);
    }

    public void deleteDefinition(Definition definition) {
        new deleteDefinitionAsyncTask(mDefinitionDao).execute(definition);
    }

    private static class insertDefinitionAsyncTask extends AsyncTask<Definition, Void, Void> {

        private DefinitionDao mAsyncTaskDefinitionDao;

        insertDefinitionAsyncTask(DefinitionDao dao) {
            mAsyncTaskDefinitionDao = dao;
        }

        @Override
        protected Void doInBackground(final Definition... params) {
            mAsyncTaskDefinitionDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteDefinitionAsyncTask extends AsyncTask<Definition, Void, Void> {

        private DefinitionDao mAsyncTaskDefinitionDao;

        deleteDefinitionAsyncTask(DefinitionDao dao) {
            mAsyncTaskDefinitionDao = dao;
        }

        @Override
        protected Void doInBackground(final Definition... params) {
            mAsyncTaskDefinitionDao.delete(params[0]);
            return null;
        }
    }
    //endregion Definition table

    //region Word_Definition tables
    public LiveData<List<Definition>> getDefinitionsByWord(Word _mWord) {
        return mDefinitionDao.getDefinitionsByWord(_mWord.getId());
    }
    //endregion Word_Definition tables
}