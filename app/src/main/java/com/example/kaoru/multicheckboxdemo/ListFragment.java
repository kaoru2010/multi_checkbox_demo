package com.example.kaoru.multicheckboxdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by kaoru on 2015/06/27.
 */
public class ListFragment extends Fragment {
    private ListAdapter mListAdapter = new MyAdapter((MyModel)ModelStore.get(ModelKey.MY_MODEL));

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View containerLayout = inflater.inflate(R.layout.fragment_main, container, false);
        configureListView(containerLayout, savedInstanceState);
        return containerLayout;
    }

    /**
     * Configure the list view.
     * The second argument savedInstanceState is just ignored because state can be restored from the models.
     *
     * @param containerLayout Container layout
     * @param savedInstanceState Bundle saved instance state
     */
    private void configureListView(View containerLayout, @Nullable Bundle savedInstanceState) {
        View view = containerLayout.findViewById(R.id.list_view);
        if (view instanceof ListView) {
            ((ListView) view).setAdapter(mListAdapter);
        }
    }
}
