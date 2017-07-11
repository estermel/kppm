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

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "diurus",
        "jenis",
        "waktudatang",
        "waktupulang",
        "keterangan"
})
public class BandaraHotel {

    @JsonProperty("diurus")
    private boolean diurus;
    @JsonProperty("jenis")
    private String jenis;
    @JsonProperty("waktudatang")
    private String waktudatang;
    @JsonProperty("waktupulang")
    private String waktupulang;
    @JsonProperty("keterangan")
    private String keterangan;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("diurus")
    public boolean getDiurus() {
        return diurus;
    }

    @JsonProperty("diurus")
    public void setDiurus(boolean diurus) {
        this.diurus = diurus;
    }

    @JsonProperty("jenis")
    public String getJenis() {
        return jenis;
    }

    @JsonProperty("jenis")
    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    @JsonProperty("waktudatang")
    public String getWaktudatang() {
        return waktudatang;
    }

    @JsonProperty("waktudatang")
    public void setWaktudatang(String waktudatang) {
        this.waktudatang = waktudatang;
    }

    @JsonProperty("waktupulang")
    public String getWaktupulang() {
        return waktupulang;
    }

    @JsonProperty("waktupulang")
    public void setWaktupulang(String waktupulang) {
        this.waktupulang = waktupulang;
    }

    @JsonProperty("keterangan")
    public String getKeterangan() {
        return keterangan;
    }

    @JsonProperty("keterangan")
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