package com.introtomobil.mustafaaydin;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

public class clothesListAdapter extends RecyclerView.Adapter<clothesListAdapter.ClothesViewHolder> {


    private java.util.List<Clothes> clothesList;
    private final Context mCtx;

    public clothesListAdapter(Context mCtx, java.util.List<Clothes> clothesList) {
        this.clothesList = clothesList;
        this.mCtx = mCtx;
    }

    @androidx.annotation.NonNull
    @Override
    public ClothesViewHolder onCreateViewHolder(@androidx.annotation.NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        android.view.View view = inflater.inflate(R.layout.clothes_list, null);
        return new ClothesViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@androidx.annotation.NonNull ClothesViewHolder holder, int position) {
        Clothes clothes = clothesList.get(position);
        holder.textV.setText(clothes.toString());

        holder.bDelete.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {

                new AlertDialog.Builder(mCtx)
                        .setTitle("Clothes will be delete")
                        .setMessage("Are you sure ? ")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                FileStorageManager.removeClothes(mCtx, ((InWardropScreen)mCtx).pos, clothesList.get(position).getId() );
                                Toast.makeText(mCtx.getApplicationContext(),"Clothes has been deleted.",Toast.LENGTH_SHORT).show();
                                clothesList.remove(clothes);
                                notifyItemRemoved(position);

                            }
                        }).setNegativeButton("No", null).show();
                }
        });


    }

    @Override
    public int getItemCount() {
        return clothesList.size();
    }

    public class ClothesViewHolder extends RecyclerView.ViewHolder {
        android.widget.TextView textV;
        Button bDelete;
        public ClothesViewHolder(@androidx.annotation.NonNull android.view.View itemView) {
            super(itemView);
            textV = itemView.findViewById(R.id.clothesText);
            bDelete = itemView.findViewById(R.id.button_delete);

        }
    }
}
