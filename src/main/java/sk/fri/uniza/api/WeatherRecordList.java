package sk.fri.uniza.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "cnt",
        "list"
})
public class WeatherRecordList {
    @JsonProperty("cnt")
    private Integer cnt;
    @JsonProperty("list")
    private List<WeatherRecord> list = null;

    public WeatherRecordList() {
        cnt = 0;
        list = new ArrayList<WeatherRecord>();
    }

    public WeatherRecordList(Integer cnt, List<WeatherRecord> list) {
        this.cnt = cnt;
        this.list = list;
    }

    @JsonProperty("cnt")
    public Integer getCnt() {
        return cnt;
    }
    @JsonProperty("cnt")
    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    @JsonProperty("list")
    public List<WeatherRecord> getList() {
        return list;
    }
    @JsonProperty("list")
    public void setList(List<WeatherRecord> list) {
        this.list = list;
    }
}
