package company.hakatonus;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class HomeFragment extends Fragment implements View.OnClickListener {
    private Button btn;
    private Button logout;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userId;
    private TextView lblEmail, lblIin, lblNumber, lblNameProf;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_profile, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        logout = (Button) v.findViewById(R.id.btn_logout);
        logout.setOnClickListener(this);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userId = user.getUid();

        lblEmail = (TextView) v.findViewById(R.id.tv_email);
        lblIin = (TextView) v.findViewById(R.id.lblIin);
        lblNumber = (TextView) v.findViewById(R.id.lblNumber);
        lblNameProf = (TextView) v.findViewById(R.id.your_profile);

        reference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                if(userProfile != null)
                {
                    String fullName = (String) userProfile.getFullName();
                    String email = (String) userProfile.getEmail();
                    String Iin = (String) userProfile.getLiter();
                    String Number = (String) userProfile.getNumber();

                    lblNameProf.setText(lblNameProf.getText().toString() + " " + fullName);
                    lblEmail.setText(email);
                    lblIin.setText(Iin);
                    lblNumber.setText(Number);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Something wrong happened!", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onClick(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getContext(), LoginActivity.class));
    }
}