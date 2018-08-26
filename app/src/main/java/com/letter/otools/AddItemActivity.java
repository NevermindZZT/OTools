package com.letter.otools;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddItemActivity extends AppCompatActivity {

    private SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
    private Date selectDate = new Date();

    private TextView textYear;
    private TextView textMouth;
    private TextView textDay;

    private ActionBar actionBar;

    private EditText editTextName;

    private LinearLayout dateChoose;

    private DatePickerDialog datePickerDialog;

    private AlertDialog.Builder typeDialog;

    private LinearLayout typeLayout;

    private TextView textType;

    private Anniversary anniversary = new Anniversary();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        Toolbar toolbar = findViewById(R.id.Toolbar);
        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_back);
            actionBar.setTitle("");
        }

        editTextName = findViewById(R.id.text_name);

        dateChoose = findViewById(R.id.date_choose);
        freshDate(anniversary.getTime());

        datePickerDialog = new DatePickerDialog(AddItemActivity.this);
        datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int mouth, int day) {
                try {
                    selectDate = (format.parse(String.valueOf(year) + "年"
                                                    + String.valueOf(mouth + 1) + "月"
                                                    + String .valueOf(day) + "日"));
                    anniversary.setTime(selectDate.getTime());
                    freshDate(anniversary.getTime());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        dateChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });

        textType = findViewById(R.id.type);

        typeDialog = new AlertDialog.Builder(this).setTitle("纪念日类型")
                .setItems(Anniversary.typeText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                textType.setText(Anniversary.typeText[i]);
                anniversary.setType(i);
            }
        });


        typeLayout = findViewById(R.id.type_layout);
        typeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                typeDialog.show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_item_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent();;
        switch (item.getItemId()) {
            case android.R.id.home:
                setResult(RESULT_CANCELED, intent);
                finish();
                break;

            case R.id.save:
                EditText editText = findViewById(R.id.text_name);
                anniversary.setText(editText.getText().toString());
                anniversary.save();
                Toast.makeText(AddItemActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK, intent);
                finish();
                break;

            default:
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
        finish();
    }

    private void freshDate(long time) {
        textYear = findViewById(R.id.text_year);
        textMouth = findViewById(R.id.text_mouth);
        textDay = findViewById(R.id.text_day);

        textYear.setText(new SimpleDateFormat("yyyy").format(time));
        textMouth.setText(new SimpleDateFormat("MM").format(time));
        textDay.setText(new SimpleDateFormat("dd").format(time));
    }

}
