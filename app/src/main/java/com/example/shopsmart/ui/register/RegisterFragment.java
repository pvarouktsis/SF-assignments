package com.example.shopsmart.ui.register;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.shopsmart.R;

public class RegisterFragment extends Fragment {

    private RegisterViewModel registerViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //registerViewModel = ViewModelProviders.of(this).get(RegisterViewModel.class);
        View register_view = inflater.inflate(R.layout.fragment_register, container, false);
        return register_view;
    }
}