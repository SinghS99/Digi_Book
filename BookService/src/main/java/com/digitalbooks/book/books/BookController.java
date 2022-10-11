package com.digitalbooks.book.books;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.digitalbooks.book.constants.ResponseConstants;
import com.digitalbooks.book.entitys.BaseResponse;
import com.digitalbooks.book.entitys.BookDetails;
import com.digitalbooks.book.exceptionhandler.BooksExceptionHandler;

@RestController
@RequestMapping("/book")
public class BookController {
	Logger logger = LoggerFactory.getLogger(BookController.class);
	@Autowired
	private BookService service;

	@PostMapping("/publishbook")
	public BaseResponse publishBook(@RequestBody BookDetails bookDetails) {
		BaseResponse response = new BaseResponse(ResponseConstants.FAIL, ResponseConstants.FAILMESSAGE);
		try {
			response = service.publishBook(bookDetails);
		} catch (Exception e) {
			logger.info("failed to publish the book", e);
		}
		return response;
	}

	@GetMapping("/getallbooksforauthor")
	public List<BookDetails> getAllBooksForAuthor(@RequestParam int authorProfileId) {
		try {
			return service.getAllBooksForAuthor(authorProfileId);
		} catch (Exception e) {
			logger.info("failed to get  books", e);
		}
		return null;
	}

	@PostMapping("/editorblockbook")
	public BaseResponse editOrBlockBook(@RequestBody BookDetails bookDetails) {
		BaseResponse response = new BaseResponse(ResponseConstants.FAIL, ResponseConstants.FAILMESSAGE);
		try {
			response = service.editOrBlockBook(bookDetails);
		} catch (Exception e) {
			logger.info("failed to block a book", e);
		}
		return response;
	}

	@GetMapping("/searchbooks")
	public List<BookDetails> getBooksBySearch(@RequestParam(required = false) String title,
			@RequestParam(required = false) String author, @RequestParam(required = false) String publisher,
			@RequestParam(required = false) long fromDate, @RequestParam(required = false) long toDate)
			throws BooksExceptionHandler {
		try {
			return service.getBooksBySearch(title, author, publisher, fromDate, toDate);
		} catch (Exception e) {
			throw new BooksExceptionHandler("Exception occured while fetching the data with search details", e);
		}
	}

	@PostMapping("/getpurchasebook")
	public List<BookDetails> getPurchasedBooksForUser(@RequestParam List<Integer> bookIds)
			throws BooksExceptionHandler {
		try {
			return service.getPurchasedBooksForUser(bookIds);
		} catch (Exception e) {
			logger.info("Exception occured while purchasing book");
		}
		return new ArrayList<BookDetails>();
	}

//	@RequestMapping("/test")
//	public String test() {
////		Optional<BookDetails> list = service.getAllBookesById();
////		System.out.println(new Gson().toJson(list.get()));
//		return "Working";
//	}

}
