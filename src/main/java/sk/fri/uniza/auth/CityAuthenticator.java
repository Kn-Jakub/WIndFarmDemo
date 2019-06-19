package sk.fri.uniza.auth;

import sk.fri.uniza.api.CityApiKey;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public class CityAuthenticator{

    private ArrayList<CityApiKey> authenticatedCities;

    public CityAuthenticator() {
        this.authenticatedCities = new ArrayList<>();
    }

    public Optional<CityApiKey> authenticate(String apiKey) {
        for (CityApiKey city : authenticatedCities){
            if(city.getApiKey().equals(apiKey)){
                return Optional.of(city);
            }
        }
        return Optional.empty();
    }

    /**
     * Registers new city to be authenticated and return its city key pair.
     * @param cityID ID of the city to be registered.
     * @return City - key pair of the newly registered city.
     */
    public CityApiKey registerNewCity(Integer cityID){
        CityApiKey createdCityApiKey = new CityApiKey(UUID.randomUUID().toString(), cityID);
        authenticatedCities.add(createdCityApiKey);
        return createdCityApiKey;
    }

    /**
     * Unregisters city from the authorization.
     * @param city ID of the city to be registered.
     * @return true then the city has been unregistered successfully, otherwise false.
     */
    public boolean unregisterCity(CityApiKey city){
        int index = -1;

        for(CityApiKey c : authenticatedCities){
            if(c.equals(city)){
                index = authenticatedCities.indexOf(c);
            }
        }

        if(index > 0){
            authenticatedCities.remove(index);
            return true;
        }
        return false;
    }

    /**
     * Unregisters all cities from the authorization.
     */
    public void unregisterAll(){
        authenticatedCities.clear();
    }

    /**
     * Returns all authenticated cities City-key pairs
     * @return City-key pairs of authenticated cities
     */
    public ArrayList<CityApiKey> getAuthenticatedCities() {
        return authenticatedCities;
    }
}
