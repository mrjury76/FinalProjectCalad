package com.example.project;

import static com.google.android.material.internal.ContextUtils.getActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Add_Adapter extends RecyclerView.Adapter<Add_Adapter.View_Holder> {
    Context context;
    //we have created our own structure that is a Contact_modal.
    // So our arraylist will be of type Contact_modal
    ArrayList<Add_Modal> arrayList;
    boolean[] selected = {false};

    //Generate Constructor
   Add_Adapter (Context context, ArrayList<Add_Modal> arrayList){
        this.context=context;
        this.arrayList=arrayList;
    }
    @NonNull
    @Override
    public View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate the view
        View view= LayoutInflater.from(context).inflate(R.layout.add_row,parent,false);
        // Layout:layout that we want to display
        // parent – The ViewGroup into which the new View will be added
        // after it is bound to an adapter position.
        View_Holder viewHolder=new View_Holder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull View_Holder holder, int position) {
        // Bind the data
        //set the values of imageview and both textViews
        holder.name.setText(arrayList.get(position).name);
        holder.calories.setText(arrayList.get(position).calories);
        final int[] prevPosition = {position};


        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (!selected[0]) {
                    int selectedPosition = holder.getAdapterPosition();
                    AddActivity.name = arrayList.get(selectedPosition).name;
                    AddActivity.calorie = arrayList.get(selectedPosition).calories;
                    AddActivity.protein = arrayList.get(selectedPosition).protein;
                    AddActivity.carb = arrayList.get(selectedPosition).carb;
                    AddActivity.fat = arrayList.get(selectedPosition).fat;
                    holder.img.setImageResource(R.drawable.selected);
                    selected[0] = true;
                    return true;
                }
                else
                {
                    Toast.makeText(context, "You can only select one item at a time", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(selected[0]) {
                    int selectedPosition = holder.getAdapterPosition();
                    holder.img.setImageResource(R.drawable.deselected);
                    selected[0] = false;
                }
            }
        });




    }

    @Override
    public int getItemCount()
    {
        return arrayList.size();
    }
    public class View_Holder extends RecyclerView.ViewHolder{
        TextView name, calories;
        ImageView img;
        public View_Holder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.item_row_name);
            calories=itemView.findViewById(R.id.item_row_calories);
            img = itemView.findViewById(R.id.item_bg);
        }
    }


}
