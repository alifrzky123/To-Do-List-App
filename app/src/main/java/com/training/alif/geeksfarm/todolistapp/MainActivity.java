package com.training.alif.geeksfarm.todolistapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
    private EditText etNewItem;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initial();
        fba.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                items = new ArrayList<String>();
                itemsAdapter = new ArrayAdapter<String>(MainActivity.this,
                        android.R.layout.simple_list_item_1, items);
                lvItems.setAdapter(itemsAdapter);
                dialogs();
            }
        });
    }

    public void initial() {
        lvItems = (ListView) findViewById(R.id.lvToDoList);
        fba = (FloatingActionButton) findViewById(R.id.btn_fba);
    }

    public void dialogs(){
        final EditText taskEditText = new EditText(MainActivity.this);
        AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                .setTitle("Add a new task to do")
                .setMessage("What do you want to do next?")
                .setView(taskEditText)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String task = String.valueOf(taskEditText.getText());
                        Log.d(TAG,"Task to add: " + task);
                        String itemText = etNewItem.getText().toString();
                        itemsAdapter.add(itemText);
                        etNewItem.setText("");
                        etNewItem.getText().toString();
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
        dialog.show();
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_task:
                Log.d(TAG, "Add a new task");
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }*/
}
