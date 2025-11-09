package com.safaria.backend.repository;

import com.safaria.backend.entity.TourProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface TourProviderRepository extends JpaRepository<TourProvider, Integer> {

   

    Optional<List<TourProvider>> findByEnabled(Boolean enabled);

    Optional<TourProvider> findById(Integer id);

    Optional<TourProvider> findByUserId(Long userId);

   
}
