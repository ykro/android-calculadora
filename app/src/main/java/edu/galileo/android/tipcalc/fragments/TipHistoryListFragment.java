package edu.galileo.android.tipcalc.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.galileo.android.tipcalc.R;
import edu.galileo.android.tipcalc.adapter.CustomRecyclerAdapter;
import edu.galileo.android.tipcalc.adapter.OnItemClickListener;


public class TipHistoryListFragment extends Fragment {
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    private CustomRecyclerAdapter adapter;

    public TipHistoryListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initAdapter();
        initRecyclerView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tip_history_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    private void initAdapter() {
        adapter = new CustomRecyclerAdapter();
        OnItemClickListener listener = (OnItemClickListener)getActivity();
        adapter.setOnItemClickListener(listener);
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }
}
