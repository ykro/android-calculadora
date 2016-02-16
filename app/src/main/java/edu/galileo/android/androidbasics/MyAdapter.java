package edu.galileo.android.androidbasics;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by ykro on 11/15/14.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>  {
    private String[] dataset;
    private OnItemClickListener clickListener;

    public MyAdapter(String[] dataset) {
        this.dataset = dataset;
    }

    public void setOnItemClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String element = dataset[position];
        holder.txtContent.setText(element);
        if (this.clickListener != null) {
            holder.setOnItemClickListener(element, this.clickListener);
        }
    }

    @Override
    public int getItemCount() {
        return dataset.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private View view;
        public TextView txtContent;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            txtContent = (TextView)view.findViewById(R.id.txtContent);
        }

        public void setOnItemClickListener(final String element,
                                            final OnItemClickListener listener) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(element);
                }
            });

        }
    }
}