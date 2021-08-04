package com.example.googleloginapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class HomeActivity extends AppCompatActivity {
    Button signout;
    GoogleSignInClient mGoogleSignInClient;
    TextView name,email;
    ImageView pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        name=findViewById(R.id.txt_name);
        email=findViewById(R.id.txt_email);
        pic=findViewById(R.id.imgview);
        signout=findViewById(R.id.btn_signout);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String personName = acct.getDisplayName();
            //String personGivenName = acct.getGivenName();
            //String personFamilyName = acct.getFamilyName();
            String personEmail = acct.getEmail();
            //String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();
            name.setText(personName);
            email.setText(personEmail);
            Glide.with(this).load(String.valueOf(personPhoto)).into(pic);
        }

    }
    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                        Toast.makeText(HomeActivity.this, "sign out", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(HomeActivity.this,MainActivity.class));
                    }
                });
    }
}