package com.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;
@Entity(name = "account_product")
@Table(indexes = {
//    @Index(name="idx_driver_phone", columnList = "username"),
//    @Index(name="idx_driver_status", columnList = "status")
})
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class AccountProduct extends BaseEntity{

    @Id
    @SequenceGenerator(name = "account_product_id_seq", sequenceName = "account_product_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_product_id_seq")
    Integer id;

    @Column(columnDefinition = "int default 0", nullable = false)
    Integer productId;

    @Column(columnDefinition = "int default 0", nullable = false)
    Integer accountId;
}
