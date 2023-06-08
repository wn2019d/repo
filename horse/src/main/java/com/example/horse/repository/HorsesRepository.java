package com.example.horse.repository;

import com.example.horse.model.Horse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface HorsesRepository extends CrudRepository<Horse, Long> {
    @Query("from Horse as h where h.advertisedBy.id = :repId")
    List<Horse> findHorsesBySalesrepId(@Param("repId") long repId);


    @Query("from Horse as h where h.advertisedBy.id = :repId and h.height >= :height  and h.age >= :age")
    List<Horse> findHorsesByHeightAgeSalesrepId(@Param("repId") long repId, @Param("height") int height, @Param("age") int age);
}
