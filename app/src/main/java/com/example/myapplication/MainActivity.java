package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private SwitchCompat switchButton;
    private EditText editText;
    private Button editTextBtn;
    private AutoCompleteTextView autoCompleteTextView;
    private Button autoCompleteBtn;
    private Spinner spinner;
    private RadioGroup radioGroup;
    private Button dropdownBtn;
    private AutoCompleteTextView autoCompleteTextViewMenu;
    private TextView textViewPress;
    private Button secondActivityBtn;
    private Button gridViewActivityBtn;
    private MediaPlayer sound;

    @Override
    protected void onPause() {
        super.onPause();
        sound.release();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switchButton = findViewById(R.id.toggleButton);
        editText = findViewById(R.id.editText);
        editTextBtn = findViewById(R.id.editTextButton);
        autoCompleteTextView = findViewById(R.id.autoComplete);
        autoCompleteBtn = findViewById(R.id.autoCompleteButton);
        spinner = findViewById(R.id.spinner);
        radioGroup = findViewById(R.id.radioGroup);
        dropdownBtn = findViewById(R.id.popupMenu);
        autoCompleteTextViewMenu = findViewById(R.id.autoCompleteMenu);
        secondActivityBtn = findViewById(R.id.activityChange);
        gridViewActivityBtn = findViewById(R.id.gridView);
        sound = MediaPlayer.create(this, R.raw.song);

        switchButton.setChecked(false);

        switchButton.setOnCheckedChangeListener((compoundButton, checked) -> {
            if(checked) {
                switchButton.setText(R.string.toggle_turn_on);
            } else {
                switchButton.setText(R.string.toggle_turn_off);
            }
        });

        editTextBtn.setOnClickListener(view -> {
            String string = editText.getText().toString();
            Toast message = Toast.makeText(getBaseContext(), string, Toast.LENGTH_LONG);
            message.show();
        });

        int[] myArray = {10, 20, 30, 40, 50};
        Log.v("LogExample", Arrays.toString(myArray));

        String[] fruits = {"Banana", "Apple", "Kiwi", "Grape", "Watermelon", "Orange", "Pineapple"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.select_dialog_item, fruits);
        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setAdapter(adapter);

        autoCompleteBtn.setOnClickListener(view -> {
           String string = autoCompleteTextView.getText().toString();
           Toast message = Toast.makeText(getBaseContext(), string, Toast.LENGTH_LONG);
           message.show();
        });

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String item = adapterView.getItemAtPosition(position).toString();

                Toast.makeText(adapterView.getContext(), item, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        radioGroup.setOnCheckedChangeListener((radioGroup, id) -> {
            RadioButton radioButton = radioGroup.findViewById(id);
            if(null != radioButton && id > -1) {
                Toast.makeText(this, radioButton.getText(), Toast.LENGTH_SHORT).show();
            }
        });

        dropdownBtn.setOnClickListener(view -> {
            PopupMenu popupMenu = new PopupMenu(MainActivity.this, dropdownBtn);
            popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());

            popupMenu.setOnMenuItemClickListener(menuItem -> {
                Toast.makeText(MainActivity.this, menuItem.getTitle(), Toast.LENGTH_LONG).show();
                return true;
            });
            popupMenu.show();
        });

        ArrayAdapter<String> adapterItems = new ArrayAdapter<>(this, R.layout.list_item, fruits);
        autoCompleteTextViewMenu.setAdapter(adapterItems);

        autoCompleteTextViewMenu.setOnItemClickListener((adapterView, view, position, id) -> {
            String item = adapterView.getItemAtPosition(position).toString();
            Toast.makeText(adapterView.getContext(), item, Toast.LENGTH_LONG).show();
        });

        textViewPress = findViewById(R.id.textViewPress);
        textViewPress.setOnLongClickListener(view -> {
            Toast.makeText(getBaseContext(), "You have pressed it long", Toast.LENGTH_SHORT).show();
            return true;
        });

        secondActivityBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, SecondActivity.class);
            startActivity(intent);
        });

        gridViewActivityBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, GridActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.overflow_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.item_teste1) {
            Toast.makeText(this, "Item Teste1 selected", Toast.LENGTH_LONG).show();
        } else if(item.getItemId() == R.id.item_teste2) {
            Toast.makeText(this, "Item Teste2 selected", Toast.LENGTH_LONG).show();
        } else {
            throw new IllegalStateException("Unexpected value: " + item.getItemId());
        }

        return true;
    }

    public void playMusic(View view) {
        sound.start();
    }
}