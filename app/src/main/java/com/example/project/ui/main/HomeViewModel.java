package com.example.project.ui.main;

import android.provider.ContactsContract;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.project.DataAdapter;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<Integer> mIndex = new MutableLiveData<>();

    public void setIndex(int index) {
        mIndex.setValue(index);
    }

    public String readTotalAmount(int _ID)
    {
        return DataAdapter.readSingle(_ID);
    }

    public void writeTotalAmount(int _ID, String data)
    {
        String amount = data.split(" ")[0];
        DataAdapter.writeSingle(data, _ID);
    }

}