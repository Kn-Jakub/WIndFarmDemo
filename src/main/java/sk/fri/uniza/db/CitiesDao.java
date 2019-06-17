package sk.fri.uniza.db;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import sk.fri.uniza.api.City;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class CitiesDao extends AbstractDAO<City> {
    public CitiesDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }


    public List<City> getAll() {
        CriteriaBuilder builder = currentSession().getCriteriaBuilder();
        CriteriaQuery<City> criteriaQuery = builder.createQuery(City.class);
        Root<City> root = criteriaQuery.from(City.class);
        criteriaQuery.select(root);
        return list(criteriaQuery);
    }

    public List<City> getAllFromCountry(String country) {
        CriteriaBuilder builder = currentSession().getCriteriaBuilder();
        CriteriaQuery<City> criteriaQuery = builder.createQuery(City.class);
        Root<City> root = criteriaQuery.from(City.class);
        criteriaQuery.select(root).where(builder.like(root.get("country"), country));       // condition to get only cities from specified country
        return list(criteriaQuery);
    }
}
