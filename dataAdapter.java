package com.example.mani.realtim;

/**
 * Created by mani on 29/12/2017.
 */
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class dataAdapter extends RecyclerView.Adapter<dataAdapter.MyViewHolder> {
    Context context;
    public ArrayList<datamodel> dataSet;
           Needy NI;
    public static class MyViewHolder extends RecyclerView.ViewHolder {
ImageView img;
        TextView textViewName;
        TextView ame;

        Needy NI;
        public MyViewHolder(View itemView) {
            super(itemView);

            this.img = (ImageView) itemView.findViewById(R.id.img);
            this.textViewName = (TextView) itemView.findViewById(R.id.namess);

            this.ame = (TextView) itemView.findViewById(R.id.kindofhelp);
itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                  //
                  //
                  Needyshow.s=NI;
                   Intent intent = new Intent(v.getContext(), Needyshow.class);
                   v.getContext().startActivity(intent);

                }
            });
        }
    }

    public dataAdapter( ArrayList<datamodel> data,Context cont) {
        this.dataSet = data;
        this.context=cont;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cards_layout, parent, false);



        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView textViewName = holder.textViewName;
        TextView ame = holder.ame;
     ImageView img= holder.img;
        img.setImageBitmap(BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.clothes));
       Needy s=dataSet.get(listPosition).getN();
        holder.NI=s;
          String statuss=dataSet.get(listPosition).getStatus();;
        String kohhh=dataSet.get(listPosition).getKohh();
ame.setText(kohhh);



            if (dataSet.get(listPosition).getKohh().equals("Clothes") || kohhh == "Clothes") {
                img.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.clothes));

            }
            if (dataSet.get(listPosition).getKohh().equals("Food") || kohhh == "Food") {
                img.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.food));

            }
            if (dataSet.get(listPosition).getKohh().equals("Service") || kohhh == "Service") {
                img.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.service));

            }
            if (dataSet.get(listPosition).getKohh().equals("Emergency") || kohhh == "Emergency") {
                img.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.emergency));

            }
            if (dataSet.get(listPosition).getKohh().equals("Money") || kohhh == "Money") {
                img.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.money));

            }
            if (dataSet.get(listPosition).getKohh().equals("Books") || kohhh == "Books") {
                img.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.books));

            }
            if (dataSet.get(listPosition).getKohh().equals("Blood") || kohhh == "Blood") {
                img.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.blood));

            }
            if (dataSet.get(listPosition).getKohh().equals("Fire") || kohhh == "Fire") {
                img.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.fire));
            }
            if (dataSet.get(listPosition).getStatus().equals("0") || statuss == "0") {
               textViewName.setText(dataSet.get(listPosition).getNames() + " " + dataSet.get(listPosition).getSurnames() + " is not helped yet");
            }
            if (dataSet.get(listPosition).getStatus().equals("1") || statuss == "1") {
                textViewName.setText(dataSet.get(listPosition).getNames() + " " + dataSet.get(listPosition).getSurnames() + " is  partially helped");

            }
            if (dataSet.get(listPosition).getStatus().equals("2") || statuss == "2") {
                textViewName.setText(dataSet.get(listPosition).getNames() + " " + dataSet.get(listPosition).getSurnames() + " is completely helped");


        }
        textViewName.setText(dataSet.get(listPosition).getDohh() );
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }}