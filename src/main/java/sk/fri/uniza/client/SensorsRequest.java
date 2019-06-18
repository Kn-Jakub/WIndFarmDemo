package sk.fri.uniza.client;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;



public interface SensorsRequest {
    /**
     * Request to set cities that will be followed.
     *
     * @param IDs IDs of cities which about information is requested.
     */
    @POST("/settings/cities")
    Call<Void> setFollowedCities(@Query("cityIDs") String IDs);
}
