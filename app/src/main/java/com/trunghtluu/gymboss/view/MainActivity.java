package com.trunghtluu.gymboss.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.trunghtluu.gymboss.R;
import com.trunghtluu.gymboss.adapter.GymBossAdapter;
import com.trunghtluu.gymboss.model.MemberData;
import com.trunghtluu.gymboss.util.Constants;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements GymBossAdapter.MemberAdapterDelegate {

    private GymBossAdapter gymBossAdapter;

    @BindView(R.id.member_recyclerView) RecyclerView memberRecyclerView;

    @BindView(R.id.add_button) Button addButton;

    private List<MemberData> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(v.getContext(), MemberActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        loadRecyclerView();
    }

    private void loadRecyclerView() {
        list = new ArrayList<>();
        readFromExternalStorage();

        gymBossAdapter = new GymBossAdapter(list, this, this);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, RecyclerView.VERTICAL);
        memberRecyclerView.addItemDecoration(itemDecoration);
        memberRecyclerView.setLayoutManager(
                new LinearLayoutManager(this));
        memberRecyclerView.setAdapter(gymBossAdapter);
    }

    private void readFromExternalStorage() {

        try {
            File external = new File(getExternalFilesDir(Constants.directoryPath), Constants.fileName);
            FileInputStream fileInputStream = new FileInputStream(external);
            InputStreamReader streamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(streamReader);
            String currentLine = null;

            while ((currentLine = bufferedReader.readLine()) != null) {
                String[] arr = currentLine.split(" ");
                list.add(new MemberData(arr[0], arr[1], arr[2]));
            }

            bufferedReader.close();


        } catch (IOException e) {
            Log.e("TAG_ERROR", String.valueOf(new Throwable(e.getMessage())));
        }
    }

    @Override
    public void memberSelected(MemberData selected) {
        Intent intent = new Intent(this, OnClickActivity.class);
        intent.putExtra("my_parcel", "ID: " + selected.getId() + "\nNAME: " + selected.getName() + "\nPLAN: " + selected.getPlan());
        startActivity(intent);
    }
}
