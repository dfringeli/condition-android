package ch.fringeli.dominik.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    TextView textViewCondition;
    Button buttonSunny;
    Button buttonFoggy;

    DatabaseReference dbReference = FirebaseDatabase.getInstance().getReference();
    DatabaseReference conditionReference = dbReference.child("condition");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewCondition = findViewById(R.id.textViewCondition);
        buttonSunny = findViewById(R.id.buttonSunny);
        buttonFoggy = findViewById(R.id.buttonFoggy);
    }

    @Override
    protected void onStart() {
        super.onStart();

        conditionReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue(String.class);
                textViewCondition.setText(text);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void buttonSunny_onClick(View view) {
        conditionReference.setValue("Sunny");
    }

    public void buttonFoggy_onClick(View view) {
        conditionReference.setValue("Foggy");
    }
}

