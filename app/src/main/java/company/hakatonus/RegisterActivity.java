package company.hakatonus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView lblAppName, lblApp;
    private EditText plnEmail, plnPass, plnPass2, plnIin, plnFullName,plnNumber;
    private Button btnRegister;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private FirebaseDatabase db;
    private DatabaseReference users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();

        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);

        progressBar = (ProgressBar) findViewById(R.id.barProgress);
        plnEmail = (EditText) findViewById(R.id.plnEmail);
        plnPass = (EditText) findViewById(R.id.plnPassword);
        plnPass2 = (EditText) findViewById(R.id.plnPassword2);
        plnIin = (EditText) findViewById(R.id.plnLiter);
        plnNumber = (EditText) findViewById(R.id.plnNumber);
        plnFullName = (EditText) findViewById(R.id.plnFullName);
    }

    //куда перекидывают кнопки
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnRegister:
                registerUser();
                //обозначение бд с которой мы рабим
//                db = FirebaseDatabase.getInstance("https://facerecognationapp-default-rtdb.firebaseio.com/");
//                users = db.getReference().child("Users");
//
//                users.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        User user = dataSnapshot.getValue(User.class);
//                        lblAppName.setText(user.getEmail());
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//                        lblAppName.setText("The read failed: " + databaseError.getCode());
//                    }
//                });
                break;
        }

    }

    private void registerUser(){
        String email = plnEmail.getText().toString().trim();
        String password = plnPass.getText().toString().trim();
        String password2 = plnPass.getText().toString().trim();
        String fullName = plnFullName.getText().toString().trim();
        String iin = plnIin.getText().toString().trim();
        String number = plnNumber.getText().toString().trim();


        //проверка на правильный пароль и почту
        if(password.isEmpty()){
            plnPass.setError("Password is required!");
            plnPass.requestFocus();
            return;
        }
        if(password.length() <= 6){
            plnPass.setError("Password too small");
            plnPass.requestFocus();
            return;
        }
        if(!(password.equals(password2))){
            plnPass2.setError("Password must be same!");
            plnPass2.requestFocus();
            return;
        }
        if(fullName.isEmpty()){
            plnEmail.setError("Full name is required!");
            plnEmail.requestFocus();
            return;
        }
        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() || !(email.substring(email.length() - 10, email.length()).equals("@fizmat.kz"))){
            //alela@fizmat.kz
            plnEmail.setError("Your email is incorrect! Enter correct @fizmat.kz email adress!");
            plnEmail.requestFocus();
            return;
        }
        if(fullName.isEmpty()){
            plnFullName.setError("Password is required!");
            plnFullName.requestFocus();
            return;
        }
        if(iin.isEmpty()){
            plnIin.setError("Password is required!");
            plnIin.requestFocus();
            return;
        }
        if(number.isEmpty()){
            plnNumber.setError("Password is required!");
            plnNumber.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);

        //регистрация юзера
        mAuth.createUserWithEmailAndPassword(email, password)
                //Добавляем этот листенер чтобы узнать законченность нашего децствия
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            User user = new User(fullName,email,number,iin);
                            String idd = FirebaseAuth.getInstance().getCurrentUser().getUid();
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(idd)
                                    .setValue(user)
                                    //Добавляем этот листенер чтобы узнать законченность нашего децствия
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast
                                                        .makeText(RegisterActivity.this
                                                                , "The User has been registred succesfully",
                                                                Toast.LENGTH_LONG).show();
                                                progressBar.setVisibility(View.GONE);
                                                finish();
                                            } else {
//                                                Query query = users;
//                                                query.addValueEventListener(new ValueEventListener() {
//                                                    @Override
//                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                                                    }
//
//                                                    @Override
//                                                    public void onCancelled(@NonNull DatabaseError error) {
//
//                                                    }
//                                                });
                                                Toast
                                                        .makeText(RegisterActivity.this
                                                                , "FAILED REG",
                                                                Toast.LENGTH_LONG).show();
                                                progressBar.setVisibility(View.GONE);
                                            }
                                        }
                                    });
                        } else {
                            Toast
                                    .makeText(RegisterActivity.this
                                            , "FAILED REG!!!",
                                            Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }

                });
    }

}