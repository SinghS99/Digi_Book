package com.digitalbooks.reader.purchase;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.digitalbooks.reader.entitys.BookDetails;

@FeignClient(name = "BOOK-SERVICE", url = "localhost:8090/book")
public interface BookFegin {
	@GetMapping("/searchbooks")
	public List<BookDetails> getBooksBySearch(@RequestParam(required = false) String title,
			@RequestParam(required = false) String author, @RequestParam(required = false) String publisher,
			@RequestParam(required = false) long fromDate, @RequestParam(required = false) long toDate);

	@PostMapping("/getpurchasebook")
	public List<BookDetails> getPurchasedBooksForUser(@RequestParam List<Integer> bookIds);
}
