package com.hungnvph40917.lesson5.demo;

public class SanPham {
    private String MaSP, TenSP, MoTa;

    public SanPham() {
    }

    public String getMaSP() {
        return MaSP;
    }

    public void setMaSP(String maSP) {
        MaSP = maSP;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String tenSP) {
        TenSP = tenSP;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String moTa) {
        MoTa = moTa;
    }

    public SanPham(String maSP, String tenSP, String moTa) {
        MaSP = maSP;
        TenSP = tenSP;
        MoTa = moTa;
    }
}

