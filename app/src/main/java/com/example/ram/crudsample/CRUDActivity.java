package com.example.ram.crudsample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class CRUDActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = CRUDActivity.class.getCanonicalName();
    private Button btnUpdate, btnInsert, btnDelete, btnSelect, btnSelectAll;
    private EditText edtID, edtEmail, edtName;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud);

        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnInsert = (Button) findViewById(R.id.btnInsert);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnSelect = (Button) findViewById(R.id.btnSelect);
        btnSelectAll = (Button) findViewById(R.id.btnSelectAll);

        databaseHelper = new DatabaseHelper(CRUDActivity.this);

        edtID = (EditText) findViewById(R.id.edtId);
        edtEmail = (EditText) findViewById(R.id.edtEmailId);
        edtName = (EditText) findViewById(R.id.edtName);

        btnUpdate.setOnClickListener(this);
        btnInsert.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnSelect.setOnClickListener(this);
        btnSelectAll.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String id = edtID.getText().toString().trim();
        String name = edtEmail.getText().toString().trim();
        String email = edtName.getText().toString().trim();
        switch (v.getId()) {
            case R.id.btnUpdate:
                if (id.equalsIgnoreCase("")) {
                    Toast.makeText(CRUDActivity.this, "Enter ID", Toast.LENGTH_SHORT).show();
                } else if (name.equalsIgnoreCase("")) {
                    Toast.makeText(CRUDActivity.this, "Enter Name", Toast.LENGTH_SHORT).show();
                } else if (email.equalsIgnoreCase("")) {
                    Toast.makeText(CRUDActivity.this, "Enter Email", Toast.LENGTH_SHORT).show();
                } else {
                    databaseHelper.update(id, name, email);

                    edtID.setText("");
                    edtEmail.setText("");
                    edtName.setText("");
                    Toast.makeText(CRUDActivity.this, "Updated Successfully", Toast.LENGTH_SHORT).show();

                }

                break;
            case R.id.btnInsert:
                if (id.equalsIgnoreCase("")) {
                    Toast.makeText(CRUDActivity.this, "Enter ID", Toast.LENGTH_SHORT).show();
                } else if (name.equalsIgnoreCase("")) {
                    Toast.makeText(CRUDActivity.this, "Enter Name", Toast.LENGTH_SHORT).show();
                } else if (email.equalsIgnoreCase("")) {
                    Toast.makeText(CRUDActivity.this, "Enter Email", Toast.LENGTH_SHORT).show();
                } else {
                    databaseHelper.insert(id, name, email);
                    edtID.setText("");
                    edtEmail.setText("");
                    edtName.setText("");
                    Toast.makeText(CRUDActivity.this, "Inserted Successfully", Toast.LENGTH_SHORT).show();

                }
                break;
            case R.id.btnDelete:
                if (id.equalsIgnoreCase("")) {
                    Toast.makeText(CRUDActivity.this, "Enter ID", Toast.LENGTH_SHORT).show();
                } else {
                    databaseHelper.delete(id, name, email);
                    edtID.setText("");
                    edtEmail.setText("");
                    edtName.setText("");
                    Toast.makeText(CRUDActivity.this, "Deleted Successfully", Toast.LENGTH_SHORT).show();

                }
                break;
            case R.id.btnSelect:
                if (id.equalsIgnoreCase("")) {
                    Toast.makeText(CRUDActivity.this, "Enter ID", Toast.LENGTH_SHORT).show();
                } else {
                    List<StudentModel> studentModelList = databaseHelper.select(id, name, email);
                    Toast.makeText(CRUDActivity.this, "ID : " + studentModelList.get(0).getId() + "\n"
                            + "Name : " + studentModelList.get(0).getName() + "\n"
                            + "Email : " + studentModelList.get(0).getEmail(), Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnSelectAll:
                Intent intent = new Intent(CRUDActivity.this, ViewActivity.class);
                startActivity(intent);
                finish();
                break;
        }

    }
}
