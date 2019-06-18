package sk.fri.uniza.db;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import sk.fri.uniza.api.WeatherRecord;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class WeatherRecordDao extends AbstractDAO<WeatherRecord> {
    public WeatherRecordDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Long save(WeatherRecord record){
        return persist(record).getId();
    }

    public List<WeatherRecord> getAllCityRecords(Long cityID){
        CriteriaBuilder builder = currentSession().getCriteriaBuilder();
        CriteriaQuery<WeatherRecord> criteriaQuery = builder.createQuery(WeatherRecord.class);
        Root<WeatherRecord> root = criteriaQuery.from(WeatherRecord.class);
        criteriaQuery.select(root).where(builder.equal(root.get("cityId"), cityID));       // condition to get only cities from specified country
        return list(criteriaQuery);
    }
}
