package sk.fri.uniza.client;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import sk.fri.uniza.api.CityApiKey;

import java.util.List;


public interface SensorsRequest {
    /**
     * Request to set cities that will be followed.
     *
     * @param cities City-Key pairs of followed cities
     */
    @POST("/settings/cities")
    Call<Void> setFollowedCities(@Body List<CityApiKey> cities);
}
