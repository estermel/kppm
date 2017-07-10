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
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "username",
        "password",
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
        "bandara-hotel",
        "hotel-acara",
        "hotel",
        "tipekamar",
        "kasur",
        "konsumsi1",
        "konsumsi2",
        "konsumsi3",
        "totalharga"
})
public class RegistrasiModel {

    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;
    @JsonProperty("role")
    private String role;
    @JsonProperty("nama")
    private String nama;
    @JsonProperty("jabatan")
    private String jabatan;
    @JsonProperty("gerejaorg")
    private String gerejaorg;
    @JsonProperty("ktp")
    private String ktp;
    @JsonProperty("nohp")
    private String nohp;
    @JsonProperty("umur")
    private int umur;
    @JsonProperty("denganistri")
    private boolean denganistri;
    @JsonProperty("namaistri")
    private String namaistri;
    @JsonProperty("provinsi")
    private String provinsi;
    @JsonProperty("kota")
    private String kota;
    @JsonProperty("alamat")
    private String alamat;
    @JsonProperty("bandara-hotel")
    private BandaraHotel bandaraHotel;
    @JsonProperty("hotel-acara")
    private HotelAcara hotelAcara;
    @JsonProperty("hotel")
    private String hotel;
    @JsonProperty("tipekamar")
    private String tipekamar;
    @JsonProperty("kasur")
    private int kasur;
    @JsonProperty("konsumsi1")
    private boolean konsumsi1;
    @JsonProperty("konsumsi2")
    private boolean konsumsi2;
    @JsonProperty("konsumsi3")
    private boolean konsumsi3;
    @JsonProperty("totalharga")
    private float totalharga;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("username")
    public String getUsername() {
        return username;
    }

    @JsonProperty("username")
    public void setUsername(String username) {
        this.username = username;
    }

    @JsonProperty("password")
    public String getPassword() {
        return password;
    }

    @JsonProperty("password")
    public void setPassword(String password) {
        this.password = password;
    }

    @JsonProperty("role")
    public String getRole() {
        return role;
    }

    @JsonProperty("role")
    public void setRole(String role) {
        this.role = role;
    }

    @JsonProperty("nama")
    public String getNama() {
        return nama;
    }

    @JsonProperty("nama")
    public void setNama(String nama) {
        this.nama = nama;
    }

    @JsonProperty("jabatan")
    public String getJabatan() {
        return jabatan;
    }

    @JsonProperty("jabatan")
    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    @JsonProperty("gerejaorg")
    public String getGerejaorg() {
        return gerejaorg;
    }

    @JsonProperty("gerejaorg")
    public void setGerejaorg(String gerejaorg) {
        this.gerejaorg = gerejaorg;
    }

    @JsonProperty("ktp")
    public String getKtp() {
        return ktp;
    }

    @JsonProperty("ktp")
    public void setKtp(String ktp) {
        this.ktp = ktp;
    }

    @JsonProperty("nohp")
    public String getNohp() {
        return nohp;
    }

    @JsonProperty("nohp")
    public void setNohp(String nohp) {
        this.nohp = nohp;
    }

    @JsonProperty("umur")
    public int getUmur() {
        return umur;
    }

    @JsonProperty("umur")
    public void setUmur(int umur) {
        this.umur = umur;
    }

    @JsonProperty("denganistri")
    public boolean getDenganistri() {
        return denganistri;
    }

    @JsonProperty("denganistri")
    public void setDenganistri(boolean denganistri) {
        this.denganistri = denganistri;
    }

    @JsonProperty("namaistri")
    public String getNamaistri() {
        return namaistri;
    }

    @JsonProperty("namaistri")
    public void setNamaistri(String namaistri) {
        this.namaistri = namaistri;
    }

    @JsonProperty("provinsi")
    public String getProvinsi() {
        return provinsi;
    }

    @JsonProperty("provinsi")
    public void setProvinsi(String provinsi) {
        this.provinsi = provinsi;
    }

    @JsonProperty("kota")
    public String getKota() {
        return kota;
    }

    @JsonProperty("kota")
    public void setKota(String kota) {
        this.kota = kota;
    }

    @JsonProperty("alamat")
    public String getAlamat() {
        return alamat;
    }

    @JsonProperty("alamat")
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    @JsonProperty("bandara-hotel")
    public BandaraHotel getBandaraHotel() {
        return bandaraHotel;
    }

    @JsonProperty("bandara-hotel")
    public void setBandaraHotel(BandaraHotel bandaraHotel) {
        this.bandaraHotel = bandaraHotel;
    }

    @JsonProperty("hotel-acara")
    public HotelAcara getHotelAcara() {
        return hotelAcara;
    }

    @JsonProperty("hotel-acara")
    public void setHotelAcara(HotelAcara hotelAcara) {
        this.hotelAcara = hotelAcara;
    }

    @JsonProperty("hotel")
    public String getHotel() {
        return hotel;
    }

    @JsonProperty("hotel")
    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    @JsonProperty("tipekamar")
    public String getTipekamar() {
        return tipekamar;
    }

    @JsonProperty("tipekamar")
    public void setTipekamar(String tipekamar) {
        this.tipekamar = tipekamar;
    }

    @JsonProperty("kasur")
    public int getKasur() {
        return kasur;
    }

    @JsonProperty("kasur")
    public void setKasur(int kasur) {
        this.kasur = kasur;
    }

    @JsonProperty("konsumsi1")
    public boolean getKonsumsi1() {
        return konsumsi1;
    }

    @JsonProperty("konsumsi1")
    public void setKonsumsi1(boolean konsumsi1) {
        this.konsumsi1 = konsumsi1;
    }

    @JsonProperty("konsumsi2")
    public boolean getKonsumsi2() {
        return konsumsi2;
    }

    @JsonProperty("konsumsi2")
    public void setKonsumsi2(boolean konsumsi2) {
        this.konsumsi2 = konsumsi2;
    }

    @JsonProperty("konsumsi3")
    public boolean getKonsumsi3() {
        return konsumsi3;
    }

    @JsonProperty("konsumsi3")
    public void setKonsumsi3(boolean konsumsi3) {
        this.konsumsi3 = konsumsi3;
    }

    @JsonProperty("totalharga")
    public float getTotalharga() {
        return totalharga;
    }

    @JsonProperty("totalharga")
    public void setTotalharga(float totalharga) {
        this.totalharga = totalharga;
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