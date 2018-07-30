package com.example.phd.roomsample.Models;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.phd.roomsample.Repositories.WordRepository;
import com.example.phd.roomsample.Room.Tables.Definition;
import com.example.phd.roomsample.Room.Tables.Word;

import java.util.List;

public class WordViewModel extends AndroidViewModel {

    //region Declare Objects
    private WordRepository mRepository;
    //endregion Declare Objects

    //region Declare Values
    private LiveData<List<Word>> mAllWords;
    private LiveData<List<Definition>> mAllDefinitions;
    //endregion Declare Values

    public WordViewModel(Application application) {
        super(application);
        mRepository = new WordRepository(application);
        mAllWords = mRepository.getAllWords();
        mAllDefinitions = mRepository.getAllDefinitions();
    }

    //region Word table
    public LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    public void insertWord(Word word) {
        mRepository.insertWord(word);
    }

    public void deleteWord(Word word) {
        mRepository.deleteWord(word);
    }
    //endregion Word table

    //region Definition table
    public LiveData<List<Definition>> getAllDefinitions() {
        return mAllDefinitions;
    }

    public void insertDefinition(Definition definition) {
        mRepository.insertDefinition(definition);
    }

    public void deleteDefinition(Definition definition) {
        mRepository.deleteDefinition(definition);
    }
    //endregion Definition table

    //region Word_Definition tables
    public LiveData<List<Definition>> getDefinitionsByWord(Word _mWord) {
        return mRepository.getDefinitionsByWord(_mWord);
    }
    //endregion Word_Definition tables
}