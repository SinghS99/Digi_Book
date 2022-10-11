package com.reader.purchase;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.google.common.base.Optional;
import com.reader.constants.ResponseConstants;
import com.reader.entitys.BaseResponse;
import com.reader.entitys.BookDetails;
import com.reader.entitys.ReaderPurchasedBooks;
import com.reader.exceptionhandler.BooksExceptionHandler;

@Service
@Component
public class ReaderService {

	@Autowired
	private ReaderDao dao;

	@Autowired
	private EmailService emailService;

	@Autowired
	private BookFegin fegin;

	Logger logger = LoggerFactory.getLogger(ReaderService.class);

	public List<BookDetails> getAllBookDetails(String title, String author, String publisher, String date)
			throws BooksExceptionHandler {
		try {
			logger.info("calling the book service" + title, author, publisher, date);
			return fegin.getBooksBySearch(title, author, publisher, date);
		} catch (Exception e) {
			throw new BooksExceptionHandler("Exception occured while fetching with search", e);
		}
	}

	public BaseResponse purchaseBook(int bookId, String name, String emailId) throws BooksExceptionHandler {
		try {
			ReaderPurchasedBooks reader = new ReaderPurchasedBooks();
			reader.setTime(LocalTime.now());
			reader.setDate(LocalDate.now());
			reader.setEmailId(emailId);
			reader.setReaderName(name);
			reader.setBookId(bookId);
			reader.setPaymentId("pid" + generateInvoiceId());
			ReaderPurchasedBooks details = dao.save(reader);
			return details != null ? new BaseResponse(ResponseConstants.SUCCESS, ResponseConstants.SUCCESSMESSAGE)
					: new BaseResponse(ResponseConstants.FAIL, ResponseConstants.FAILMESSAGE);

		} catch (Exception e) {
			throw new BooksExceptionHandler("Exception occured while purchasing book", e);
		}
	}

	private int generateInvoiceId() throws BooksExceptionHandler {
		try {
			int min = 1000;
			int max = 99999;
			int num = (int) (Math.random() * (max - min + 1) + min);
			return num;
		} catch (Exception e) {
			throw new BooksExceptionHandler("Exception occured while generating the random number", e);
		}
	}

	public List<BookDetails> getPurchasedBooks(String emailId) throws BooksExceptionHandler {
		try {
			List<ReaderPurchasedBooks> purchasedBooks = dao.getPurchasedBooks(emailId);
			List<Integer> listOfBookIds = purchasedBooks.stream().map(ReaderPurchasedBooks::getBookId)
					.collect(Collectors.toList());
			/// getting books from books server
			return fegin.getPurchasedBooksForUser(listOfBookIds);
		} catch (Exception e) {
			throw new BooksExceptionHandler("Exception occured while fetching purchased books", e);
		}
	}

	public BaseResponse cancleABook(int bookId, String emailId) throws BooksExceptionHandler {
		try {
			Optional<ReaderPurchasedBooks> details = dao.deleteBookWithIn48Hours(bookId, emailId,
					LocalDate.now().plusDays(-10));
			return details.isPresent() ? new BaseResponse(ResponseConstants.SUCCESS, ResponseConstants.SUCCESSMESSAGE)
					: new BaseResponse(ResponseConstants.FAIL, ResponseConstants.FAILTODELETE);
		} catch (Exception e) {
			throw new BooksExceptionHandler("Exception occured while cancleing the book", e);
		}
	}

	public BookDetails getPaymentDetails(String paymentId) throws BooksExceptionHandler {
		try {
			// fetching the bookid by pid and get book details for book service
			ReaderPurchasedBooks userDetails = dao.getBookDetailsByPid(paymentId);
			BookDetails bookDetails = fegin.getPurchasedBooksForUser(Arrays.asList(userDetails.getBookId())).get(0);
			bookDetails.setPaymentId(userDetails.getPaymentId());
			bookDetails.setEmailId(userDetails.getEmailId());
			return bookDetails;
		} catch (Exception e) {
			throw new BooksExceptionHandler("Exception occured while fetching with pid and invoice search", e);
		}
	}

	public BaseResponse blockBookForUser(int bookId) throws BooksExceptionHandler {
		try {
			java.util.Optional<ReaderPurchasedBooks> details = dao.findById((long) bookId);
			List<String> emails = details.stream().map(ReaderPurchasedBooks::getEmailId).collect(Collectors.toList());
			emailService.send(emails, ResponseConstants.BLOCK_SUBJECT,
					ResponseConstants.BLOCK_BOOK_CONTENT + "passbook name");
		} catch (Exception e) {
			throw new BooksExceptionHandler("Exception occured while sending book block emails", e);
		}
		return new BaseResponse(ResponseConstants.SUCCESS, ResponseConstants.SUCCESSMESSAGE);
	}

//@Cacheable(key = "#movieId", value = "MovieArea")
}
