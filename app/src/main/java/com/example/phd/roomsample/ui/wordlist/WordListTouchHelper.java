package com.example.phd.roomsample.ui.wordlist;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

public class WordListTouchHelper extends ItemTouchHelper.SimpleCallback {
    private WordListAdapter mWordListAdapter;

    WordListTouchHelper(WordListAdapter _mWordListAdapter) {
        super(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.mWordListAdapter = _mWordListAdapter;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        mWordListAdapter.remove(viewHolder.getAdapterPosition());
    }
}
