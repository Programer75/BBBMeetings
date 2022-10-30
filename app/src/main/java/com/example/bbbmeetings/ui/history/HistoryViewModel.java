package com.example.bbbmeetings.ui.history;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HistoryViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public HistoryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Coming soon.");
    }

    public LiveData<String> getText() {
        return mText;
    }
}