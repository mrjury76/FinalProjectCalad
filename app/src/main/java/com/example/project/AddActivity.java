package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.project.SQLite.DBHandler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AddActivity extends AppCompatActivity {


    private DBHandler dbHandler;
    Button add;
    EditText amount;
    EditText date;
    Spinner typeSpinner;
    static String name;
    static String calorie;
    static String protein;
    static String carb;
    static String fat;
    RecyclerView recyclerView;
    ArrayList<Add_Modal> fruits = new ArrayList<>();
    ArrayList<Add_Modal> vegetables = new ArrayList<>();
    ArrayList<Add_Modal> meats = new ArrayList<>();
    ArrayList<Add_Modal> dairies =new ArrayList<>();
    ArrayList<Add_Modal> spices =new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        dbHandler = new DBHandler(AddActivity.this);
        add = findViewById(R.id.button);
        //name = findViewById(R.id.AddName);
        amount =findViewById(R.id.AddAmount);
        date = findViewById(R.id.AddDate);
        typeSpinner = findViewById(R.id.AddTypeSpinner);
        recyclerView = findViewById(R.id.AddRecycler);

        String[] types = {"Fruits", "Vegetables", "Meats"};

        fruits.add(new Add_Modal("Apple", "20", R.drawable.deselected, "0", "5", "0"));
        fruits.add(new Add_Modal("Orange", "10", R.drawable.deselected, "0", "5", "0"));
        fruits.add(new Add_Modal("Watermelon", "40", R.drawable.deselected, "0", "5", "0"));
        vegetables.add(new Add_Modal("Brocolli", "5", R.drawable.deselected, "0", "5", "0"));
        vegetables.add(new Add_Modal("Peas", "10", R.drawable.deselected, "0", "5", "0"));
        meats.add(new Add_Modal("Chicken", "50", R.drawable.deselected, "0", "5", "0"));
        meats.add(new Add_Modal("Beef", "100", R.drawable.deselected, "0", "5", "0"));
        meats.add(new Add_Modal("Pork", "200", R.drawable.deselected, "0", "5", "0"));

        Add_Adapter meatAdapter = new Add_Adapter(this, meats);
        Add_Adapter vegetableAdapter = new Add_Adapter(this, vegetables);
        Add_Adapter fruitAdapter = new Add_Adapter(this, fruits);

        recyclerView.setAdapter(fruitAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        ArrayAdapter<String> typeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, types);
        typeSpinner.setAdapter(typeAdapter);

        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = parent.getItemAtPosition(position).toString();
                switch (selected){
                    case "Fruits":{
                        recyclerView.setAdapter(fruitAdapter);
                        break;
                    }
                    case "Vegetables":{
                        recyclerView.setAdapter(vegetableAdapter);
                        break;
                    }
                    case "Meats":{
                        recyclerView.setAdapter(meatAdapter);
                        break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ItemName = name;
                String ItemAmount = amount.getText().toString();
                String ItemDate = date.getText().toString();
                String ItemCalories = calorie;
                String ItemProtein = protein;
                String ItemCarb = carb;
                String ItemFat = fat;

                if (ItemName.isEmpty() && ItemAmount.isEmpty() && ItemDate.isEmpty() && ItemProtein.isEmpty() && ItemCarb.isEmpty() && ItemFat.isEmpty()) {
                    Toast.makeText(AddActivity.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }

                dbHandler.addNewItem(ItemDate, ItemName, ItemAmount, ItemCalories, ItemProtein, ItemCarb, ItemFat);

                Toast.makeText(AddActivity.this, "Item has been added. \nName: " + ItemName + "Date: " + ItemDate, Toast.LENGTH_SHORT).show();
                amount.setText("");
                date.setText("");
            }
        });
        Intent intent = getIntent();
    }
}
