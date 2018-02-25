package com.daos;

import com.entities.Book;
import com.entities.EmbededId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, EmbededId> {

    Book getBookByName(String name);

}
