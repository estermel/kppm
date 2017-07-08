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
        "trip",
        "jenis",
        "harga",
        "response"
})
public class TransportasiModel {

    @JsonProperty("trip")
    private String trip;
    @JsonProperty("jenis")
    private String jenis;
    @JsonProperty("harga")
    private float harga;
    @JsonProperty("response")
    private Response response;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("trip")
    public String getTrip() {
        return trip;
    }

    @JsonProperty("trip")
    public void setTrip(String trip) {
        this.trip = trip;
    }

    @JsonProperty("jenis")
    public String getJenis() {
        return jenis;
    }

    @JsonProperty("jenis")
    public void setJenis(String jenis) {
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

    @JsonProperty("response")
    public Response getResponse() {
        return response;
    }

    @JsonProperty("response")
    public void setResponse(Response response) {
        this.response = response;
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