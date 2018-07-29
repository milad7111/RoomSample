package com.example.phd.roomsample.Ui.WordList;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.support.annotation.NonNull;

import com.example.phd.roomsample.Room.Tables.Word;

import java.util.List;

/**
 * Defines the contract between the View {@link WordListActivity} and the Presenter {@link WordListPresenter}
 */
public interface WordListContract {
    interface MvpView extends LifecycleOwner {
        void showAllWords(List<Word> _mAllWords);

        @NonNull
        @Override
        Lifecycle getLifecycle();
    }

    interface Presenter {
        void getAllWords();
        void delete(Word _mWord);
    }
}