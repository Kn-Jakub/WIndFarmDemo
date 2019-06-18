package sk.fri.uniza.resources;

import io.dropwizard.hibernate.UnitOfWork;
import sk.fri.uniza.api.WeatherRecord;
import sk.fri.uniza.api.WeatherRecordList;
import sk.fri.uniza.db.WeatherRecordDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/weather/data")
public class WeatherDataResource {
    private final static java.util.logging.Logger LOGGER = Logger.getLogger(WeatherDataResource.class.getName());

    private WeatherRecordDao weatherRecordDao;

    public WeatherDataResource(WeatherRecordDao weatherRecordDao) {
        this.weatherRecordDao = weatherRecordDao;
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
                weatherRecordDao.save(record);              // save record to the weatherRecordDao
            }
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
    public List<WeatherRecord> storeWeatherRecordList(@QueryParam("cityID") Long cityID){
        return weatherRecordDao.getAllCityRecords(cityID);
    }
}
