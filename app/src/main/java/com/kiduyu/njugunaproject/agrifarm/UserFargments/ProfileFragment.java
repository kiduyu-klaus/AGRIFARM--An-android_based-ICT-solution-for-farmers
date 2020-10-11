package com.kiduyu.njugunaproject.agrifarm.UserFargments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kiduyu.njugunaproject.agrifarm.HomeActivity;
import com.kiduyu.njugunaproject.agrifarm.LoginActivity;
import com.kiduyu.njugunaproject.agrifarm.Model.User;
import com.kiduyu.njugunaproject.agrifarm.R;
import com.kiduyu.njugunaproject.agrifarm.RegisterActivity;
import com.kiduyu.njugunaproject.agrifarm.Session.Prevalent;

public class ProfileFragment extends Fragment {
    EditText edtFullName;
    EditText edtEmail;
    EditText edtPassword;
    Button edt_profile, save;
    EditText edtMobile;
    ProgressDialog pDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment, container, false);


        edtFullName = view.findViewById(R.id.edt_name);
        edtFullName.setEnabled(false);
        save = view.findViewById(R.id.edt_profile_save);
        edt_profile = view.findViewById(R.id.edt_profile);
        edtEmail = view.findViewById(R.id.edt_email);
        edtEmail.setEnabled(false);
        edtPassword = view.findViewById(R.id.edt_password);
        edtPassword.setEnabled(false);
        edtMobile = view.findViewById(R.id.edt_phone);
        edtMobile.setEnabled(false);

        edt_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtFullName.setEnabled(true);
                edtFullName.setText("");
                edtFullName.setMaxLines(1);
                edtFullName.setHint("Input New Full Name");
                edtFullName.setHintTextColor(getResources().getColor(R.color.dialog_color));

                edtEmail.setEnabled(true);
                edtEmail.setText("");
                edtEmail.setMaxLines(1);
                edtEmail.setHint("Input New Username");
                edtEmail.setHintTextColor(getResources().getColor(R.color.dialog_color));

                edtPassword.setEnabled(true);
                edtPassword.setText("");
                edtPassword.setMaxLines(1);
                edtPassword.setHint("Input New password");
                edtPassword.setHintTextColor(getResources().getColor(R.color.dialog_color));


                edtMobile.setEnabled(true);
                edtMobile.setText("");
                edtMobile.setMaxLines(1);
                edtMobile.setHint("Input New mobile number");
                edtMobile.setHintTextColor(getResources().getColor(R.color.dialog_color));


                edt_profile.setVisibility(View.GONE);
                save.setVisibility(View.VISIBLE);

            }
        });
        String mphone = Prevalent.currentOnlineUser.getPhone();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtFullName.getText().toString();
                String phone = edtMobile.getText().toString();
                String memail = edtMobile.getText().toString();
                String password = edtPassword.getText().toString();


                if (TextUtils.isEmpty(name)) {
                    edtFullName.setError("Name Is Required..");
                    return;
                } else if (TextUtils.isEmpty(phone)) {
                    edtMobile.setError("Phone Number Is Required..");
                    return;
                } else if (TextUtils.isEmpty(memail)) {
                    edtMobile.setError("Username Is Required..");
                    return;
                } else if (TextUtils.isEmpty(password)) {
                    edtPassword.setError("Password Is Required..");
                    return;
                } else {
                    //String uniqueid= UUID.randomUUID().toString();
                    String uniqueid = Settings.Secure.getString(getActivity().getContentResolver(), Settings.Secure.ANDROID_ID);
                    Log.d("TAG", "onCreate: " + uniqueid);

                    ValidatephoneNumber(name, phone, memail, password);


                }
            }
        });
        getUser(mphone);
        return view;
    }

    private void ValidatephoneNumber(final String name, final String phone, final String memail, final String password) {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Updating Account");
        progressDialog.setMessage("Please wait......");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.child("Users").child(phone).exists()) {
                    User user = new User(name, phone, memail, password, "https://cdn.pixabay.com/photo/2014/03/24/17/19/teacher-295387__340.png");

                    RootRef.child("Users").child(phone).setValue(user)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {

                                        progressDialog.dismiss();
                                        getUser(phone);
                                    } else {
                                        progressDialog.dismiss();
                                        Toast.makeText(getActivity(), "Network Error: Please try again after some time...", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                } else {

                    progressDialog.dismiss();
                    Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();


                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressDialog.dismiss();
                Toast.makeText(getActivity(), String.valueOf(databaseError.getMessage()), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void getUser(final String mphone) {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Fetching Account");
        progressDialog.setMessage("Please wait......");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();


        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child("Users").child(mphone).exists()) {
                    User usersData = dataSnapshot.child("Users").child(mphone).getValue(User.class);

                    edtFullName.setText(usersData.getFullname());
                    edtMobile.setText(usersData.getPhone());
                    edtEmail.setText(usersData.getUsername());
                    edtPassword.setText(usersData.getPass());
                    progressDialog.dismiss();

                } else {
                    Toast.makeText(getActivity(), "Account with this " + mphone + " number do not exists.", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressDialog.dismiss();
                Toast.makeText(getActivity(), String.valueOf(databaseError.getMessage()), Toast.LENGTH_SHORT).show();


            }
        });
    }
}
