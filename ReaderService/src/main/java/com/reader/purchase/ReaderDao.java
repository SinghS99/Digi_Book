package com.reader.purchase;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.google.common.base.Optional;
import com.reader.entitys.ReaderPurchasedBooks;

public interface ReaderDao extends JpaRepository<ReaderPurchasedBooks, Long> {

	@Query(value = "SELECT * FROM user_database.user_purchased_books where email_id=:emailId", nativeQuery = true)
	List<ReaderPurchasedBooks> getPurchasedBooks(String emailId);

	@Query(value = "DELETE FROM user_database.user_purchased_books where book_id=:bookId && email_id=:emailId && date>=:localDate", nativeQuery = true)
	Optional<ReaderPurchasedBooks> deleteBookWithIn48Hours(int bookId, String emailId, LocalDate localDate);

	@Query(value = "SELECT * FROM user_database.user_purchased_books where payment_id=:paymentId", nativeQuery = true)
	ReaderPurchasedBooks getBookDetailsByPid(String paymentId);

}
