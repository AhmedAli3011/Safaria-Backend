package com.safaria.backend.repository;

import com.safaria.backend.entity.TourProvider;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TourProviderRepository extends JpaRepository<TourProvider, Long> {

    Optional<List<TourProvider>> findByEnabled(Boolean enabled);

    Optional<TourProvider> findById(Long id);

    Optional<TourProvider> findByUserId(Long userId);

}
