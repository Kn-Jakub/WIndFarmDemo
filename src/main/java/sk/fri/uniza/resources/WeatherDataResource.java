package sk.fri.uniza.resources;

import io.dropwizard.hibernate.UnitOfWork;
import sk.fri.uniza.api.CityApiKey;
import sk.fri.uniza.api.WeatherRecord;
import sk.fri.uniza.api.WeatherRecordList;
import sk.fri.uniza.auth.CityAuthenticator;
import sk.fri.uniza.db.WeatherRecordDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/weather/data")
public class WeatherDataResource {
    private final static java.util.logging.Logger LOGGER = Logger.getLogger(WeatherDataResource.class.getName());

    private WeatherRecordDao weatherRecordDao;
    private CityAuthenticator cityAuthenticator;

    public WeatherDataResource(WeatherRecordDao weatherRecordDao, CityAuthenticator cityAuthenticator) {
        this.weatherRecordDao = weatherRecordDao;
        this.cityAuthenticator = cityAuthenticator;
    }

    @POST
    @Path("/recordList")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public Response storeWeatherRecordList(WeatherRecordList recordList){
        if (recordList.getCnt() > 0){
            for (WeatherRecord record : recordList.getList()){
                LOGGER.log(Level.INFO, "Received record from city : id = " + record.getCityId()
                        + ", temperature=" + record.getTemperature()
                        + ", pressure=" + record.getPressure()
                        + ", humidity=" + record.getHumidity()
                        + ", time=" + record.getCreationTime()
                );
                weatherRecordDao.save(record);              // save record to the database
            }
            return Response.ok().build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @POST
    @Path("/record")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public Response storeWeatherRecord(WeatherRecord record, @QueryParam("key") String key){
        Optional<CityApiKey> keyOptional = cityAuthenticator.authenticate(key);

        CityApiKey cityApiKey = keyOptional.orElseThrow(() -> {
            throw new WebApplicationException("Wrong key!", Response.Status.BAD_REQUEST);
        });

        if (record != null){

            if(!record.getCityId().equals(cityApiKey.getCityID())){   // id of ciy from authentication and from record does not match
                LOGGER.log(Level.WARNING, "Id of ciy from authentication and from record does not match");
                return Response.status(Response.Status.BAD_REQUEST).build();
            }

            LOGGER.log(Level.INFO, "STORING RECEIVED RECORD from city : id = " + record.getCityId()
                    + ", temperature=" + record.getTemperature()
                    + ", pressure=" + record.getPressure()
                    + ", humidity=" + record.getHumidity()
                    + ", time=" + record.getCreationTime()
            );
            weatherRecordDao.save(record);              // save record to the database

            return Response.ok().build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }


    /**
     * Resource for get weather records for specified city.
     * @param cityID ID of the city which comments
     * @return
     */
    @GET
    @Path("/recordList")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    //@PermitAll
    public List<WeatherRecord> getWeatherRecordList(@QueryParam("cityID") Long cityID, @QueryParam("limit") Integer limit){
        if(limit == null){
            return weatherRecordDao.getAllCityRecords(cityID);
        } else {
            return weatherRecordDao.getAllCityRecords(cityID, limit);
        }

    }
}
