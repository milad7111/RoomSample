package com.example.phd.roomsample.Base;

public class BasePresenterClass <V extends BaseInterface> implements BasePresenterInterface<V> {

    private V mBaseInterface;

    public BasePresenterClass() {
    }

    @Override
    public void onAttach(V BaseInterface) {
        this.mBaseInterface = BaseInterface;
    }

    public V getBaseInterface() {
        return mBaseInterface;
    }
}