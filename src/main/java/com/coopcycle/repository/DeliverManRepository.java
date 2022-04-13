package com.coopcycle.repository;

import com.coopcycle.domain.DeliverMan;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the DeliverMan entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DeliverManRepository extends JpaRepository<DeliverMan, Long> {}
