package edu.bbte.replate.core.repository;

import edu.bbte.replate.core.model.City;
import edu.bbte.replate.core.model.County;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
    City findCityByNameAndCounty(String name, County county);
}
