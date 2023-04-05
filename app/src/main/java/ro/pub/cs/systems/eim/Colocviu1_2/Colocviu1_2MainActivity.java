package ro.pub.cs.systems.eim.Colocviu1_2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Colocviu1_2MainActivity extends AppCompatActivity {
    private EditText editText1 = null;
    private TextView textView1 = null;
    private Button add = null;
    private Button compute = null;

    private int numberTerms = 0;
    int sum  = 0;
    private boolean modified = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_2_main);

        editText1 = (EditText)findViewById(R.id.next_term);
        textView1 = (TextView)findViewById(R.id.all_terms);
        add = (Button)findViewById(R.id.add);
        compute = (Button)findViewById(R.id.compute);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText1.getText().toString();
                if (text != null && !text.isEmpty()) {
                    if (numberTerms == 0) {
                        textView1.setText(textView1.getText().toString()  + text);
                    } else {
                        textView1.setText(textView1.getText().toString() + " + " + text);
                    }
                    modified = true;
                    numberTerms++;
                }
            }
        });


        compute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (modified) {
                    String text = textView1.getText().toString();
                    sum = 0;
                    if (!text.isEmpty()) {
                        String[] terms = text.split(" \\+ ");

                        for (String term : terms) {
                            sum += Integer.parseInt(term);
                        }
                    }
                    Intent intent = new Intent(getApplicationContext(), Colocviul1_2SecondaryActivity.class);
                    intent.putExtra(Constants.SUM, sum);

                    startActivityForResult(intent, Constants.REQUEST_CODE);
                } else {
                    if (savedInstanceState != null) {
                        Toast.makeText(getApplicationContext(), "Suma termenilor este " + sum, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.REQUEST_CODE) {
            int sum = data.getIntExtra(Constants.SUM, 0);
            Toast.makeText(this, "Suma termenilor este "  + sum, Toast.LENGTH_SHORT).show();
            modified = false;
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putInt(Constants.SUM, sum);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        sum = savedInstanceState.getInt(Constants.SUM);
    }
}