package com.example.phd.roomsample.models;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.phd.roomsample.repositories.WordRepository;
import com.example.phd.roomsample.room.tables.Definition;
import com.example.phd.roomsample.room.tables.Word;

import java.util.List;

public class WordViewModel extends AndroidViewModel {

    //region Declare Objects
    private WordRepository mRepository;
    //endregion Declare Objects

    //region Declare Values
    //endregion Declare Values

    public WordViewModel(Application application) {
        super(application);

        mRepository = new WordRepository(application);
    }

    //region Word table
    public LiveData<List<Word>> getAllWords() {
        return mRepository.getAllWords();
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
        return mRepository.getAllDefinitions();
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