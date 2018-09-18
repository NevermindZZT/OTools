package com.letter.otools;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.letter.otools.service.NotifyService;
import com.letter.otools.util.AnniUtil;

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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "anni";
            String channelName = "纪念日提醒";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            createNotificationChannel(channelId, channelName, importance);
        }

//        Intent broadIntent  = new Intent("android.appwidget.action.APPWIDGET_UPDATE");
//        getApplicationContext().sendBroadcast(broadIntent);

        if (AnniUtil.isNotifyServiceRunning(getApplicationContext()) != true) {
            startService(new Intent(this, NotifyService.class));
        }
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

    @TargetApi(Build.VERSION_CODES.O)
    private void createNotificationChannel(String channelId, String channelName, int importance) {
        NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
        NotificationManager notificationManager = (NotificationManager) getSystemService(
                NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);
    }
}
