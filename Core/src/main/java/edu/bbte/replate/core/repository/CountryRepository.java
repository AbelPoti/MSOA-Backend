package edu.bbte.replate.core.repository;

import edu.bbte.replate.core.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
    Country findCountryByName(String name);
}
