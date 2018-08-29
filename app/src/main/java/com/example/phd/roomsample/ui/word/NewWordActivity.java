package com.example.phd.roomsample.ui.word;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.example.phd.roomsample.base.BaseActivity;
import com.example.phd.roomsample.R;
import com.example.phd.roomsample.room.tables.Word;

import static com.example.phd.roomsample.utils.ViewHelper.createSnackBar;

public class NewWordActivity extends BaseActivity implements WordContract.MvpView, View.OnClickListener {

    //region Declare Objects
    private NewWordPresenter mWordPresenter;
    //endregion Declare Objects

    //region Declare Views
    private EditText etNewWord;
    //endregion Declare Views

    //region Declare Values
    public static final String EXTRA_REPLY = "countOfAddedWords";
    private int mCount;
    //endregion Declare Values

    private void initViews(){

        //region Initialize Views
        etNewWord = findViewById(R.id.activity_new_word_edtx_word);
        //endregion Initialize Views

        //region Set Events
        findViewById(R.id.activity_new_word_btn_save).setOnClickListener(this);
        //endregion Set Events
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);

        //region Initialize Presenter
        mWordPresenter = new NewWordPresenter(this);
        //endregion Initialize Presenter

        //region Initialize Values
        mCount = 0;
        //endregion Initialize Values

        initViews();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_new_word_btn_save:
                if (TextUtils.isEmpty(etNewWord.getText())) {
                    checkResponse(0);
                } else {
                    mWordPresenter.insertWord(new Word(etNewWord.getText().toString()));
                }
                break;
        }
    }

    @Override
    public void checkResponse(int _responseCode) {
        switch (_responseCode) {
            case 0:
                createSnackBar(etNewWord, getString(R.string.empty_not_saved), Snackbar.LENGTH_LONG).show();
                break;
            case 1:
                etNewWord.setText(null);
                mCount++;
                createSnackBar(etNewWord, getString(R.string.word_added), Snackbar.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        Intent replyIntent = new Intent();
        switch (mCount) {
            case 0:
                setResult(RESULT_CANCELED, replyIntent);
                break;
            default:
                replyIntent.putExtra(EXTRA_REPLY, mCount);
                setResult(RESULT_OK, replyIntent);
        }

        super.onBackPressed();
    }
}