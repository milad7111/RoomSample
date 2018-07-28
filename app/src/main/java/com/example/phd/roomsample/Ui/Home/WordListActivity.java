package com.example.phd.roomsample.Ui.Home;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.phd.roomsample.Models.WordViewModel;
import com.example.phd.roomsample.R;
import com.example.phd.roomsample.Room.Tables.Word;
import com.example.phd.roomsample.Ui.Word.NewWordActivity;

import java.util.List;

public class WordListActivity extends AppCompatActivity implements View.OnClickListener {

    private WordViewModel mWordViewModel;
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //region Declare RecyclerView and Initialize it
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final WordListAdapter adapter = new WordListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //endregion Declare RecyclerView and Initialize it

        //region Initialize Views
        findViewById(R.id.activity_main_fab_add_word).setOnClickListener(this);
        //endregion Initialize Views

        mWordViewModel = ViewModelProviders.of(this).get(WordViewModel.class);
        mWordViewModel.getAllWords().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(@Nullable final List<Word> words) {
                // Update the cached copy of the words in the adapter.
                adapter.setWords(words);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case NEW_WORD_ACTIVITY_REQUEST_CODE:
                switch (resultCode) {
                    case RESULT_OK:
                        mWordViewModel.insert(new Word(data.getStringExtra(NewWordActivity.EXTRA_REPLY)));
                        break;
                    default:
                        Toast.makeText(getApplicationContext(), R.string.empty_not_saved, Toast.LENGTH_LONG).show();
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
}
