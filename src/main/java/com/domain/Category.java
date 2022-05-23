package com.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity(name = "category")
@Table(indexes = {
//    @Index(name="idx_driver_phone", columnList = "username"),
//    @Index(name="idx_driver_status", columnList = "status")
})
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Category extends BaseEntity {

    @Id
    @SequenceGenerator(name = "category_id_seq", sequenceName = "category_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_id_seq")
    Integer id;

    @Column(columnDefinition = "varchar(50)", nullable = false)
    String name;

    @Column(columnDefinition = "varchar(50)", nullable = false)
    String description;
}
