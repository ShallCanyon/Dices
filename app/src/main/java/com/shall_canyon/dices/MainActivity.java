package com.shall_canyon.dices;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * @author ShallCanyon
 * @version 1.02
 */

public class MainActivity extends AppCompatActivity {

    //determine whether the main fab_menu is opened or not
    boolean isFabOpen = false;
    //define maximum randomized data
    int MAX = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * randomize data with a button
         */
        final TextView tx_disp = findViewById(R.id.Text_Disp);
        Button bt_roll = findViewById(R.id.bt_roll);
        bt_roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = (int) (Math.random() * MAX + 1);
                tx_disp.setText(String.valueOf(i));
            }
        });

        /**
         * fabs of dices selection
         */
        FloatingActionButton fab = findViewById(R.id.bt_fab);
        final FloatingActionButton d6 = findViewById(R.id.bt_d6);
        final FloatingActionButton d60 = findViewById(R.id.bt_d60);
        final FloatingActionButton d100 = findViewById(R.id.bt_d100);
        fab.bringToFront();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isFabOpen) {
                    closeFabMenu(d6, d60, d100);

                } else {
                    openFabMenu(d6, d60, d100);
                }
            }
        });

        d6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MAX = 6;
                tx_disp.setText(String.valueOf(6));
                Toast.makeText(MainActivity.this, "D6", Toast.LENGTH_SHORT).show();
                closeFabMenu(d6, d60, d100);
            }
        });
        d60.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MAX = 60;
                tx_disp.setText(String.valueOf(60));
                Toast.makeText(MainActivity.this, "D60", Toast.LENGTH_SHORT).show();
                closeFabMenu(d6, d60, d100);
            }
        });
        d100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MAX = 100;
                tx_disp.setText(String.valueOf(100));
                Toast.makeText(MainActivity.this, "D100", Toast.LENGTH_SHORT).show();
                closeFabMenu(d6, d60, d100);
            }
        });

    }

    private void closeFabMenu(FloatingActionButton... x) {
        isFabOpen = false;
        for (int i = 0; i < x.length; i++) {
            x[i].animate().translationY(0);
            x[i].hide();
        }
    }

    private void openFabMenu(FloatingActionButton... x) {
        isFabOpen = true;
        for (int i = 0; i < x.length; i++)
            x[i].show();
        x[0].animate().translationY(-getResources().getDimension(R.dimen.standard_short));
        x[1].animate().translationY(-getResources().getDimension(R.dimen.standard_medium));
        x[2].animate().translationY(-getResources().getDimension(R.dimen.standard_long));
    }
}
