package com.springbootapps.recipe.repositories;

/* @author ravin @date 20-05-2020 @time 17:18 */

import com.springbootapps.recipe.domain.Notes;
import org.springframework.data.repository.CrudRepository;

public interface NotesRepository extends CrudRepository<Notes, Long> {
}
