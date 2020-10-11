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

import com.kiduyu.njugunaproject.agrifarm.Model.User;
import com.kiduyu.njugunaproject.agrifarm.StatusBar.StatusBar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivity extends AppCompatActivity {
    EditText name,phone,username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBar.changeStatusBarColor(this);
        setContentView(R.layout.activity_register);
        name=findViewById(R.id.editTextUfullname);
        phone=findViewById(R.id.editTextUphonee);
        username=findViewById(R.id.editTextUname_r_a);
        password=findViewById(R.id.editTextPassword_r_v);
    }
    public void toLogin(View view) {
    }

    public void Validate(View view) {
        String uname = name.getText().toString().trim();
        String uphone = phone.getText().toString().trim();
        String uusername = username.getText().toString().trim();
        String upass = password.getText().toString().trim();

        if (TextUtils.isEmpty(uname)){
            name.setError("required");
            name.requestFocus();
        } else if (TextUtils.isEmpty(uphone)){
            phone.setError("required");
            phone.requestFocus();
        }else if (TextUtils.isEmpty(uusername)){
            username.setError("required");
            username.requestFocus();
        }else if (TextUtils.isEmpty(upass)){
            password.setError("required");
            password.requestFocus();
        } else{
            ProgressDialog progressDialog= new ProgressDialog(this);
            progressDialog.setTitle("Creating Account");
            progressDialog.setMessage("Please wait......");
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
            CreateAccount(uname,uphone,uusername,upass,progressDialog);
        }
    }

    private void CreateAccount(final String uname, final String uphone, final String uusername, final String upass, final ProgressDialog progressDialog) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {

                    if (!(dataSnapshot.child("Users").child(uphone).exists()))
                    {
                        User user = new User(uname,uphone,uusername,upass,"https://cdn.pixabay.com/photo/2014/03/24/17/19/teacher-295387__340.png");

                        RootRef.child("Users").child(uphone).setValue(user)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task)
                                    {
                                        if (task.isSuccessful())
                                        {
                                            Toast.makeText(RegisterActivity.this, "Congratulations "+uname+", your account has been created.", Toast.LENGTH_SHORT).show();
                                            progressDialog.dismiss();

                                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                            startActivity(intent);
                                        }
                                        else
                                        {
                                            progressDialog.dismiss();
                                            Toast.makeText(RegisterActivity.this, "Network Error: Please try again after some time...", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                    else
                    {
                        Toast.makeText(RegisterActivity.this, "This " + uphone + " already exists.", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        Toast.makeText(RegisterActivity.this, "Please try again using another phone number.", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressDialog.dismiss();
                Toast.makeText(RegisterActivity.this, String.valueOf(databaseError.getMessage()), Toast.LENGTH_SHORT).show();

            }
        });

    }
}