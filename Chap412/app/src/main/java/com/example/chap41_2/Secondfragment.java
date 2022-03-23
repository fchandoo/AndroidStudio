package com.example.chap41_2;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chap41_2.databinding.SecondFragmentBinding;


public class Secondfragment<SecondFragmentArgs> extends Fragment {

    private ViewModel sViewModel;
    private SecondFragmentBinding sBinding;


    public static Secondfragment newInstance() {
        return new Secondfragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup
            container,
                             @Nullable Bundle savedInstanceState) {

        sBinding = SecondFragmentBinding.inflate(inflater, container, false);
        return sBinding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        sBinding = null;
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onStart() {
        super.onStart();

        SecondFragmentArgs args = SecondFragmentArgs.fromBundle(getArguments());
        String message = args.getMessage();
        sBinding.argText.setText(message);
    }
}