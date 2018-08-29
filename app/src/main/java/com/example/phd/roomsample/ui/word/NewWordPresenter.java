package com.example.phd.roomsample.ui.word;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.FragmentActivity;

import com.example.phd.roomsample.models.WordViewModel;
import com.example.phd.roomsample.room.tables.Word;

/**
 * Prepares data and Notify View
 */
public class NewWordPresenter implements WordContract.Presenter {

    //region Declare Objects
    private WordViewModel mWordViewModel;
    private WordContract.MvpView mMvpView;
    //endregion Declare Objects

    public NewWordPresenter(WordContract.MvpView _mMvpView) {
        this.mMvpView = _mMvpView;
        this.mWordViewModel = ViewModelProviders.of((FragmentActivity) _mMvpView).get(WordViewModel.class);
    }

    @Override
    public void insertWord(Word _mWord) {
        mWordViewModel.insertWord(_mWord);
        mMvpView.checkResponse(1);
    }
}