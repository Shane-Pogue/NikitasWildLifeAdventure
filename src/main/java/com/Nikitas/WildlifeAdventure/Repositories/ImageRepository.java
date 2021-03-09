package com.Nikitas.WildlifeAdventure.Repositories;

import com.Nikitas.WildlifeAdventure.Domain.Image;
import com.Nikitas.WildlifeAdventure.Domain.ImageType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ImageRepository extends CrudRepository<Image, Long> {

    @Query("select id from Image i where imageType = :type")
    List<Long> findByType(@Param("type") ImageType type);
}
