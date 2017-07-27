package com.ester.kppm.model;

/**
 * Created by mel on 7/11/17.
 */

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.gson.annotations.SerializedName;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "diurus",
        "jenis",
        "waktudatang",
        "waktupulang",
        "keterangan"
})
public class BandaraHotel {

    @SerializedName("diurus")
    private boolean diurus;
    @SerializedName("jenis")
    private String jenis;
    @SerializedName("waktudatang")
    private String waktudatang;
    @SerializedName("waktupulang")
    private String waktupulang;
    @SerializedName("keterangan")
    private String keterangan;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @SerializedName("diurus")
    public boolean getDiurus() {
        return diurus;
    }

    @SerializedName("diurus")
    public void setDiurus(boolean diurus) {
        this.diurus = diurus;
    }

    @SerializedName("jenis")
    public String getJenis() {
        return jenis;
    }

    @SerializedName("jenis")
    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    @SerializedName("waktudatang")
    public String getWaktudatang() {
        return waktudatang;
    }

    @SerializedName("waktudatang")
    public void setWaktudatang(String waktudatang) {
        this.waktudatang = waktudatang;
    }

    @SerializedName("waktupulang")
    public String getWaktupulang() {
        return waktupulang;
    }

    @SerializedName("waktupulang")
    public void setWaktupulang(String waktupulang) {
        this.waktupulang = waktupulang;
    }

    @SerializedName("keterangan")
    public String getKeterangan() {
        return keterangan;
    }

    @SerializedName("keterangan")
    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
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