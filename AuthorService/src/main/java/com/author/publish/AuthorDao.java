package com.author.publish;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.author.entitys.AuthorDetails;

public interface AuthorDao extends JpaRepository<AuthorDetails, Long> {

	@Query(value = "SELECT * FROM author_database.author_account_details where email_id=:emailId", nativeQuery = true)
	List<AuthorDetails> findByEmailId(String emailId);

}
