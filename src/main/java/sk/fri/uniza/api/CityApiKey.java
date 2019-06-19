package sk.fri.uniza.api;


import com.fasterxml.jackson.annotation.JsonProperty;

public class CityApiKey {
    @JsonProperty("apiKey")
    private String apiKey;
    @JsonProperty("cityID")
    private Integer cityID;

    public CityApiKey() {
    }

    public CityApiKey(String apiKey, Integer cityID) {
        this.apiKey = apiKey;
        this.cityID = cityID;
    }

    @JsonProperty("apiKey")
    public String getApiKey() {
        return apiKey;
    }

    @JsonProperty("cityID")
    public Integer getCityID() {
        return cityID;
    }
}
