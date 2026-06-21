package edu.bbte.replate.core.repository;

import edu.bbte.replate.core.model.Country;
import edu.bbte.replate.core.model.County;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountyRepository extends JpaRepository<County, Long> {
    County findCountyByNameAndCountry(String name, Country country);
}
