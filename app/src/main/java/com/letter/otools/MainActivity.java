package com.letter.otools;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import org.litepal.LitePal;

import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Anniversary[] anniversaries = {
            new Anniversary(new Date().getTime() - 5461218546L, "第一个纪念日"),
            new Anniversary(new Date().getTime(), "第二个纪念日"),
            new Anniversary(new Date().getTime(), "第三个纪念日"),
            new Anniversary(new Date().getTime(), "第四个纪念日"),
            new Anniversary(new Date().getTime(), "第五个纪念日", Anniversary.ANNI_TYPE_EVERY_YEAR),
            new Anniversary(new Date().getTime() + 9861218546L, "第六个纪念日", Anniversary.ANNI_TYPE_COUNT_DOWN),
            new Anniversary(new Date().getTime(), "第七个纪念日", Anniversary.ANNI_TYPE_ONLY_ONCE),
    };

    private AnniAdapter anniAdapter;

    List<Anniversary> anniversaryList;// = Arrays.asList(anniversaries);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.Toolbar);
        setSupportActionBar(toolbar);

        anniversaryList = LitePal.findAll(Anniversary.class);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new SpacesItemDecoration(1));
        anniAdapter = new AnniAdapter(anniversaryList);
        recyclerView.setAdapter(anniAdapter);

        FloatingActionButton floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddItemActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    anniversaryList.clear();
                    anniversaryList.addAll(LitePal.findAll(Anniversary.class));
                    anniAdapter.notifyDataSetChanged();
                }
                break;

            default:
                break;
        }
    }
}
