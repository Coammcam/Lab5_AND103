package com.hungnvph40917.lesson5.demo;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.hungnvph40917.lesson5.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Demoo extends AppCompatActivity {

    EditText txt1, txt2, txt3;
    TextView tvkq;
    Button button, select, delete, update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_demoo);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        txt1 = findViewById(R.id.edt1);
        txt2 = findViewById(R.id.edt2);
        txt3 = findViewById(R.id.edt3);
        tvkq = findViewById(R.id.tvkq);
        button = findViewById(R.id.button);
        select = findViewById(R.id.btnSelect);
        delete = findViewById(R.id.btnDelete);
        update = findViewById(R.id.btnUpdate);
        button.setOnClickListener(v -> {
            insertData(txt1, txt2, txt3, tvkq);
        });
        select.setOnClickListener(v -> {
            selectData();
        });
        delete.setOnClickListener(v -> {
            deleteData(txt1);
        });
        update.setOnClickListener(v -> {
            updateData(txt1, txt2, txt3, tvkq);
        });
    }

    String strKQ = "";
    List<SanPham> ls;

    private void selectData() {
        strKQ = "";
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.24.22.81/lesson5/").addConverterFactory(GsonConverterFactory.create()).build();
        InterFaceSanPham inter = retrofit.create(InterFaceSanPham.class);
        Call<SvrResponseSanPham> call = inter.selectSanPham();

        call.enqueue(new Callback<SvrResponseSanPham>() {
            @Override
            public void onResponse(Call<SvrResponseSanPham> call, Response<SvrResponseSanPham> response) {
                SvrResponseSanPham res = response.body();
                ls=new ArrayList<>(Arrays.asList(res.getProducts()));
                for (SanPham s: ls) {
                    strKQ += "MaSP: "+s.getMaSP() + "-" + "TenSP" + s.getTenSP() + "-" + "Mo Ta:" + s.getMoTa() + "\n";
                }
                tvkq.setText(strKQ);
            }

            @Override
            public void onFailure(Call<SvrResponseSanPham> call, Throwable throwable) {
                tvkq.setText(throwable.getMessage());
            }
        });

    }

    private void insertData(EditText txt1, EditText txt2, EditText txt3, TextView tvketqua) {
        SanPham s = new SanPham(txt1.getText().toString(), txt2.getText().toString(), txt3.getText().toString());

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.24.22.81/lesson5/insert.php/").addConverterFactory(GsonConverterFactory.create()).build();

        InterFaceSanPham insertSanPham = retrofit.create(InterFaceSanPham.class);
        Call<SvrResponseSanPham> call = insertSanPham.insertSanPham(s.getMaSP(), s.getTenSP(), s.getMoTa());

        call.enqueue(new Callback<SvrResponseSanPham>() {
            @Override
            public void onResponse(Call<SvrResponseSanPham> call, Response<SvrResponseSanPham> response) {
                SvrResponseSanPham res = response.body();
                tvketqua.setText(res.getMessage());
            }

            @Override
            public void onFailure(Call<SvrResponseSanPham> call, Throwable t) {
                tvketqua.setText(t.getMessage());
            }
        });

    }

    private void deleteData(EditText txt) {

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.24.22.81/lesson5/").addConverterFactory(GsonConverterFactory.create()).build();

        InterFaceSanPham deleleSanPham = retrofit.create(InterFaceSanPham.class);
        Call<SvrResponseSanPham> call = deleleSanPham.deleteSanPham(txt.getText().toString());

        call.enqueue(new Callback<SvrResponseSanPham>() {
            @Override
            public void onResponse(Call<SvrResponseSanPham> call, Response<SvrResponseSanPham> response) {
                SvrResponseSanPham res = response.body();
                tvkq.setText(res.getMessage());
            }

            @Override
            public void onFailure(Call<SvrResponseSanPham> call, Throwable t) {
                tvkq.setText(t.getMessage());
            }
        });

    }

    private void updateData(EditText txt1, EditText txt2, EditText txt3, TextView tvketqua) {
        SanPham s = new SanPham(txt1.getText().toString(), txt2.getText().toString(), txt3.getText().toString());

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.24.22.81/lesson5/").addConverterFactory(GsonConverterFactory.create()).build();

        InterFaceSanPham updateSanPham = retrofit.create(InterFaceSanPham.class);
        Call<SvrResponseSanPham> call = updateSanPham.updateSanPham(s.getMaSP(), s.getTenSP(), s.getMoTa());

        call.enqueue(new Callback<SvrResponseSanPham>() {
            @Override
            public void onResponse(Call<SvrResponseSanPham> call, Response<SvrResponseSanPham> response) {
                SvrResponseSanPham res = response.body();
                tvketqua.setText(res.getMessage());
            }

            @Override
            public void onFailure(Call<SvrResponseSanPham> call, Throwable t) {
                tvketqua.setText(t.getMessage());
            }
        });

    }
}