package com.my_sokhra.selibrah.mysokhra.ui.share;

import android.content.Intent;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.my_sokhra.selibrah.mysokhra.LoginActivity;
import com.my_sokhra.selibrah.mysokhra.Signup;

public class ShareViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ShareViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is share fragment");

    }

    public LiveData<String> getText() {
        return mText;
    }
}