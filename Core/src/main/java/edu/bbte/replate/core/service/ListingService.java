package edu.bbte.replate.core.service;

import edu.bbte.replate.core.dto.incoming.FilterCriteria;
import edu.bbte.replate.core.model.Listing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ListingService {
    Listing findById(Long id);

    Page<Listing> findAll(Pageable pageable);

    Page<Listing> findByFilters(FilterCriteria filters, Pageable pageable);

    Listing create(Listing listing);

    void update(Listing listing);

    void delete(Long id);

    boolean isOwner(Long listingId, Long userId);
}
