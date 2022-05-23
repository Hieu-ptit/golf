package com.repository;

import com.domain.RoleAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<RoleAccount, Integer>, InsertUpdateRepository<RoleAccount> {
    List<RoleAccount> findByAccountId(String userId);
}
