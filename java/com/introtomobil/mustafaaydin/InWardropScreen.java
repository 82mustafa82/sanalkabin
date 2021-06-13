package com.introtomobil.mustafaaydin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.KeyEvent;

public class InWardropScreen extends AppCompatActivity {
    private RecyclerView ClothestView;

    private java.util.List<Clothes> clothesList;
    private clothesListAdapter clothesListAdapter;
    public int pos;
    private int new_id;


    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_wardrop);

        Intent intent = getIntent();
        android.os.Bundle extras = intent.getExtras();
        pos = extras.getInt("pos");


        clothesList = FileStorageManager.readClothes(this, pos);

        new_id = 0;
        if (clothesList.size()>0)
            new_id = clothesList.get(clothesList.size()-1).getId();

        ClothestView = findViewById(R.id.recycler_1);
        ClothestView.setHasFixedSize(true);
        ClothestView.setLayoutManager(new LinearLayoutManager(this));
        clothesListAdapter = new clothesListAdapter(this, clothesList);
        ClothestView.setAdapter(clothesListAdapter);

        com.google.android.material.floatingactionbutton.FloatingActionButton fab_1 = findViewById(R.id.floatingActionButton);
        fab_1.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                Intent intent = new Intent(getApplicationContext(), AddNewClothes.class);
                intent.putExtra("pos" , pos);
                intent.putExtra("id" , new_id);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}