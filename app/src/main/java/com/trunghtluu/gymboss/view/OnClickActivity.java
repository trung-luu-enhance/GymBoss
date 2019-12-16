package com.trunghtluu.gymboss.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.trunghtluu.gymboss.R;
import com.trunghtluu.gymboss.model.MemberData;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OnClickActivity extends AppCompatActivity {

    @BindView(R.id.memmber_textView)
    TextView memberTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_click);

        ButterKnife.bind(this);

        memberTextView.setText(getIntent().getStringExtra("my_parcel"));
    }
}
