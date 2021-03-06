package com.ossovita.mydoesapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DoesAdapter extends RecyclerView.Adapter<DoesAdapter.MyViewHolder> {

    Context context;
    private ArrayList<MyDoes> myDoes;

    public DoesAdapter(Context context, ArrayList<MyDoes> myDoes) {
        this.context = context;
        this.myDoes = myDoes;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_does,parent,false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.titleDoes.setText(myDoes.get(position).getTitledoes());
        holder.descDoes.setText(myDoes.get(position).getDescdoes());
        holder.dateDoes.setText(myDoes.get(position).getDatedoes());

        String getTitleDoes = myDoes.get(position).getTitledoes();
        String getDescDoes = myDoes.get(position).getDescdoes();
        String getDateDoes = myDoes.get(position).getDatedoes();
        String getKeyDoes = myDoes.get(position).getKeydoes();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context,EditTaskDesk.class)
                .putExtra("titledoes",getTitleDoes)
                .putExtra("descdoes",getDescDoes)
                .putExtra("datedoes",getDateDoes)
                .putExtra("keydoes",getKeyDoes)
                );
            }
        });

    }

    @Override
    public int getItemCount() {
        return myDoes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView titleDoes,descDoes,dateDoes,keydoes;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titleDoes = itemView.findViewById(R.id.titledoes);
            descDoes = itemView.findViewById(R.id.descdoes);
            dateDoes = itemView.findViewById(R.id.datedoes);
        }
    }




}
