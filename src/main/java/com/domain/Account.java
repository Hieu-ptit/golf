package com.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.model.bo.Gender;
import com.model.bo.StatusCommon;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.time.LocalDate;

@Entity(name = "accounts")
@Table
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Account extends BaseEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "CHAR(32)", unique = true)
    String id;

    @JsonIgnore
    @NotNull
    @Size(min = 60, max = 60)
    @Column(columnDefinition = "varchar(60)")
    String password;

    @Column(columnDefinition = "varchar(50)")
    String fullName;

    @Size(min = 2, max = 10)
    @Column(columnDefinition = "varchar(10)")
    String langKey;

    @Size(max = 20)
    @Column(columnDefinition = "varchar(50)")
    String resetKey;

    @Email
    @Size(min = 5, max = 254)
    @Column(length = 254, unique = true)
    String email;

    @Column(name = "reset_date")
    Instant resetDate = null;

    @Column(columnDefinition = "varchar(250)")
    String imageUrl;

    @Size(max = 20)
    @Column(columnDefinition = "varchar(250)")
    String activationKey;

    @Column(columnDefinition = "varchar(20) default 'ACTIVE'", nullable = false)
    @Enumerated(EnumType.STRING)
    StatusCommon status;

    @Column(columnDefinition = "int default 10")
    Integer age;

    @Column(columnDefinition = "varchar(20) default 'MALE'")
    @Enumerated(EnumType.STRING)
    Gender gender;

    @Column(columnDefinition = "timestamp")
    LocalDate birthDay;

    @Column(columnDefinition = "varchar(50)")
    String address;
}
