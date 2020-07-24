package com.example.shopsmart.views.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.example.shopsmart.R;
import com.example.shopsmart.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends Activity {
    protected final static String TAG = "LOGIN_A";
    protected User user;
    protected EditText etEmail;
    protected EditText etPassword;
    protected Button btnLogin;
    protected Button btnRegister;
    protected FirebaseAuth fa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: called");

        // initialize ui
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initializeUIComponents();

        // initialize firebase
        fa = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart: called");
        super.onStart();
        FirebaseUser fu = fa.getCurrentUser();
        updateUI(fu);
    }

    protected void initializeUIComponents() {
        Log.d(TAG, "initializeComponents: called");

        // initialize components
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        btnRegister = findViewById(R.id.btn_register);

        // on click
        btnLogin.setOnClickListener(loginListener);
        btnRegister.setOnClickListener(registerListener);
    }

    protected void loginUser() {
        Log.d(TAG, "loginUser: called");
        fa.signInWithEmailAndPassword(user.getEmail(), user.getPassword())
            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "loginUser: succeeded");
                        FirebaseUser fu = fa.getCurrentUser();
                        dismissLoading();
                        updateUI(fu);
                    } else {
                        Log.w(TAG, "loginUser: failed", task.getException());
                        showToast(LoginActivity.this,"Sign in failed");
                    }

                }
            });
    }

    protected void updateUI(FirebaseUser fu) {
        Log.d(TAG, "updateUI: called");
        if (fu != null) {
            showToast(LoginActivity.this,"Signed in successfully");
            goToMainActivity(LoginActivity.this);
        }
    }



    protected void createUser() {
        Log.d(TAG, "createUser: called");
        user = new User(
            etEmail.getText().toString().trim(),
            etPassword.getText().toString().trim()
        );
    }

    protected View.OnClickListener loginListener =
        new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "loginListener: called");
                showLoading(LoginActivity.this);
                createUser();
                loginUser();
            }
        };

    protected View.OnClickListener registerListener =
        new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "registerListener: called");
                goToRegisterActivity(LoginActivity.this);
            }
        };

}