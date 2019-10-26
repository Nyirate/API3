package com.example.api1.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.api1.R;


public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    private Button mWatch;
    private EditText editLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWatch = (Button)findViewById(R.id.watch);
        editLocation = (EditText) findViewById(R.id.edit);
        mWatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mov = editLocation.getText().toString();
                Intent intent = new Intent(MainActivity.this,MoviesActivity.class);
                intent.putExtra("location", mov);
                startActivity(intent);
            }


        });
    }

}
