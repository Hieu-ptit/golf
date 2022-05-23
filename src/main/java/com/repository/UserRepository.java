package com.repository;

import com.domain.Account;
import com.model.bo.StatusCommon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Account, String>, InsertUpdateRepository<Account> {
    Account findOneByActivationKey(String activationKey);

    Optional<Account> findOneByResetKey(String resetKey);

    Optional<Account> findByEmailAndStatusNot(String email, StatusCommon statusCommon);

}
