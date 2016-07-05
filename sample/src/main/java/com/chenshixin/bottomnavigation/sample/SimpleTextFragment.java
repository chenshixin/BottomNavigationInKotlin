package com.chenshixin.bottomnavigation.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by chenshixin on 7/5/16.
 */
public class SimpleTextFragment extends Fragment{

    public static final String KEY_TEXT = "key_text";

    public static SimpleTextFragment newInstance(String text) {

        Bundle args = new Bundle();
        args.putString(KEY_TEXT, text);
        SimpleTextFragment fragment = new SimpleTextFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_simple, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView textView = (TextView) view.findViewById(R.id.simpleFragmentText);
        textView.setText(getArguments().getString(KEY_TEXT));
    }
}
