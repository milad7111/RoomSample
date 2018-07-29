package com.example.phd.roomsample.Ui.Word;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.FragmentActivity;

import com.example.phd.roomsample.Models.WordViewModel;
import com.example.phd.roomsample.Room.Tables.Word;

/**
 * Prepares data and Notify View
 */
public class NewWordPresenter implements WordContract.Presenter {

    //region Declare Objects
    private WordViewModel mWordViewModel;
    private WordContract.MvpView mMvpView;
    //endregion Declare Objects

    NewWordPresenter(WordContract.MvpView _mMvpView) {
        this.mMvpView = _mMvpView;
        this.mWordViewModel = ViewModelProviders.of((FragmentActivity) _mMvpView).get(WordViewModel.class);
    }

    @Override
    public void insertNewWord(Word _mWord) {
        mWordViewModel.insert(_mWord);
        mMvpView.checkResponse(1);
    }
}