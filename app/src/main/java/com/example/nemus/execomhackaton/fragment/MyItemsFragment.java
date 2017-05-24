package com.example.nemus.execomhackaton.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nemus.execomhackaton.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyItemsFragment extends Fragment {


    public MyItemsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(
                R.layout.recycler_view,
                container, false);
        ContentAdapter adapter = new ContentAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

        int tilePadding = getResources().getDimensionPixelOffset(R.dimen.tile_padding);
        recyclerView.setPadding(tilePadding, tilePadding, tilePadding, tilePadding);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        // Inflate the layout for this fragment
        return recyclerView;

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.fragment_my_items, parent, false));
        }
    }

    public static class ContentAdapter extends RecyclerView.Adapter<ViewHolder>{

        private static final int LENGTH = 18;
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return LENGTH;
        }
    }

}
