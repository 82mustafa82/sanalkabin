package com.introtomobil.mustafaaydin;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class WardropListAdapter extends RecyclerView.Adapter<WardropListAdapter.WardropViewHolder> {


    private java.util.List<Wardrop> wardropsList;
    private final Context mCtx;
    private Wardrop wardrop;

    public WardropListAdapter(Context mCtx, java.util.List<Wardrop> wardropsList) {
        this.wardropsList = wardropsList;
        this.mCtx = mCtx;
    }

    @androidx.annotation.NonNull
    @Override
    public WardropViewHolder onCreateViewHolder(@androidx.annotation.NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        android.view.View view = inflater.inflate(R.layout.wardrop_list, null);
        return new WardropViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@androidx.annotation.NonNull WardropViewHolder holder, int position) {

        if (position < wardropsList.size()){
            wardrop = wardropsList.get(position);
            holder.textV.setText(wardrop.getName());
            holder.actionButton.setText("Delete Wardrop");
        }
        else{
            holder.input.setVisibility(android.view.View.VISIBLE);
            holder.actionButton.setText("Create Wardrop");
        }

        holder.self.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                Intent intent = new Intent(mCtx, InWardropScreen.class);
                intent.putExtra("pos" , position);
                mCtx.startActivity(intent);
            }
        });


        holder.actionButton.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                if(position >= wardropsList.size()){
                    String name = holder.input.getText().toString();
                    int id = 0;
                    int prevSize = wardropsList.size();
                    if(prevSize>0) {
                       id =  wardropsList.get(prevSize-1).getId()+1;
                   }
                    Wardrop wardrop = new Wardrop(id, name);
                    FileStorageManager.addWardrops(mCtx, wardrop);
                    wardropsList.add(wardrop);
                    holder.input.setText("");
                    holder.input.setHint("type new wardop name");
                    notifyItemRangeInserted(prevSize,wardropsList.size()-prevSize);

                }
                else
                {
                    new AlertDialog.Builder(mCtx)
                            .setTitle("Clothes will be delete")
                            .setMessage("Are you sure ? ")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    FileStorageManager.removeWardrop(mCtx, wardropsList.get(position).getId());
                                    wardropsList.remove(wardrop);
                                    Toast.makeText(mCtx.getApplicationContext(),"Wardrop has been deleted.",Toast.LENGTH_SHORT).show();
                                    notifyItemRemoved(position);

                                }
                            }).setNegativeButton("No", null).show();
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return wardropsList.size()+1;
    }

    public class WardropViewHolder extends RecyclerView.ViewHolder {
        android.widget.TextView textV;
        EditText input;
        Button actionButton;
        ConstraintLayout self;
        public WardropViewHolder(@androidx.annotation.NonNull android.view.View itemView) {
            super(itemView);
            textV = itemView.findViewById(R.id.wardropText);
            input = itemView.findViewById(R.id.input);
            actionButton = itemView.findViewById(R.id.delete_or_addBtn);
            self = itemView.findViewById(R.id.goClothes);

        }
    }
}
