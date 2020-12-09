package com.codecool.zlapka.localizationcomponent.repositories;

import com.codecool.zlapka.localizationcomponent.models.EventHeader;
import org.springframework.data.repository.CrudRepository;

public interface EventHeaderRepository extends CrudRepository<EventHeader, String> {

    boolean existsByIdAndLocalizationId(String id, String localizationId);

}
