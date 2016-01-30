package org.thehecklers;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by markheckler on 1/19/2016.
 */

@RepositoryRestResource
public interface ReadingRepository extends CrudRepository<Reading, Integer> {

}
