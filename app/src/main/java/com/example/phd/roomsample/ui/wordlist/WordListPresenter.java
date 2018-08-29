package com.example.phd.roomsample.ui.wordlist;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.example.phd.roomsample.models.WordViewModel;
import com.example.phd.roomsample.room.tables.Definition;
import com.example.phd.roomsample.room.tables.Word;

import java.util.List;

/**
 * Prepares data and Notify View
 */
public class WordListPresenter implements WordListContract.Presenter {

    //region Declare Objects
    private WordViewModel mWordViewModel;
    private WordListContract.MvpView mMvpView;
    //endregion Declare Objects

    WordListPresenter(WordListContract.MvpView _mMvpView) {
        this.mMvpView = _mMvpView;
        this.mWordViewModel = ViewModelProviders.of((FragmentActivity) _mMvpView).get(WordViewModel.class);
    }

    @Override
    public void getAllWords() {
        mWordViewModel.getAllWords().observe(mMvpView, new Observer<List<Word>>() {
            @Override
            public void onChanged(@Nullable final List<Word> words) {
                mMvpView.showAllWords(words);
            }
        });
    }

    @Override
    public void deleteWord(Word _mWord) {
        mWordViewModel.deleteWord(_mWord);
    }

    @Override
    public void getDefinitionsByWord(Word _mWord) {
        LiveData<List<Definition>> mDefinitionsByWord = mWordViewModel.getDefinitionsByWord(_mWord);

        mDefinitionsByWord.observe(mMvpView, new Observer<List<Definition>>() {
            @Override
            public void onChanged(@Nullable List<Definition> definitions) {
                mMvpView.showDefinitionsByWord(definitions);
            }
        });
    }
}