package com.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity(name = "role_account")
@Table(indexes = {
//    @Index(name="idx_driver_phone", columnList = "username"),
//    @Index(name="idx_driver_status", columnList = "status")
})
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class RoleAccount extends BaseEntity {

    @Id
    @SequenceGenerator(name = "role_account_id_seq", sequenceName = "role_account_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_account_id_seq")
    Integer id;

    @Column(columnDefinition = "varchar(50)", nullable = false)
    String accountId;

    @Column(columnDefinition = "varchar(50)", nullable = false)
    String roleName;
}
