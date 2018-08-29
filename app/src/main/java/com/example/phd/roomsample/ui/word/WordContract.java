package com.example.phd.roomsample.ui.word;

import com.example.phd.roomsample.room.tables.Word;

/**
 * Defines the contract between the View {@link NewWordActivity} and the Presenter {@link NewWordPresenter}
 */
public interface WordContract {
    interface MvpView {
        void checkResponse(int _responseCode);
    }

    interface Presenter {
        void insertWord(Word _mWord);
    }
}