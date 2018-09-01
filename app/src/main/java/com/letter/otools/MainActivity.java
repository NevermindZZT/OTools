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

import org.litepal.LitePal;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AnniAdapter anniAdapter;

    List<Anniversary> anniversaryList;

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
        anniAdapter.setOnAnniItemClickListener(new OnAnniItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(MainActivity.this, AnniversaryActivity.class);
                intent.putExtra("anniId", anniversaryList.get(position).getId());
                startActivityForResult(intent, 1);
            }
        });
        recyclerView.setAdapter(anniAdapter);

        final FloatingActionButton floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddItemActivity.class);
                intent.putExtra("editType", AddItemActivity.ITEM_ADD);
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
