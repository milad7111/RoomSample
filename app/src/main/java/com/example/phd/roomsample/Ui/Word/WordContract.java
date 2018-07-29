package com.example.phd.roomsample.Ui.Word;

import android.arch.lifecycle.LiveData;

import com.example.phd.roomsample.Room.Tables.Word;
import com.example.phd.roomsample.Ui.Home.WordListActivity;
import com.example.phd.roomsample.Ui.Home.WordListPresenter;

import java.util.List;

/**
 * Defines the contract between the View {@link NewWordActivity} and the Presenter {@link NewWordPresenter}
 */
public interface WordContract {
    interface MvpView {
        void checkResponse(int _responseCode);
    }

    interface Presenter {
        void insertNewWord(Word _mWord);
    }
}