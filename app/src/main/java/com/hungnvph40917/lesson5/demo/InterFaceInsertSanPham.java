package com.hungnvph40917.lesson5.demo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface InterFaceInsertSanPham {
    @FormUrlEncoded
    @POST("insert.php")
    Call<SvrResponseSanPham> insertSanPham(
            @Field("MaSP") String MaSP,
            @Field("TenSP") String TenSP,
            @Field("MoTa") String MoTa
    );
}
