package com.book.books;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.book.constants.ResponseConstants;
import com.book.entitys.BaseResponse;
import com.book.entitys.BookDetails;
import com.book.exceptionhandler.BooksExceptionHandler;
import com.google.gson.Gson;

@Service
@Component
public class BookService {

	@Autowired
	private BookDao dao;

	@Autowired
	private BookFegin fegin;

	public BaseResponse publishBook(BookDetails bookDetails) throws BooksExceptionHandler {
		BookDetails details = null;
		try {
			details = dao.save(bookDetails);
			System.out.println(new Gson().toJson(details));
		} catch (Exception e) {
			throw new BooksExceptionHandler("Exception occured while inserting data into db");
		}
		return details != null ? new BaseResponse(ResponseConstants.SUCCESS, ResponseConstants.SUCCESSMESSAGE)
				: new BaseResponse(ResponseConstants.FAIL, ResponseConstants.FAILMESSAGE);
	}

	public List<BookDetails> getAllBooksForAuthor(int authorProfileId) throws BooksExceptionHandler {
		try {
			return dao.findByAuthorId(authorProfileId);
		} catch (Exception e) {
			throw new BooksExceptionHandler("Exception occured while fetching the data with email", e);
		}
	}

	public BaseResponse editOrBlockBook(BookDetails bookDetails) throws BooksExceptionHandler {
		BookDetails response = null;
		try {
			java.util.Optional<BookDetails> details = dao.findById(bookDetails.getBookId());
			BookDetails dbDetails = details.get();
			dbDetails.setActive(bookDetails.getActive());
			dbDetails.setContent(bookDetails.getContent());
			dbDetails.setCategory(bookDetails.getCategory());

			response = dao.save(dbDetails);
			fegin.blockBookForUser(dbDetails.getBookId());
		} catch (Exception e) {
			throw new BooksExceptionHandler("Exception occured while fetching the data with email", e);
		}
		return response != null ? new BaseResponse(ResponseConstants.SUCCESS, ResponseConstants.SUCCESSMESSAGE)
				: new BaseResponse(ResponseConstants.FAIL, ResponseConstants.FAILMESSAGE);
	}

	public List<BookDetails> getBooksBySearch(String title, String author, String publisher, String releaseDate)
			throws BooksExceptionHandler {
		try {
			return dao.getBooksBySearch(title, author, publisher, releaseDate);
		} catch (Exception e) {
			throw new BooksExceptionHandler("Exception occured while fetching the data with search details", e);
		}
	}

	public List<BookDetails> getPurchasedBooksForUser(List<Integer> bookIds) throws BooksExceptionHandler {
		List<BookDetails> details = new ArrayList<BookDetails>();
		try {
			for (int i : bookIds) {
				details.add(dao.findById(i).get());
			}
		} catch (Exception e) {
			throw new BooksExceptionHandler("Exception occured while fetching the data with bookId", e);
		}
		return details;
	}
}
