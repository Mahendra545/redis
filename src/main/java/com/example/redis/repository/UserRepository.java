package com.example.redis.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.redis.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByFirstName(String firstName);

	List<User> findByFirstNameContainsOrderByFirstNameAsc(String firstName);

	List<User> findByFirstNameAndLastName(String firstName, String lastName);

	@Query("from User where firstName=:firstName and lastName=:lastName")
	List<User> getUsersByName(@Param("firstName") String firstName, @Param("lastName") String lastName);

	@Query(value = "select u from user u where u.first_name = :firstName and u.last_name = :lastName", nativeQuery = true)
	List<User> getUsersByNameSql(@Param("firstName") String firstName, @Param("lastName") String lastName);

	List<User> findByFirstNameOrLastName(String firstName, String lastName);

	List<User> findByFirstNameContainsOrLastNameContains(String firstName, String lastName);

	List<User> findByFirstNameContainsOrderByFirstNameAsc(String firstName, Pageable pageable);

	Optional<User> findByUserId(Long userId);

}
