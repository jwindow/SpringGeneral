package com.jung.inheritance;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

//@NoRepositoryBean
public interface BillingDetailsRepo<T extends BillingDetails> extends JpaRepository<T, Long>{
	List<T> findByOwner(String owner);

}
