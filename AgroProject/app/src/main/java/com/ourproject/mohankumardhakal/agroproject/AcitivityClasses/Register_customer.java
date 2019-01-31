package com.ourproject.mohankumardhakal.agroproject.AcitivityClasses;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.ourproject.mohankumardhakal.agroproject.R;

public class Register_customer extends AppCompatActivity {
    EditText emailview, passwordview;
    Button registerbtn;
    private ProgressDialog progressDialog;

   private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        emailview = findViewById(R.id.email);
        passwordview = findViewById(R.id.password);
        registerbtn = findViewById(R.id.register);

        progressDialog = new ProgressDialog(this);


        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register(view);

            }

        });
    }

    private void register(final View view)
    {
        String email = emailview.getText().toString();
        String password = passwordview.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplication(), "Please enter the Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplication(), "Please Enter the password", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Registering....");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    progressDialog.hide();
                    Toast.makeText(getApplication(), "Successfully Registered", Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(view.getContext(), Application_main.class);
                    startActivityForResult(myIntent, 0);

                } else {
                    Toast.makeText(getApplication(), "Failed to register, please try again later", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }}
