package sk.fri.uniza.db;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import sk.fri.uniza.api.WeatherRecord;

public class WeatherRecordDao extends AbstractDAO<WeatherRecord> {
    public WeatherRecordDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Long save(WeatherRecord record){
        return persist(record).getId();
    }
}
