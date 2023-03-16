package ime.gtfs.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import ime.gtfs.entities.Calendar;

public interface CalendarRepository extends JpaRepository<Calendar, Integer> {

}
