package com.book.books;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.book.entitys.BookDetails;

@Component
public interface BookDao extends JpaRepository<BookDetails, Integer> {

//	@Modifying
	@Query(value = "update  books_base_data.book_base_data set  active=1 where book_id =:bookId", nativeQuery = true)
	void bolckBoodById(int bookId);

	@Query(value = "SELECT * FROM books_base_data.book_base_data where email_id=:emailId", nativeQuery = true)
	List<BookDetails> getBooksByEmailId(String emailId);

	@Query(value = "SELECT * FROM books_base_data.book_base_data where active=0 && title like ':title%' || author_name like ':author%' || publisher like ':publisher%' || date=:releaseDate", nativeQuery = true)
	List<BookDetails> getBooksBySearch(String title, String author, String publisher, String releaseDate);

	@Query(value = "SELECT * FROM books_base_data.book_base_data where author_profileid=:authorProfileId", nativeQuery = true)
	List<BookDetails> findByAuthorId(int authorProfileId);

}
