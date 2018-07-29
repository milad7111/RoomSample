package com.example.phd.roomsample.Ui.Home;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.phd.roomsample.Base.BaseActivity;
import com.example.phd.roomsample.R;
import com.example.phd.roomsample.Room.Daos.WordDao;
import com.example.phd.roomsample.Room.Tables.Word;
import com.example.phd.roomsample.Ui.Word.NewWordActivity;

import java.util.List;

import static com.example.phd.roomsample.Utils.ViewHelper.createSnackBar;

/**
 * Displays WordList View
 */
public class WordListActivity extends BaseActivity implements WordListContract.MvpView, View.OnClickListener {

    //region Declare Objects
    private WordListPresenter mWordListPresenter;
    //endregion Declare Objects

    //region Declare Views
    private RecyclerView recyclerView;
    //endregion Declare Views

    //region Declare Values
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    //endregion Declare Values

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //region Initialize Presenter
        mWordListPresenter = new WordListPresenter(this);
        mWordListPresenter.getAllWords();
        //endregion Initialize Presenter

        //region Initialize Views
        recyclerView = findViewById(R.id.activity_main_rclv_word_list);
        //endregion Initialize Views

        //region Set Events
        findViewById(R.id.activity_main_fab_add_word).setOnClickListener(this);
        //endregion Set Events
    }

    @SuppressLint("DefaultLocale")
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case NEW_WORD_ACTIVITY_REQUEST_CODE:
                switch (resultCode) {
                    case RESULT_OK:
                        createSnackBar(recyclerView.getRootView(), String.format("%d  %s", data.getIntExtra(NewWordActivity.EXTRA_REPLY, 0), getString(R.string.how_many_words_added)), Snackbar.LENGTH_SHORT).show();
                        break;
                    default:
                        createSnackBar(recyclerView.getRootView(), getString(R.string.no_word_add), Snackbar.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_main_fab_add_word:
                startActivityForResult(new Intent(WordListActivity.this, NewWordActivity.class), NEW_WORD_ACTIVITY_REQUEST_CODE);
                break;
        }
    }

    @Override
    public void showAllWords(List<Word> _mAllWords) {
        WordListAdapter adapter = new WordListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setWords(_mAllWords);
    }
}