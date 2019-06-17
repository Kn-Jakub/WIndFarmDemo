package sk.fri.uniza.resources;

import io.dropwizard.hibernate.UnitOfWork;
import sk.fri.uniza.api.City;
import sk.fri.uniza.db.CitiesDao;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Path("/cities")
public class CitiesResource {
    private static final String ALL_COUNTRIES_STR = "all";
    private CitiesDao citiesDao;
    private ArrayList<String> countries;


    public CitiesResource(CitiesDao citiesDao) {
        this.citiesDao = citiesDao;

        countries = new ArrayList<>();
    }

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @PermitAll
    public List<City> getCities(@QueryParam("country") Optional<String> country){
        final String countryStr = country.orElse(ALL_COUNTRIES_STR);
        ArrayList<City> cities = null;

        if(countryStr.equals(ALL_COUNTRIES_STR)){
            return citiesDao.getAll();
        } else {
            return citiesDao.getAllFromCountry(countryStr);
        }
    }

    @GET
    @Path("/countries")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @PermitAll
    public List<String> getAllCountries(){
        if(countries.size() == 0){
            readAllCountries();
        }
        return countries;
    }

    private void readAllCountries(){
        // find out all countries
        final ArrayList<City> cities = new ArrayList<>(citiesDao.getAll());

        for(City city : cities){
            boolean found = false;
            String currentCountry = city.getCountry();
            for(String country : countries){
                if(country.equals(currentCountry)){
                    found = true;
                    break;
                }
            }

            if(!found){
                countries.add(currentCountry);
            }
        }
        /* Sort statement*/
        Collections.sort(countries);
    }
}
