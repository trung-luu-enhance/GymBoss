package com.trunghtluu.gymboss.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.trunghtluu.gymboss.R;
import com.trunghtluu.gymboss.model.MemberData;
import com.trunghtluu.gymboss.util.MemberViewHolder;

import java.util.Collections;
import java.util.List;

public class GymBossAdapter extends RecyclerView.Adapter<MemberViewHolder> {

    private MemberAdapterDelegate memberDelegate;


    public interface MemberAdapterDelegate{
        void memberSelected(MemberData selected);
    }

    private List<MemberData> list
            = Collections.emptyList();

    private Context context;

    public GymBossAdapter(List<MemberData> list, Context context, MemberAdapterDelegate memberAdapterDelegate) {
        this.list = list;
        this.context = context;
        this.memberDelegate = memberAdapterDelegate;
    }

    @NonNull
    @Override
    public MemberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.member_card, parent, false);

        return new MemberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberViewHolder holder, int position) {
        holder.getIdTextView()
                .setText("ID: " + list.get(position).getId());
        holder.getNameTextView()
                .setText("NAME: " + list.get(position).getName());
        holder.getPlanTextView()
                .setText("PLAN: " + list.get(position).getPlan());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                memberDelegate.memberSelected(list.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
