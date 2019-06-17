package sk.fri.uniza.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.sql.Timestamp;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "cityId",
        "temperature",
        "pressure",
        "humidity",
        "creationTimeStamp"
})
public class WeatherRecord {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("cityId")
    private Integer cityId;
    @JsonProperty("temperature")
    private double temperature;
    @JsonProperty("pressure")
    private double pressure;
    @JsonProperty("humidity")
    private double humidity;
    @JsonProperty("creationTimeStamp")
    private Timestamp creationTime;

    public WeatherRecord() {
        this.id = null;
        this.cityId = null;
        this.temperature = 0;
        this.pressure = 0;
        this.humidity = 0;
        this.creationTime = null;
    }

    public WeatherRecord(Integer cityId, double temperature, double pressure, double humidity, Timestamp creationTime) {
        this.id = null;
        this.cityId = cityId;
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.creationTime = creationTime;
    }

    public WeatherRecord(Long id, Integer cityId, double temperature, double pressure, double humidity, Timestamp creationTime) {
        this.id = id;
        this.cityId = cityId;
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.creationTime = creationTime;
    }

    @JsonProperty("id")
    public Long getId() {
        return id;
    }
    @JsonProperty("id")
    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("cityId")
    public Integer getCityId() {
        return cityId;
    }
    @JsonProperty("cityId")
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    @JsonProperty("temperature")
    public double getTemperature() {
        return temperature;
    }
    @JsonProperty("temperature")
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    @JsonProperty("pressure")
    public double getPressure() {
        return pressure;
    }
    @JsonProperty("pressure")
    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    @JsonProperty("humidity")
    public double getHumidity() {
        return humidity;
    }
    @JsonProperty("humidity")
    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    @JsonProperty("creationTimeStamp")
    public Timestamp getCreationTime() {
        return creationTime;
    }
    @JsonProperty("creationTimeStamp")
    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }
}
