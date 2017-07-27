package com.ester.kppm.model;

/**
 * Created by mel on 7/3/17.
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
        "id",
        "jenis",
        "harga",
        "status",
        "info"
})
public class KonsumsiModel {

    @JsonProperty("id")
    private int id;
    @JsonProperty("jenis")
    private int jenis;
    @JsonProperty("harga")
    private float harga;
    private String status;
    private String info;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("jenis")
    public int getJenis() {
        return jenis;
    }

    @JsonProperty("jenis")
    public void setJenis(int jenis) {
        this.jenis = jenis;
    }

    @JsonProperty("harga")
    public float getHarga() {
        return harga;
    }

    @JsonProperty("harga")
    public void setHarga(float harga) {
        this.harga = harga;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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