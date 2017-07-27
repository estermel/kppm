package com.ester.kppm.model;

/**
 * Created by mel on 7/11/17.
 */

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.gson.annotations.SerializedName;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "role",
        "nama",
        "jabatan",
        "gerejaorg",
        "ktp",
        "nohp",
        "umur",
        "denganistri",
        "namaistri",
        "provinsi",
        "kota",
        "alamat",
        "bandarahotel",
        "hotelacara",
        "hotel",
        "tipekamar",
        "kasur",
        "konsumsi1",
        "konsumsi2",
        "totalharga",
        "status",
        "info" // return kodepeserta
})
public class PesertaModel {

    @SerializedName("role")
    private String role;
    @SerializedName("nama")
    private String nama;
    @SerializedName("jabatan")
    private String jabatan;
    @SerializedName("gerejaorg")
    private String gerejaorg;
    @SerializedName("ktp")
    private String ktp;
    @SerializedName("nohp")
    private String nohp;
    @SerializedName("umur")
    private int umur;
    @SerializedName("denganistri")
    private boolean denganistri;
    @SerializedName("namaistri")
    private String namaistri;
    @SerializedName("provinsi")
    private String provinsi;
    @SerializedName("kota")
    private String kota;
    @SerializedName("alamat")
    private String alamat;
    @SerializedName("bandarahotel")
    private BandaraHotel bandaraHotel;
    @SerializedName("hotelacara")
    private HotelAcara hotelAcara;
    @SerializedName("hotel")
    private String hotel;
    @SerializedName("tipekamar")
    private String tipekamar;
    @SerializedName("kasur")
    private int kasur;
    @SerializedName("konsumsi1")
    private boolean konsumsi1;
    @SerializedName("konsumsi2")
    private boolean konsumsi2;
    @SerializedName("totalharga")
    private float totalharga;
    @SerializedName("status")
    private String status;
    @SerializedName("info")
    private String info;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @SerializedName("role")
    public String getRole() {
        return role;
    }

    @SerializedName("role")
    public void setRole(String role) {
        this.role = role;
    }

    @SerializedName("nama")
    public String getNama() {
        return nama;
    }

    @SerializedName("nama")
    public void setNama(String nama) {
        this.nama = nama;
    }

    @SerializedName("jabatan")
    public String getJabatan() {
        return jabatan;
    }

    @SerializedName("jabatan")
    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    @SerializedName("gerejaorg")
    public String getGerejaorg() {
        return gerejaorg;
    }

    @SerializedName("gerejaorg")
    public void setGerejaorg(String gerejaorg) {
        this.gerejaorg = gerejaorg;
    }

    @SerializedName("ktp")
    public String getKtp() {
        return ktp;
    }

    @SerializedName("ktp")
    public void setKtp(String ktp) {
        this.ktp = ktp;
    }

    @SerializedName("nohp")
    public String getNohp() {
        return nohp;
    }

    @SerializedName("nohp")
    public void setNohp(String nohp) {
        this.nohp = nohp;
    }

    @SerializedName("umur")
    public int getUmur() {
        return umur;
    }

    @SerializedName("umur")
    public void setUmur(int umur) {
        this.umur = umur;
    }

    @SerializedName("denganistri")
    public boolean getDenganistri() {
        return denganistri;
    }

    @SerializedName("denganistri")
    public void setDenganistri(boolean denganistri) {
        this.denganistri = denganistri;
    }

    @SerializedName("namaistri")
    public String getNamaistri() {
        return namaistri;
    }

    @SerializedName("namaistri")
    public void setNamaistri(String namaistri) {
        this.namaistri = namaistri;
    }

    @SerializedName("provinsi")
    public String getProvinsi() {
        return provinsi;
    }

    @SerializedName("provinsi")
    public void setProvinsi(String provinsi) {
        this.provinsi = provinsi;
    }

    @SerializedName("kota")
    public String getKota() {
        return kota;
    }

    @SerializedName("kota")
    public void setKota(String kota) {
        this.kota = kota;
    }

    @SerializedName("alamat")
    public String getAlamat() {
        return alamat;
    }

    @SerializedName("alamat")
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    @SerializedName("bandarahotel")
    public BandaraHotel getBandaraHotel() {
        return bandaraHotel;
    }

    @SerializedName("bandarahotel")
    public void setBandaraHotel(BandaraHotel bandaraHotel) {
        this.bandaraHotel = bandaraHotel;
    }

    @SerializedName("hotelacara")
    public HotelAcara getHotelAcara() {
        return hotelAcara;
    }

    @SerializedName("hotelacara")
    public void setHotelAcara(HotelAcara hotelAcara) {
        this.hotelAcara = hotelAcara;
    }

    @SerializedName("hotel")
    public String getHotel() {
        return hotel;
    }

    @SerializedName("hotel")
    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    @SerializedName("tipekamar")
    public String getTipekamar() {
        return tipekamar;
    }

    @SerializedName("tipekamar")
    public void setTipekamar(String tipekamar) {
        this.tipekamar = tipekamar;
    }

    @SerializedName("kasur")
    public int getKasur() {
        return kasur;
    }

    @SerializedName("kasur")
    public void setKasur(int kasur) {
        this.kasur = kasur;
    }

    @SerializedName("konsumsi1")
    public boolean getKonsumsi1() {
        return konsumsi1;
    }

    @SerializedName("konsumsi1")
    public void setKonsumsi1(boolean konsumsi1) {
        this.konsumsi1 = konsumsi1;
    }

    @SerializedName("konsumsi2")
    public boolean getKonsumsi2() {
        return konsumsi2;
    }

    @SerializedName("konsumsi2")
    public void setKonsumsi2(boolean konsumsi2) {
        this.konsumsi2 = konsumsi2;
    }

    @SerializedName("totalharga")
    public float getTotalharga() {
        return totalharga;
    }

    @SerializedName("totalharga")
    public void setTotalharga(float totalharga) {
        this.totalharga = totalharga;
    }

    public boolean isDenganistri() {
        return denganistri;
    }

    public boolean isKonsumsi1() {
        return konsumsi1;
    }

    public boolean isKonsumsi2() {
        return konsumsi2;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}