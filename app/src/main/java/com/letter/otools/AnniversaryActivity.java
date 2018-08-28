package com.letter.otools;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.LitePal;

import java.text.SimpleDateFormat;

public class AnniversaryActivity extends AppCompatActivity {

    private ActionBar actionBar;
    private Anniversary anniversary;

    private TextView anniDate;
    private TextView anniDays;
    private TextView anniText;
    private TextView anniType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anniversary);
        Toolbar toolbar = findViewById(R.id.Toolbar);
        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_back);
            actionBar.setTitle("");
        }

        anniversary = (Anniversary) getIntent().getSerializableExtra("anniData");

        anniText = findViewById(R.id.anni_text);
        anniText.setText(anniversary.getText());

        anniDate = findViewById(R.id.anni_date);
        anniDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(anniversary.getTime()));

        anniDays = findViewById(R.id.anni_days);
        anniDays.setText("0天");

        anniType = findViewById(R.id.anni_type);
        anniType.setText(Anniversary.typeText[anniversary.getType()]);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.anniversary_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;

            case R.id.delete:
                LitePal.delete(Anniversary.class, anniversary.getId());
                intent = new Intent();
                setResult(RESULT_OK, intent);
                Toast.makeText(AnniversaryActivity.this, "已删除", Toast.LENGTH_SHORT).show();
                finish();
                break;

            case R.id.edit:
                intent = new Intent(AnniversaryActivity.this, AddItemActivity.class);
                intent.putExtra("editType", AddItemActivity.ITEM_EDIT);
                intent.putExtra("anniData", anniversary);
                startActivityForResult(intent, 1);
                break;

            default:
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
