package com.example.phd.roomsample.ui.wordlist;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.support.annotation.NonNull;

import com.example.phd.roomsample.room.tables.Definition;
import com.example.phd.roomsample.room.tables.Word;

import java.util.List;

/**
 * Defines the contract between the View {@link WordListActivity} and the Presenter {@link WordListPresenter}
 */
public interface WordListContract {
    interface MvpView extends LifecycleOwner {
        void showAllWords(List<Word> _mAllWords);
        void showDefinitionsByWord(List<Definition> _mDefinitions);
        void deleteWord(Word _mWord);
        void getDefinitionsByWord(Word _mWord);

        @NonNull
        @Override
        Lifecycle getLifecycle();
    }

    interface Presenter {
        void getAllWords();
        void deleteWord(Word _mWord);
        void getDefinitionsByWord(Word _mWord);
    }
}