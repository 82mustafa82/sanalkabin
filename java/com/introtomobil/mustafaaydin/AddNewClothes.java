package com.introtomobil.mustafaaydin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class AddNewClothes extends AppCompatActivity {

    Button create, dateSelector;
    EditText nameT, typeT, colorT, patternT, priceT, dateT;
    private int pos;

    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_clothes);

        Intent intent = getIntent();
        android.os.Bundle extras = intent.getExtras();
        pos = extras.getInt("pos");
        int new_id = extras.getInt("id");

        create = findViewById(R.id.create_clothes_button);
        nameT = findViewById(R.id.name);
        typeT = findViewById(R.id.type);
        colorT = findViewById(R.id.color);
        patternT = findViewById(R.id.pattern);
        priceT = findViewById(R.id.price);
        dateT = findViewById(R.id.date);
        dateSelector = findViewById(R.id.select_date);
        create.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                String name = nameT.getText().toString();
                String type = typeT.getText().toString();
                String color = colorT.getText().toString();
                String pattern = patternT.getText().toString();
                String priceString=priceT.getText().toString();
                if (priceString.isEmpty())
                    priceString = "0";
                Float price = Float.valueOf(priceString);
                String date = dateT.getText().toString();



                Clothes clothes = new Clothes(new_id, name, type, color, pattern, price, date);

                FileStorageManager.addClothes(AddNewClothes.this, pos, clothes);
                Toast.makeText(AddNewClothes.this.getApplicationContext(),"Clothes has been created.",Toast.LENGTH_SHORT).show();

                Intent a = new Intent(AddNewClothes.this, InWardropScreen.class);
                a.putExtra("pos" , pos);
                startActivity(a);


            }
        });
        dateSelector.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddNewClothes.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                dateT.setText(day + "/" + (month + 1) + "/" + year);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.show();

            }
        });
    }

}