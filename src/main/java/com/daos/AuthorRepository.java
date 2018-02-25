package com.daos;

import com.entities.Author;
import com.entities.EmbededId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, EmbededId> {

    Author findByName(String name);

}
