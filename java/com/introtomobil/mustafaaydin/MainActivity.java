package com.introtomobil.mustafaaydin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView WardropView;

    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        java.util.List<Wardrop> WardropList = FileStorageManager.readWardrops(this);

        WardropView = findViewById(R.id.rv2);
        WardropView.setHasFixedSize(true);
        WardropView.setLayoutManager(new LinearLayoutManager(this));
        WardropListAdapter wardropListAdapter = new WardropListAdapter(this, WardropList);
        WardropView.setAdapter(wardropListAdapter);
    }
}