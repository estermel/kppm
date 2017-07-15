package com.ester.kppm.model;

/**
 * Created by mel on 7/3/17.
 */
import java.util.HashMap;
import java.util.List;
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
        "namahotel",
        "tipekamar",
        "status",
        "info"
})
public class HotelModel {

    @JsonProperty("id")
    private int id;
    @JsonProperty("namahotel")
    private String namahotel;
    @JsonProperty("tipekamar")
    private List<TipeKamar> tipekamar;
    @JsonProperty("status")
    private String status;
    @JsonProperty("info")
    private String info;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("namahotel")
    public String getNamahotel() {
        return namahotel;
    }

    @JsonProperty("namahotel")
    public void setNamahotel(String namahotel) {
        this.namahotel = namahotel;
    }

    @JsonProperty("tipekamar")
    public List<TipeKamar> getTipekamar() {
        return tipekamar;
    }

    @JsonProperty("tipekamar")
    public void setTipekamar(List<TipeKamar> tipekamar) {
        this.tipekamar = tipekamar;
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
