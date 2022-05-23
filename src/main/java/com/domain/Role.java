package com.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Entity(name = "role")
@Table(indexes = {
//    @Index(name="idx_driver_phone", columnList = "username"),
//    @Index(name="idx_driver_status", columnList = "status")
})
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Role extends BaseEntity {

    @NotNull
    @Size(max = 50)
    @Id
    @Column(length = 50)
    private String name;
}
