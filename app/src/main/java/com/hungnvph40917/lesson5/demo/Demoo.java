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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Demoo extends AppCompatActivity {

    EditText txt1, txt2, txt3;
    TextView tvkq;
    Button button;

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
        button.setOnClickListener(v -> {
            insertData(txt1, txt2, txt3, tvkq);
        });
    }

    private void insertData(EditText txt1, EditText txt2, EditText txt3, TextView tvketqua) {
        SanPham s = new SanPham(txt1.getText().toString(), txt2.getText().toString(), txt3.getText().toString());

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.65.101/lesson5/insert.php/").addConverterFactory(GsonConverterFactory.create()).build();

        InterFaceInsertSanPham insertSanPham = retrofit.create(InterFaceInsertSanPham.class);
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
}