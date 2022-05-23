package com.domain;

import com.model.bo.StatusCommon;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "product")
@Table(indexes = {
//    @Index(name="idx_driver_phone", columnList = "username"),
//    @Index(name="idx_driver_status", columnList = "status")
})
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class product extends BaseEntity{

    @Id
    @SequenceGenerator(name = "product_id_seq", sequenceName = "product_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_seq")
    Integer id;

    @Column(columnDefinition = "int default 0", nullable = false)
    Integer categoryId;

    @Column(columnDefinition = "varchar(50)", nullable = false)
    String name;

    @Column(columnDefinition = "decimal(15,2) default '0'", nullable = false)
    BigDecimal price;

    @Column(columnDefinition = "varchar(20) default 'ACTIVE'", nullable = false)
    @Enumerated(EnumType.STRING)
    StatusCommon status;
}
