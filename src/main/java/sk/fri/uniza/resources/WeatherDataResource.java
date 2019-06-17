package sk.fri.uniza.resources;

import io.dropwizard.hibernate.UnitOfWork;
import sk.fri.uniza.api.WeatherRecord;
import sk.fri.uniza.api.WeatherRecordList;
import sk.fri.uniza.db.WeatherRecordDao;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/weather/data")
public class WeatherDataResource {
    private final static java.util.logging.Logger LOGGER = Logger.getLogger(WeatherDataResource.class.getName());

    private WeatherRecordDao database;

    public WeatherDataResource(WeatherRecordDao database) {
        this.database = database;
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
                database.save(record);              // save record to the database
            }
            return Response.ok().build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
