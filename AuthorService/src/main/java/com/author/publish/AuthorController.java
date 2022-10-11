package com.author.publish;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.author.constants.ResponseConstants;
import com.author.entitys.AuthorDetails;
import com.author.entitys.BaseResponse;
import com.author.entitys.BookDetails;
import com.author.exceptionhandler.AuthorExceptionHandler;

@RestController
@RequestMapping("/author")
public class AuthorController {
	Logger logger = LoggerFactory.getLogger(AuthorController.class);
	@Autowired
	private AuthorService service;

	@PostMapping("/register")
	public BaseResponse registerAutor(@RequestBody AuthorDetails authorDetails) throws AuthorExceptionHandler {
		BaseResponse response = new BaseResponse(ResponseConstants.FAIL, ResponseConstants.FAILMESSAGE);
		try {
			response = service.registerAuthor(authorDetails);
		} catch (Exception e) {
			logger.info("failed to register", e);
		}
		return response;
	}

	@GetMapping("/login")
	public BaseResponse loginAuthor(@RequestParam String emailId, @RequestParam String password)
			throws AuthorExceptionHandler {
		BaseResponse response = new BaseResponse(ResponseConstants.FAIL, ResponseConstants.LOGINFAIL);
		try {
			response = service.loginAuthor(emailId, password);
		} catch (Exception e) {
			logger.info("failed to login", e);
		}
		return response;
	}

	@PostMapping("/publishbook")
	public BaseResponse publishBook(@RequestBody BookDetails bookDetails) throws AuthorExceptionHandler {
		BaseResponse response = new BaseResponse(ResponseConstants.FAIL, ResponseConstants.FAILMESSAGE);
		try {
			response = service.publishBook(bookDetails);
		} catch (Exception e) {
			logger.info("failed to publish the book", e);
		}
		return response;
	}

	@GetMapping("/getallbooksforauthor")
	public List<BookDetails> getAllBooksForAuthor(@RequestParam int authorProfileId) throws AuthorExceptionHandler {
		try {
			return service.getAllBooksForAuthor(authorProfileId);
		} catch (Exception e) {
			logger.info("failed to get  books", e);
		}
		return null;
	}

	@PutMapping("/editorblockbook")
	public BaseResponse editOrBlockBook(@RequestBody BookDetails bookDetails) throws AuthorExceptionHandler {
		BaseResponse response = new BaseResponse(ResponseConstants.FAIL, ResponseConstants.FAILMESSAGE);
		try {
			response = service.editOrBlockBook(bookDetails);
		} catch (Exception e) {
			logger.info("failed to block a book", e);
		}
		return response;
	}

//	@RequestMapping("/test")
//	public String test() {
//		return "Working";
//	}

}
