package com.digitalbooks.reader.purchase;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.digitalbooks.reader.entitys.ReaderPurchasedBooks;

public interface ReaderDao extends JpaRepository<ReaderPurchasedBooks, Long> {

	@Query(value = "SELECT * FROM user_database.user_purchased_books where email_id=:emailId", nativeQuery = true)
	List<ReaderPurchasedBooks> getPurchasedBooks(String emailId);

	@Query(value = "SELECT * FROM user_database.user_purchased_books where payment_id=:paymentId", nativeQuery = true)
	ReaderPurchasedBooks getBookDetailsByPid(String paymentId);

	@Query(value = "SELECT * FROM user_database.user_purchased_books where book_id=:bookId && email_id=:emailId && time_stamp>=:date", nativeQuery = true)
	List<ReaderPurchasedBooks> getBookForReader(int bookId, String emailId, long date);

}
