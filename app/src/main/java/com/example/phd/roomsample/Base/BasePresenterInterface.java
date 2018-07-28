package com.example.phd.roomsample.Base;

public interface BasePresenterInterface<V extends BaseInterface> {
    void onAttach(V BaseInterface);
}