package com.example.ram.crudsample;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<StudentModel> studentModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        studentModelList = new ArrayList<>();
        DatabaseHelper databaseHelper = new DatabaseHelper(ViewActivity.this);
        studentModelList = databaseHelper.selectAll();

        recyclerView = findViewById(R.id.rvDatas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ListAdapter adapter = new ListAdapter(this, studentModelList);
        recyclerView.setAdapter(adapter);
    }

    public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

        private List<StudentModel> studentModelList;
        private LayoutInflater mInflater;

        ListAdapter(Context context, List<StudentModel> studentModelList) {
            this.mInflater = LayoutInflater.from(context);
            this.studentModelList = studentModelList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.list_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            StudentModel studentModel = studentModelList.get(position);
            holder.tvId.setText(studentModel.getId());
            holder.tvName.setText(studentModel.getName());
            holder.tvEmail.setText(studentModel.getEmail());
        }

        @Override
        public int getItemCount() {
            return studentModelList.size();
        }


        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView tvId,tvName,tvEmail;

            ViewHolder(View itemView) {
                super(itemView);
                tvId = itemView.findViewById(R.id.tvId);
                tvName = itemView.findViewById(R.id.tvName);
                tvEmail = itemView.findViewById(R.id.tvEmail);
            }

        }

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ViewActivity.this,CRUDActivity.class);
        startActivity(intent);
        finish();
    }
}
