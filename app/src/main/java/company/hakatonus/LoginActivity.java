package company.hakatonus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView lblRegister;
    private EditText plnEmail, plnPass;
    private Button btnLogin;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private FirebaseDatabase db;
    private DatabaseReference users;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        lblRegister = (TextView) findViewById(R.id.lblRegister);
        lblRegister.setOnClickListener(this);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);

        plnEmail = (EditText) findViewById(R.id.plnEmail);
        plnPass = (EditText) findViewById(R.id.plnPassword);
        progressBar = (ProgressBar) findViewById(R.id.barProgress);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.lblRegister:
                startActivity(new Intent(this, RegisterActivity.class));
                break;

            case R.id.btnLogin:
                userLogin();
        }
    }

    private void userLogin(){
        String email = plnEmail.getText().toString().trim();
        String password = plnPass.getText().toString().trim();

        //проверка на правильный пароль и почту
        if(password.isEmpty()){
            plnPass.setError("Password is required!");
            plnPass.requestFocus();
            return;
        }
        if(email.isEmpty()){
            plnEmail.setError("Email is required!");
            plnEmail.requestFocus();
            return;
        }
        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            plnEmail.setError("Your email is incorrect!");
            plnEmail.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if(user.isEmailVerified()){
                        //направляет в юзер профиль
                        progressBar.setVisibility(View.GONE);
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }
                    else{
                        progressBar.setVisibility(View.GONE);
                        user.sendEmailVerification();
                        Toast.makeText(LoginActivity.this, "Check your email to verify your account!", Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(LoginActivity.this, "Failed Login! Please check your credentials", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}