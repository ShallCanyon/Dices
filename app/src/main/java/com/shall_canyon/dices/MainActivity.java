package com.shall_canyon.dices;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

/**
 * @author ShallCanyon
 */

public class MainActivity extends AppCompatActivity {

    //determine whether the main fab_menu is opened or not
    boolean isFabOpen = false;
    //define upper and lower limit of dice
    private int upper = 100;
    private int lower = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //randomize data with a button
        final TextView tx_disp = findViewById(R.id.Text_Disp);
        Button bt_roll = findViewById(R.id.bt_roll);
        bt_roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = (int) (Math.random() * getRange() + lower);
                tx_disp.setText(String.valueOf(i));
            }
        });

        //fabs of dices selection
        FloatingActionButton fab = findViewById(R.id.bt_fab);
        final FloatingActionButton d6 = findViewById(R.id.bt_d6);
        final FloatingActionButton d60 = findViewById(R.id.bt_d60);
        final FloatingActionButton d100 = findViewById(R.id.bt_d100);
        final FloatingActionButton diy = findViewById(R.id.bt_diy);
        fab.bringToFront();

        //ClickListeners of fabs
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isFabOpen) {
                    closeFabMenu(d6, d60, d100, diy);
                } else {
                    openFabMenu(d6, d60, d100, diy);
                }
            }
        });
        d6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeTextView(6, 1);
                Toast.makeText(MainActivity.this, "D6", Toast.LENGTH_SHORT).show();
                closeFabMenu(d6, d60, d100, diy);
            }
        });
        d60.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeTextView(60, 1);
                Toast.makeText(MainActivity.this, "D60", Toast.LENGTH_SHORT).show();
                closeFabMenu(d6, d60, d100, diy);
            }
        });
        d100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeTextView(100, 1);
                Toast.makeText(MainActivity.this, "D100", Toast.LENGTH_SHORT).show();
                closeFabMenu(d6, d60, d100, diy);
            }
        });
        diy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textEditDialog();
            }
        });

    }

    public void setUpper(int i) {
        upper = i;
    }

    public void setLower(int i) {
        lower = i;
    }

    public int getRange() {
        return upper - lower + 1;
    }

    /**
     * Change maximum upper, lower limits of dice
     * and
     * text of TextView
     */
    public void changeTextView(int upper, int lower) {
        setUpper(upper);
        setLower(lower);
        TextView id = findViewById(R.id.Text_Disp);
        id.setText(String.valueOf(this.upper));
    }

    /**
     * Close Fab Menu by animating other fabs(param ...x) to default position and set invisible
     *
     * @param x
     */
    public void closeFabMenu(FloatingActionButton... x) {
        isFabOpen = false;
        for (int i = 0; i < x.length; i++) {
            x[i].animate().translationY(0);
            x[i].hide();
        }
    }

    /**
     * Open Fab Menu by animating other fabs(param ...x) to upper positions and set visible
     *
     * @param x
     */
    public void openFabMenu(FloatingActionButton... x) {
        isFabOpen = true;
        for (int i = 0; i < x.length; i++)
            x[i].show();
        x[0].animate().translationY(-getResources().getDimension(R.dimen.standard_0));
        x[1].animate().translationY(-getResources().getDimension(R.dimen.standard_1));
        x[2].animate().translationY(-getResources().getDimension(R.dimen.standard_2));
        x[3].animate().translationY(-getResources().getDimension(R.dimen.standard_3));
    }

    /**
     * Pop up AlertDialog when click TextView
     *
     * @param view
     */
    public void text_clicked(View view) {
        //Toast.makeText(MainActivity.this,"TextEdit clicked",Toast.LENGTH_SHORT).show();
        textEditDialog();
    }

    /**
     * Pop up AlertDialog that changes maximum upper and lower limits of dice
     */
    public void textEditDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("输入骰子范围");

        //Using LinearLayout to contain two EditText
        LinearLayout layout = new LinearLayout(MainActivity.this);
        layout.setOrientation(LinearLayout.VERTICAL);

        //Create EditText inside layout
        final EditText editUpper = new EditText(this);
        editUpper.setHint("最大值");
        editUpper.setText(String.valueOf(upper));
        final EditText editLower = new EditText(this);
        editLower.setHint("最小值");
        editLower.setText(String.valueOf(lower));
        layout.addView(editUpper);
        layout.addView(editLower);
        builder.setView(layout);

        builder.setPositiveButton("完成", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (editLower.getText().toString().trim().length() > 0 &&
                        editUpper.getText().toString().trim().length() > 0) {
                    int up = Integer.parseInt(editUpper.getText().toString().trim());
                    int down = Integer.parseInt(editLower.getText().toString().trim());
                    changeTextView(up, down);
                    Toast.makeText(MainActivity.this, "D" + getRange(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "输入框内容不能为空", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("退出", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
