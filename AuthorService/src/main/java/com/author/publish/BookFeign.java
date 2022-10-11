package com.author.publish;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.author.entitys.BaseResponse;
import com.author.entitys.BookDetails;

@FeignClient(name = "BOOK-SERVICE", url = "localhost:8090/book")
public interface BookFeign {

	@PostMapping("/publishbook")
	public BaseResponse publishBook(@RequestBody BookDetails bookDetails);

	@GetMapping("/getallbooksforauthor")
	public List<BookDetails> getAllBooksForAuthor(@RequestParam String emailId);

	@PostMapping("/editorblockbook")
	public BaseResponse editOrBlockBook(@RequestBody BookDetails bookDetails);

	@GetMapping("/getallbooksforauthor")
	public List<BookDetails> getAllBooksForAuthor(@RequestParam int authorProfileId);
}
