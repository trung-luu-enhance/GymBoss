package com.trunghtluu.gymboss.util;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.trunghtluu.gymboss.R;
import com.trunghtluu.gymboss.view.MainActivity;

public class MemberViewHolder extends RecyclerView.ViewHolder {

    TextView idTextView;
    TextView nameTextView;
    TextView planTextView;

    public MemberViewHolder(@NonNull View memberView) {
        super(memberView);

        idTextView = memberView.findViewById(R.id.id_textView);
        nameTextView = memberView.findViewById(R.id.name_textView);
        planTextView = memberView.findViewById(R.id.plan_textView);
    }

    public TextView getIdTextView() {
        return idTextView;
    }

    public TextView getNameTextView() {
        return nameTextView;
    }

    public TextView getPlanTextView() {
        return planTextView;
    }
}
