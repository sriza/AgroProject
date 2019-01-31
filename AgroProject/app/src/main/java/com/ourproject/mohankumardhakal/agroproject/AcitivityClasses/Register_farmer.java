package com.ourproject.mohankumardhakal.agroproject.AcitivityClasses;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.ourproject.mohankumardhakal.agroproject.R;

public class Register_farmer extends AppCompatActivity {
    EditText emailview, farmname, passwordview;
    Button registerbtn;
    private String userID;
    private ProgressDialog progressDialog;


    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_farmer);


        emailview = findViewById(R.id.email);
        farmname = findViewById(R.id.farmname);
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

    private void register(final View view) {
        String email = emailview.getText().toString();
        String fname = farmname.getText().toString();
        String password = passwordview.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplication(), "Please enter the Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(fname)) {
            Toast.makeText(getApplication(), "Please Enter the password", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplication(), "Please Enter the password", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Registering....");
        progressDialog.show();

        registerNewEmail(email,fname,password);



    }

    public void registerNewEmail(final String email, String password, final String username) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        // If sign in fails, display a message to the user. If sign in succeeds

                        if (!task.isSuccessful()) {
                            Toast.makeText(getApplication(), "Failed to authenticate.",
                                    Toast.LENGTH_SHORT).show();

                        } else if (task.isSuccessful()) {
                            //send verificaton email

                            userID = firebaseAuth.getCurrentUser().getUid();
                        }

                    }
                });



    }

}