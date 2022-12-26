package company.hakatonus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

//    private Button logout;
//    private FirebaseUser user;
//    private DatabaseReference reference;
//    private String userId;
//    private TextView lblEmail, lblIin, lblNumber, lblNameProf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

//        logout = (Button)  findViewById(R.id.btn_logout);
//        logout.setOnClickListener(this);
//
//        user = FirebaseAuth.getInstance().getCurrentUser();
//        reference = FirebaseDatabase.getInstance().getReference("Users");
//        userId = user.getUid();
//
//        lblEmail = (TextView) findViewById(R.id.tv_email);
//        lblIin = (TextView) findViewById(R.id.lblIin);
//        lblNumber = (TextView) findViewById(R.id.lblNumber);
//        lblNameProf = (TextView) findViewById(R.id.your_profile);
//
//        reference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                User userProfile = snapshot.getValue(User.class);
//
//                if(userProfile != null)
//                {
//                    String fullName = userProfile.getFullName();
//                    String email = userProfile.getEmail();
//                    String Iin = userProfile.getLiter();
//                    String Number = userProfile.getNumber();
//
//                    lblNameProf.setText(lblNameProf.getText().toString() + " " + fullName);
//                    lblEmail.setText(email);
//                    lblIin.setText(Iin);
//                    lblNumber.setText(Number);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(ProfileActivity.this, "Something wrong happened!", Toast.LENGTH_LONG).show();
//            }
//        });

    }
//
//    @Override
//    public void onClick(View view) {
//        FirebaseAuth.getInstance().signOut();
//        startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
//    }
}