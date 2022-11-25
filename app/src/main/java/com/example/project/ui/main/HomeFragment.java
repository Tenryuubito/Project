package com.example.project.ui.main;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.project.DataAdapter;
import com.example.project.R;
import com.example.project.databinding.FragmentHomeBinding;

import java.text.DecimalFormat;
import java.util.Locale;
import java.util.Objects;

/**
 * A placeholder fragment containing a simple view.
 */
public class HomeFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    private TextView totalAmount;
    private Button testMoneyMaker;

    public static HomeFragment newInstance(int index) {
        HomeFragment fragment = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        homeViewModel.setIndex(index);
    }

    /**
     * initialize the tab
     */
    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    private void initTab() {
        totalAmount = binding.getRoot().findViewById(R.id.textView_total_amount);
        testMoneyMaker = binding.getRoot().findViewById(R.id.test_button_money_maker);

        totalAmount.setText(homeViewModel.readTotalAmount(totalAmount.getId()));

        testMoneyMaker.setOnClickListener(view -> {
            double amount = Double.parseDouble(totalAmount.getText().toString().split(" ")[0]) * 1.1 + 1;
            totalAmount.setText(String.format("%.2f €", amount).replace(",", "."));
        });
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);

        if (DataAdapter.readSingle(-1).equals(""))
        {
            DataAdapter.writeSingle("0.00 €", R.id.textView_total_amount);
            DataAdapter.writeSingle("pass", -1);
        }

        initTab();

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        homeViewModel.writeTotalAmount(totalAmount.getId(), totalAmount.getText().toString());

        binding = null;
    }
}