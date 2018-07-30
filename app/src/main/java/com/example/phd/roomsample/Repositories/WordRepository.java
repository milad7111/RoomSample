package com.example.phd.roomsample.Repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.phd.roomsample.Room.Daos.DefinitionDao;
import com.example.phd.roomsample.Room.Daos.WordDao;
import com.example.phd.roomsample.Room.Tables.Definition;
import com.example.phd.roomsample.Room.Tables.Word;
import com.example.phd.roomsample.Room._Main.WordRoomDatabase;

import java.util.List;

public class WordRepository {

    //region Declare Objects
    private WordDao mWordDao;
    private DefinitionDao mDefinitionDao;
    //endregion Declare Objects

    //region Declare Values
    private LiveData<List<Word>> mAllWords;
    private LiveData<List<Definition>> mAllDefinitions;
    //endregion Declare Values

    public WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);

        mWordDao = db.wordDao();
        mDefinitionDao = db.definitionDao();

        mAllWords = mWordDao.getAllWords();
        mAllDefinitions = mDefinitionDao.getAllDefinitions();
    }

    //region Word table
    public LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    public void insertWord(Word word) {
        new insertWordAsyncTask(mWordDao).execute(word);
    }

    public void deleteWord(Word word) {
        new deleteWordAsyncTask(mWordDao).execute(word);
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
        return mAllDefinitions;
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