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
        "namahotel",
        "tipekamar",
        "response"
})
public class HotelModel {

    @JsonProperty("namahotel")
    private String namahotel;
    @JsonProperty("tipekamar")
    private List<TipeKamar> tipekamar = null;
    @JsonProperty("response")
    private Response response;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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
