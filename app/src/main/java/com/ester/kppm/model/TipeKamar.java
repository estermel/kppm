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
        "nama",
        "single",
        "double",
        "ekstrabed"
})
public class TipeKamar {

    @JsonProperty("nama")
    private String nama;
    @JsonProperty("single")
    private float single;
    @JsonProperty("double")
    private float _double;
    @JsonProperty("ekstrabed")
    private float ekstrabed;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("nama")
    public String getNama() {
        return nama;
    }

    @JsonProperty("nama")
    public void setNama(String nama) {
        this.nama = nama;
    }

    @JsonProperty("single")
    public float getSingle() {
        return single;
    }

    @JsonProperty("single")
    public void setSingle(float single) {
        this.single = single;
    }

    @JsonProperty("double")
    public float getDouble() {
        return _double;
    }

    @JsonProperty("double")
    public void setDouble(float _double) {
        this._double = _double;
    }

    @JsonProperty("ekstrabed")
    public float getEkstrabed() {
        return ekstrabed;
    }

    @JsonProperty("ekstrabed")
    public void setEkstrabed(float ekstrabed) {
        this.ekstrabed = ekstrabed;
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