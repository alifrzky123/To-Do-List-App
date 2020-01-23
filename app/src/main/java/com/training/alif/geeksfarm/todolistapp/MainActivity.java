package com.training.alif.geeksfarm.todolistapp;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> items;
    private ArrayAdapter<String> itemsAdapter;
    private ListView lvItems;
    private FloatingActionButton fba;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initial();
        lvItems.setAdapter(itemsAdapter);

        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final int which_pos = position;
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Have you done finished ")
                        .setMessage("Beneran sudah dikerjakan?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                items.remove(which_pos);
                                itemsAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("Belum" , null).show();

                return false;
            }
        });

        fba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItems();
            }
        });
    }

    public void initial() {
        items = new ArrayList<String>();
        itemsAdapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, items);
        lvItems = (ListView) findViewById(R.id.lvToDoList);
        fba = (FloatingActionButton) findViewById(R.id.btn_fba);
    }

    public void addItems(){
        final EditText taskEditText = new EditText(MainActivity.this);
        AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                .setTitle("Add a new task to do")
                .setMessage("What do you want to do next?")
                .setView(taskEditText)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                        items.add(taskEditText.getText().toString());
                        itemsAdapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
        dialog.show();
    }
    public void addToSh(){
        SharedPreferences share = getSharedPreferences("to do" , MODE_PRIVATE);
        SharedPreferences.Editor Edit = share.edit();
        String k = String.valueOf(key);
        edit.putString(k, items);
        edit.apply();

    }
}
