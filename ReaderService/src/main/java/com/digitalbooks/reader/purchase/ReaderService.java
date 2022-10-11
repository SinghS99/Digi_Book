package com.digitalbooks.reader.purchase;

import java.time.Clock;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.digitalbooks.reader.constants.HttpCalls;
import com.digitalbooks.reader.constants.ResponseConstants;
import com.digitalbooks.reader.entitys.BaseResponse;
import com.digitalbooks.reader.entitys.BookDetails;
import com.digitalbooks.reader.entitys.ReaderPurchasedBooks;
import com.digitalbooks.reader.exceptionhandler.BooksExceptionHandler;
import com.digitalbooks.reader.kafka.Consumer;

@Service
@Component
public class ReaderService {

	@Autowired
	private ReaderDao dao;

	@Autowired
	private EmailService emailService;

	@Autowired
	private BookFegin fegin;
	@Autowired
	private Consumer consumer;

	Logger logger = LoggerFactory.getLogger(ReaderService.class);

	public List<BookDetails> getAllBookDetails(String title, String author, String publisher, long fromDate,
			long toDate) throws BooksExceptionHandler {
		try {
			logger.info("calling the book service" + title, author, publisher, fromDate, toDate);
			return fegin.getBooksBySearch(title, author, publisher, fromDate, toDate);
		} catch (Exception e) {
			throw new BooksExceptionHandler("Exception occured while fetching with search", e);
		}
	}

	public BaseResponse purchaseBook(int bookId, String name, String emailId) throws BooksExceptionHandler {
		try {
			ReaderPurchasedBooks reader = new ReaderPurchasedBooks();
			reader.setTimeStamp(Clock.systemDefaultZone().millis());
			reader.setEmailId(emailId);
			reader.setReaderName(name);
			reader.setBookId(bookId);
			reader.setPaymentId("pid" + generatePId());
			ReaderPurchasedBooks details = dao.save(reader);
			return details != null ? new BaseResponse(ResponseConstants.SUCCESS, ResponseConstants.SUCCESSMESSAGE)
					: new BaseResponse(ResponseConstants.FAIL, ResponseConstants.FAILMESSAGE);

		} catch (Exception e) {
			throw new BooksExceptionHandler("Exception occured while purchasing book", e);
		}
	}

	private int generatePId() throws BooksExceptionHandler {
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
			if (purchasedBooks != null) {
				List<Integer> listOfBookIds = purchasedBooks.stream().map(ReaderPurchasedBooks::getBookId)
						.collect(Collectors.toList());
				/// getting books from books server
				if (listOfBookIds.size() > 0)
					return fegin.getPurchasedBooksForUser(listOfBookIds);
			}
		} catch (Exception e) {
			throw new BooksExceptionHandler("Exception occured while fetching purchased books", e);
		}
		return new ArrayList<BookDetails>();
	}

	public BaseResponse unSubscriABook(int bookId, String emailId) throws BooksExceptionHandler {
		try {
			List<ReaderPurchasedBooks> details = dao.getBookForReader(bookId, emailId,
					HttpCalls.getCurrentMilliSeconds() - HttpCalls.ONEDAYMILLISECONDS * 2);
			ReaderPurchasedBooks details1 = null;
			if (details != null && details.size() > 0) {
				details.get(0).setSubscriptionStatus(1);
				details1 = dao.save(details.get(0));
			}
			return details1 != null ? new BaseResponse(ResponseConstants.SUCCESS, ResponseConstants.SUCCESSMESSAGE)
					: new BaseResponse(ResponseConstants.FAIL, ResponseConstants.FAILTOUNSUBSCRIPTION);
		} catch (Exception e) {
			throw new BooksExceptionHandler("Exception occured while cancleing the book", e);
		}
	}

	public BookDetails getPaymentDetails(String paymentId) throws BooksExceptionHandler {
		BookDetails bookDetails = null;
		try {
			// fetching the bookid by pid and get book details for book service
			ReaderPurchasedBooks userDetails = dao.getBookDetailsByPid(paymentId);
			if (userDetails != null) {
				bookDetails = fegin.getPurchasedBooksForUser(Arrays.asList(userDetails.getBookId())).get(0);
				bookDetails.setPaymentId(userDetails.getPaymentId());
				bookDetails.setEmailId(userDetails.getEmailId());
			} else {
				bookDetails = new BookDetails();
				bookDetails.setStatusCode(ResponseConstants.FAIL);
				bookDetails.setStatusMessage(ResponseConstants.FAILMESSAGE);
			}
		} catch (Exception e) {
			throw new BooksExceptionHandler("Exception occured while fetching with pid and invoice search", e);
		}
		return bookDetails;
	}

	public BaseResponse blockBookForUser(int bookId) throws BooksExceptionHandler {
		try {
			BaseResponse response = new BaseResponse(ResponseConstants.FAIL, ResponseConstants.FAILMESSAGE);
			Optional<ReaderPurchasedBooks> details = dao.findById((long) bookId);
			if (details == null)
				return response;
			else {
				consumer.listenToTopic("hello");
			}
//			List<String> emails = details.stream().map(ReaderPurchasedBooks::getEmailId).collect(Collectors.toList());
//			emailService.send(emails, ResponseConstants.BLOCK_SUBJECT,
//					ResponseConstants.BLOCK_BOOK_CONTENT + "passbook name");
		} catch (Exception e) {
			throw new BooksExceptionHandler("Exception occured while sending book block emails", e);
		}
		return new BaseResponse(ResponseConstants.SUCCESS, ResponseConstants.SUCCESSMESSAGE);
	}

	public static void main(String[] args) {
		System.out.println(Clock.systemDefaultZone().millis());
	}

//@Cacheable(key = "#movieId", value = "MovieArea")
}
