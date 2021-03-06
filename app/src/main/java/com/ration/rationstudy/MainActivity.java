package com.ration.rationstudy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ration.rationstudy.Hani.HaniMainActivity;
import com.ration.rationstudy.mark.MarkMainActivity;
import com.ration.rationstudy.marty.MartyMainActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Context context = this;

    enum Members {
        Mark("Mark"),
        Marty("Marty"),
        Hani("Hani"),
        HttpTestCode("http");

        private String title;

        Members(String title) {
            this.title = title;
        }
    }

    private ArrayList<Members> memberArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));

        init();

        MemberAdapter memberAdapter = new MemberAdapter();
        memberAdapter.setMemberSelectListener(memberSelectListener);
        rv.setAdapter(memberAdapter);
    }

    private void init() {
        memberArray.add(Members.Mark);
        memberArray.add(Members.Marty);
        memberArray.add(Members.Hani);
        memberArray.add(Members.HttpTestCode);
    }

    MemberSelectListener memberSelectListener = members -> {
        Intent intent = null;

        switch (members) {
            case Mark:
                Toast.makeText(MainActivity.this, "Mark", Toast.LENGTH_SHORT).show();
                intent = new Intent(context, MarkMainActivity.class);
                break;
            case Marty:
                Toast.makeText(MainActivity.this, "Marty", Toast.LENGTH_SHORT).show();
                intent = new Intent(context, MartyMainActivity.class);

                break;
            case Hani:
                Toast.makeText(MainActivity.this, "Hani", Toast.LENGTH_SHORT).show();
                intent = new Intent(context, HaniMainActivity.class);
                break;
            case HttpTestCode:
                intent = new Intent(context, TestActivity.class);
                break;
        }

        if (intent != null) {
            startActivity(intent);
        }
    };

    class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.MemberHolder> {

        MemberSelectListener memberSelectListener;

        void setMemberSelectListener(MemberSelectListener memberSelectListener) {
            this.memberSelectListener = memberSelectListener;
        }

        @NonNull
        @Override
        public MemberHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.list_members, viewGroup, false);
            return new MemberHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MemberHolder memberHolder, int i) {
            final Members member = memberArray.get(i);
            memberHolder.tv_member.setText(member.title);
            memberHolder.itemView.setOnClickListener(v -> memberSelectListener.onSelected(member));
        }

        @Override
        public int getItemCount() {
            return memberArray.size();
        }

        class MemberHolder extends RecyclerView.ViewHolder {

            private TextView tv_member;

            MemberHolder(@NonNull View itemView) {
                super(itemView);
                tv_member = (TextView) itemView.findViewById(R.id.tvMember);
            }
        }
    }

    interface MemberSelectListener {
        void onSelected(Members members);
    }
}
