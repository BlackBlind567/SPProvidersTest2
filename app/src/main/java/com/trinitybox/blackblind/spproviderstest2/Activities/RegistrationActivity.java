package com.trinitybox.blackblind.spproviderstest2.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.trinitybox.blackblind.spproviderstest2.R;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RegistrationActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";
    FirebaseFirestore firebaseFirestore;
    Button submitButton;
    TextInputEditText emailEditText, fullNameEditText, mobileEditText;
    TextInputLayout emailLayout, fullNameLayout, mobileLayout;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //UI References
        submitButton = findViewById(R.id.submitButton);
        spinner = findViewById(R.id.spinner);

        emailLayout = findViewById(R.id.emailWrapper);
        fullNameLayout = findViewById(R.id.nameWrapper);
        mobileLayout = findViewById(R.id.mobileWrapper);

        emailEditText = findViewById(R.id.emailET);
        fullNameEditText = findViewById(R.id.nameET);
        mobileEditText = findViewById(R.id.mobieET);

        //Setting Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Services_array, R.layout.custom_spinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //Initialize Firestore
        firebaseFirestore = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setTimestampsInSnapshotsEnabled(true)
                .build();
        firebaseFirestore.setFirestoreSettings(settings);

        //On Submitting Form
        submitButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                // Create a new user with a first and last name
                Map<String, Object> user = new HashMap<>();

                if (!fullNameEditText.getText().toString().equals("")) {
                    user.put("name", fullNameEditText.getText().toString());
                } else {
                    fullNameEditText.requestFocus();
                    final AlertDialog.Builder builder = new AlertDialog.Builder(RegistrationActivity.this,R.style.DialogTheme);
                    builder.setIcon(android.R.drawable.ic_dialog_alert)
                            .setTitle("Insufficient Input")
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                    //alert dialog background
                    Objects.requireNonNull(alertDialog.getWindow()).setBackgroundDrawableResource(R.color.colorTextDialog);
                    return;
                }

                Log.d(TAG, "If you can see this message, return didn't work");

                user.put("email", emailEditText.getText().toString());

                //TODO: Email Validation
//                    if (Patterns.EMAIL_ADDRESS.matcher("ravindra@gmail.com").matches()) {
//                        Log.d("TAG", "Email Validation Successfull");
//                    }
                user.put("mobile", mobileEditText.getText().toString());
                user.put("category", spinner.getSelectedItem().toString());
                user.put("approved", false);

                //Remove OnClickListner
                submitButton.setOnClickListener(null);
                submitButton.setText("Please Wait...");

                firebaseFirestore.collection("users")
                        .add(user)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                                startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error adding document", e);
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegistrationActivity.this);
                                builder.setMessage("Registration Failed")
                                        .setTitle("Failed");
                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                            }
                        });
            }
        });
    }
}
