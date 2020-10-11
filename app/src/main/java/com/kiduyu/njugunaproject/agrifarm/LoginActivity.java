package com.kiduyu.njugunaproject.agrifarm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.kiduyu.njugunaproject.agrifarm.Model.User;
import com.kiduyu.njugunaproject.agrifarm.Session.Prevalent;
import com.kiduyu.njugunaproject.agrifarm.StatusBar.StatusBar;

public class LoginActivity extends AppCompatActivity {
    EditText username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBar.changeStatusBarColor(this);
        setContentView(R.layout.activity_login);
        username=findViewById(R.id.editTextUname);
        password=findViewById(R.id.editTextPassword);
    }

    public void home(View view) {
        String muser=username.getText().toString().trim();
        String mpass=password.getText().toString().trim();


        if (TextUtils.isEmpty(muser)){
            username.setError("required");
            username.requestFocus();
        } else if (TextUtils.isEmpty(mpass)){
            password.setError("required");
            password.requestFocus();
        }else {
            ProgressDialog progressDialog= new ProgressDialog(this);
            progressDialog.setTitle("Checking Account");
            progressDialog.setMessage("Please wait......");
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            ValidateUser(muser,mpass,progressDialog);
        }
    }

    private void ValidateUser(final String muser, final String mpass, final ProgressDialog progressDialog) {


        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();


        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child("Users").child(muser).exists()) {
                    User usersData = dataSnapshot.child("Users").child(muser).getValue(User.class);

                    if (usersData.getPhone().equals(muser)) {
                        if (usersData.getPass().equals(mpass)) {

                                Toast.makeText(LoginActivity.this, "Welcome "+usersData.getFullname()+", you are logged in Successfully...", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();

                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                Prevalent.currentOnlineUser = usersData;
                                startActivity(intent);

                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(LoginActivity.this, "Password is incorrect.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(LoginActivity.this, "phonenumber is incorrect.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Account with this " + muser + " number do not exists.", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressDialog.dismiss();
                Toast.makeText(LoginActivity.this, String.valueOf(databaseError.getMessage()), Toast.LENGTH_SHORT).show();


            }
        });

    }


}