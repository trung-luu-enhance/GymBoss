package com.trunghtluu.gymboss.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.trunghtluu.gymboss.R;
import com.trunghtluu.gymboss.util.Constants;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MemberActivity extends AppCompatActivity {

    @BindView(R.id.name_editText) EditText nameEditText;
    @BindView(R.id.plan_editText) EditText planEditText;

    @BindView(R.id.submit_button) Button submitButton;

    private File external;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);

        ButterKnife.bind(this);

        external = new File(getExternalFilesDir(Constants.directoryPath), Constants.fileName);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                writeToExternalStorage();

                sharedPreferences = getSharedPreferences(Constants.CURRENT_ID, Context.MODE_PRIVATE);
                int id = sharedPreferences.getInt(Constants.CURRENT_ID, 0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt(Constants.CURRENT_ID, ++id);
                editor.commit();

                finish();
            }
        });
    }

    private String getInput() {

        sharedPreferences = getSharedPreferences(Constants.CURRENT_ID, Context.MODE_PRIVATE);
        int id = sharedPreferences.getInt(Constants.CURRENT_ID, 0);

        String input = id + " " + nameEditText.getText() + " " + planEditText.getText() + "\n";

        nameEditText.setText("");
        planEditText.setText("");

        return input;

    }

    private void writeToExternalStorage() {
        try {

            FileOutputStream fileOutputStream = new FileOutputStream(external, true);

            String input = getInput();
            fileOutputStream.write(input.getBytes());
            fileOutputStream.close();

            Toast.makeText(this, input + " successfully added member.", Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            Log.e("TAG_ERROR", String.valueOf(new Throwable(e.getMessage())));
        }
    }
}
