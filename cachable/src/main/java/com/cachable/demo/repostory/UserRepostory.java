package com.cachable.demo.repostory;

import com.cachable.demo.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepostory extends JpaRepository<Customer,Integer> {
}
