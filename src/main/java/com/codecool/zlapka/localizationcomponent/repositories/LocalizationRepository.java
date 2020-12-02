package com.codecool.zlapka.localizationcomponent.repositories;

import com.codecool.zlapka.localizationcomponent.models.Localization;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalizationRepository extends CrudRepository<Localization, Long> {


}
