package com.example.assessment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Registration extends AppCompatActivity {

    TextView signUpto;
    EditText sname, fname, email, password, cPassword;
    MaterialButton signUp;
    ProgressDialog progressDialog;

    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        sname = findViewById(R.id.sname);
        fname = findViewById(R.id.fname);
        email = findViewById(R.id.Email);
        password = findViewById(R.id.Password);
        cPassword = findViewById(R.id.cPassword);
        signUp = findViewById(R.id.signUp);
        signUpto = findViewById(R.id.signUpto);

        signUpto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registration.this, Login.class);
                startActivity(intent);
            }
        });

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Creating Account...");


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mSname = sname.getText().toString();
                String mFname = fname.getText().toString();
                String mEmail = email.getText().toString();
                String mPassword = password.getText().toString();
                String mCPassword = cPassword.getText().toString();

                if (TextUtils.isEmpty(mSname) || TextUtils.isEmpty(mFname)
                        ||TextUtils.isEmpty(mEmail) || TextUtils.isEmpty(mPassword)
                        || TextUtils.isEmpty(mCPassword)){
                    Toast.makeText(Registration.this, "All Fields are Required", Toast.LENGTH_SHORT).show();
                } else if(!Patterns.EMAIL_ADDRESS.matcher(mEmail).matches()){
                    email.setError("Invalid Email");
                    email.setFocusable(true);
                } else if(!mPassword.equals(mCPassword)){
                    Toast.makeText(Registration.this, "password does not match", Toast.LENGTH_SHORT).show();
                } else if(mPassword.length() < 6){
                    password.setError("Password length must be at least 6 character");
                    password.setFocusable(true);
                }  else {

                    register(mSname, mFname, mEmail, mPassword);

                }
            }
        });

    }

    private void register(String mSname, String mFname, String mEmail, String mPassword) {

        progressDialog.show();
        auth.createUserWithEmailAndPassword(mEmail,mPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            progressDialog.dismiss();
                            FirebaseUser firebaseUser = auth.getCurrentUser();
                            assert firebaseUser != null;
                            String userId = firebaseUser.getUid();

                            HashMap<String, String> myUser = new HashMap<>();
                            myUser.put("id", userId);
                            myUser.put("gender", mSname);
                            myUser.put("first_Name", mFname);
                            myUser.put("last_Name", mEmail);
                            myUser.put("email", mPassword);

                            database = FirebaseDatabase.getInstance();
                            reference = database.getReference("Assessment");

                            reference.child(userId).setValue(myUser);


                        } else {

                            Toast.makeText(Registration.this, "you can't register with this email and password", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(Registration.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}