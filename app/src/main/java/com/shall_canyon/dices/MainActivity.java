package com.shall_canyon.dices;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView tx_disp = findViewById(R.id.Text_Disp);
        Button bt_roll = findViewById(R.id.bt_roll);
        bt_roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = (int)(Math.random()*100 + 1);
                tx_disp.setText(String.valueOf(i));
            }
        });
    }
}
