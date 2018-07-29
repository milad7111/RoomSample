package com.example.phd.roomsample.Ui.Word;

import com.example.phd.roomsample.Room.Tables.Word;

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