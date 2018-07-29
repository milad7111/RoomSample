package com.example.phd.roomsample.Models;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.phd.roomsample.Repositories.WordRepository;
import com.example.phd.roomsample.Room.Tables.Word;

import java.util.List;

public class WordViewModel extends AndroidViewModel {

    //region Declare Objects
    private WordRepository mRepository;
    //endregion Declare Objects

    //region Declare Values
    private LiveData<List<Word>> mAllWords;
    //endregion Declare Values

    public WordViewModel(Application application) {
        super(application);
        mRepository = new WordRepository(application);
        mAllWords = mRepository.getAllWords();
    }

    public LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    public void insert(Word word) {
        mRepository.insert(word);
    }

    public void delete(Word word) {
        mRepository.delete(word);
    }
}