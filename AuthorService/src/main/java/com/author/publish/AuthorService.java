package com.author.publish;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.author.constants.ResponseConstants;
import com.author.entitys.AuthorDetails;
import com.author.entitys.BaseResponse;
import com.author.entitys.BookDetails;
import com.author.exceptionhandler.AuthorExceptionHandler;
import com.author.jwt.api.util.JwtUtil;
import com.author.utils.PasswordEncDec;
import com.google.gson.Gson;

@Service
public class AuthorService {

	@Autowired
	private AuthorDao dao;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private BookFeign fegin;

	public BaseResponse registerAuthor(AuthorDetails authorDetails) throws AuthorExceptionHandler {
		authorDetails.setPassword(PasswordEncDec.encryptingPassword(authorDetails.getPassword()));
		AuthorDetails details = null;
		try {
			details = dao.save(authorDetails);
			System.out.println(new Gson().toJson(details));
		} catch (Exception e) {
			throw new AuthorExceptionHandler("Exception occured while inserting data into db");
		}
		return details != null ? new BaseResponse(ResponseConstants.SUCCESS, ResponseConstants.SUCCESSMESSAGE)
				: new BaseResponse(ResponseConstants.FAIL, ResponseConstants.FAILMESSAGE);
	}

	public BaseResponse loginAuthor(String emailId, String password) throws AuthorExceptionHandler {
		try {
			AuthorDetails author = dao.findByEmailId(emailId).get(0);
			System.out.println(new Gson().toJson(author));
			return PasswordEncDec.bCrypter(password, author.getPassword())
					? new BaseResponse(ResponseConstants.SUCCESS, ResponseConstants.LOGINSUCCESS,
							new AuthorDetails(author.getAuthorProfileId(), author.getAuthorName(), author.getEmailId(),
									jwtUtil.generateToken(author.getEmailId())))
					: new BaseResponse(ResponseConstants.FAIL, ResponseConstants.PASSWORDWRONG);
		} catch (Exception e) {
			throw new AuthorExceptionHandler("Exception occured while inserting data into db");
		}
	}

	public BaseResponse publishBook(BookDetails bookDetails) throws AuthorExceptionHandler {
		BaseResponse details = null;
		try {
			details = fegin.publishBook(bookDetails);
		} catch (Exception e) {
			throw new AuthorExceptionHandler("Exception occured while inserting data into db");
		}
		return details;
	}

	public BaseResponse editOrBlockBook(BookDetails bookDetails) throws AuthorExceptionHandler {
		try {
//			return this.restTemplate.getForObject(HttpCalls.BLOCKBOOK + bookId, BaseResponse.class);
			return fegin.editOrBlockBook(bookDetails);
		} catch (Exception e) {
			throw new AuthorExceptionHandler("Exception occured while blocking the book", e);
		}
	}

	public List<BookDetails> getAllBooksForAuthor(int authorProfileId) throws AuthorExceptionHandler {
		try {
			return fegin.getAllBooksForAuthor(authorProfileId);
		} catch (Exception e) {
			throw new AuthorExceptionHandler("Exception occured while fetching  the books for author", e);
		}
	}
}
