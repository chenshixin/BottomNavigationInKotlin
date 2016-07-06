package com.chenshixin.bottomnavigation.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chenshixin.bottomnavigation.BottomNavigationBar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenshixin on 7/5/16.
 */
public class SimpleTextFragment extends Fragment implements BottomNavigationBar.DoubleTapToScrollTop {

    public static final String KEY_TEXT = "key_text";

    public static SimpleTextFragment newInstance(String text) {

        Bundle args = new Bundle();
        args.putString(KEY_TEXT, text);
        SimpleTextFragment fragment = new SimpleTextFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_simple, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerView(view);
    }

    private void initRecyclerView(View parent) {
        mRecyclerView = (RecyclerView) parent.findViewById(R.id.simpleRV);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        final List<String> data = getData();
        mRecyclerView.setAdapter(new RecyclerView.Adapter<ViewHolder>() {

            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_simple_text, parent, false);
                return new ViewHolder(view);
            }

            @Override
            public void onBindViewHolder(ViewHolder holder, int position) {
                holder.textView.setText(data.get(position));
            }

            @Override
            public int getItemCount() {
                return data.size();
            }
        });
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.simpleText);
        }
    }

    private List<String> getData() {
        String text = getArguments().getString(KEY_TEXT);
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add(text + " " + i);
        }
        return data;
    }

    @Override
    public void scrollToTop() {
        if (mRecyclerView != null) {
            LinearLayoutManager layoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
            layoutManager.scrollToPosition(0);
        }
    }
}
