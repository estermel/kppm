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
        "period",
        "harga",
        "response"
})
public class KonsumsiModel {

    @JsonProperty("period")
    private int period;
    @JsonProperty("harga")
    private float harga;
    @JsonProperty("response")
    private Response response;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("period")
    public int getPeriod() {
        return period;
    }

    @JsonProperty("period")
    public void setPeriod(int period) {
        this.period = period;
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