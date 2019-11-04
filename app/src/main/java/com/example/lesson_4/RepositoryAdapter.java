package com.example.lesson_4;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static android.app.Activity.RESULT_OK;

public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder> {

    Context context;
    private ArrayList<RepositoryItem> items;
    private Activity activity;


    RepositoryAdapter (List<String> repositoryes, Activity activity) {
        initItems(repositoryes);
        this.activity = activity;
    }

    @NonNull
    @Override
    public RepositoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.repository_item, parent,
                false);

        return new RepositoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RepositoryViewHolder holder, int position) {
        setItem(position, holder);
        setOnItemClickListener(position, holder.itemView);

    }

    private void setItem(int position, RepositoryViewHolder holder) {
        holder.textName.setText(items.get(position).textName);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private void setOnItemClickListener(final int position, View mainView) {
        mainView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("repository", items.get(position).textName);
                activity.setResult(RESULT_OK, intent);
                activity.finish();
            }
        });
    }

    void addItem(RepositoryItem item) {
        items.add(item);
        notifyItemInserted(items.size()-1);
    }

    private void initItems (List<String> repositoryes) {
        items = new ArrayList<RepositoryItem>(repositoryes.size());
        for (int i = 0; i < repositoryes.size(); i++) {

            items.add(new RepositoryItem());
            items.get(i).textName = repositoryes.get(i);
        }
    }
    class RepositoryViewHolder extends RecyclerView.ViewHolder {

        TextView textName;

        public RepositoryViewHolder(View itemView) {
            super(itemView);
            initViews(itemView);
        }

        private void initViews(View itemView) {
            textName = itemView.findViewById(R.id.textName);
        }
    }
}
