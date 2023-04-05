package ro.pub.cs.systems.eim.Colocviu1_2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class Colocviul1_2SecondaryActivity extends AppCompatActivity {
    Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colocviul12_secondary);

        cancelButton = findViewById(R.id.cancel_button);

        cancelButton.setOnClickListener(it -> {
            Bundle extras = getIntent().getExtras();
            Intent intent = new Intent();
            if (extras != null) {
                int sum = extras.getInt(Constants.SUM);

                intent.putExtra(Constants.SUM, sum);
                setResult(RESULT_CANCELED,intent);
            }

            finish();
        });
    }



}