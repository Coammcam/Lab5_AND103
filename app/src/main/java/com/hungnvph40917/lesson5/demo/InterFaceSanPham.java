package com.hungnvph40917.lesson5.demo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface InterFaceSanPham {
    @FormUrlEncoded
    @POST("insert.php")
    Call<SvrResponseSanPham> insertSanPham(@Field("MaSP") String MaSP, @Field("TenSP") String TenSP, @Field("MoTa") String MoTa);

    @GET("select.php")
    Call<SvrResponseSanPham> selectSanPham();

    @FormUrlEncoded
    @POST("delete.php")
    Call<SvrResponseSanPham> deleteSanPham(@Field("MaSP") String MaSP);

    @FormUrlEncoded
    @POST("update.php")
    Call<SvrResponseSanPham> updateSanPham(@Field("MaSP") String MaSP, @Field("TenSP") String TenSP, @Field("MoTa") String MoTa);
}
