package com.example.phd.roomsample.Ui.WordList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.phd.roomsample.R;
import com.example.phd.roomsample.Room.Tables.Word;

import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    private WordListPresenter mWordListPresenter;
    private final LayoutInflater mInflater;
    private List<Word> mWords; // Cached copy of words

    class WordViewHolder extends RecyclerView.ViewHolder {
        TextView _recyclerview_item_txv_word;

        private WordViewHolder(View itemView) {
            super(itemView);
            _recyclerview_item_txv_word = itemView.findViewById(R.id.recyclerview_item_txv_word);
        }
    }

    WordListAdapter(Context context, WordListPresenter _mWordListPresenter) {
        this.mInflater = LayoutInflater.from(context);
        this.mWordListPresenter = _mWordListPresenter;
    }

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new WordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        if (mWords != null) {
            Word current = mWords.get(position);
            holder._recyclerview_item_txv_word.setText(current.getWord());
        } else {
            // Covers the case of data not being ready yet.
            holder._recyclerview_item_txv_word.setText("No Word");
        }
    }

    void setWords(List<Word> words) {
        mWords = words;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mWords != null)
            return mWords.size();
        else return 0;
    }

    public void remove(int position) {
        mWordListPresenter.delete(mWords.get(position));
    }
}