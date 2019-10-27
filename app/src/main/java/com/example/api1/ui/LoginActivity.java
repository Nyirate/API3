package com.example.api1.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.example.api1.R;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    /*@BindView(R.id.register)
    TextView mRegister;*/
    @BindView(R.id.login)
    Button mButton;
    @BindView(R.id.email)
    EditText mEmail;
    @BindView(R.id.user)
    EditText mUser;
    @BindView(R.id.password)
    EditText mPassword;
    private FirebaseAuth mAuthentication;
    private String TAG;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private ProgressDialog mAuthProgressDialog;


        private TextView mRegister;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);
            mRegister = (TextView) findViewById(R.id.register);
            mRegister.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            if (v == mRegister) {
                Intent intent = new Intent(LoginActivity.this, CreateAccountActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }