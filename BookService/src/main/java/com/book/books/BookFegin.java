package com.book.books;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.book.entitys.BaseResponse;

@FeignClient(name = "READER-SERVICE", url = "localhost:8092/reader")
public interface BookFegin {
	@PutMapping("/blockbookforuser")
	public BaseResponse blockBookForUser(@RequestParam int bookId);
}
