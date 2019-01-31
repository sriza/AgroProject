package com.ourproject.mohankumardhakal.agroproject.AcitivityClasses;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.ourproject.mohankumardhakal.agroproject.R;

public class Customer_signin extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    Button sign_in;
    EditText email_text, password_text;
    TextView signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_signin);

        email_text = findViewById(R.id.email_check);
        password_text = findViewById(R.id.password_check);
        sign_in = findViewById(R.id.sign_in);
        signup = findViewById(R.id.sign_up);

        FirebaseApp.initializeApp(this);
        firebaseAuth = FirebaseAuth.getInstance();

        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sign_in();
            }
        });
    }


    private void sign_in() {
        String email = email_text.getText().toString();
        String password = password_text.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplication(), "Please enter your Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplication(), "Password is empty", Toast.LENGTH_SHORT).show();
            return;
        }

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplication(), "Successfully logged in", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplication(), "Email and password not correct ot unregistered. Please check and continue", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    public void red_click(View view) {
        Intent intent = new Intent(this, Register_customer.class);
        startActivity(intent);
    }
}
