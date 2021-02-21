package com.springframework.spring5webapp.repositories;

import com.springframework.spring5webapp.domain.Author;
import org.springframework.data.repository.CrudRepository;

// at runtime spring data jpa will provide implementation for us
public interface AuthorRepository extends CrudRepository<Author, Long> {
}
