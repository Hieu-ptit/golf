package com.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
@Entity(name = "property_setting")
@Table(indexes = {
//    @Index(name="idx_driver_phone", columnList = "username"),
//    @Index(name="idx_driver_status", columnList = "status")
})
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class PropertySetting extends BaseEntity{

    @Id
    @SequenceGenerator(name = "property_setting_id_seq", sequenceName = "property_setting_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "property_setting_id_seq")
    Integer id;

    @Column(columnDefinition = "varchar(50)", nullable = false)
    String name;

    @Column(columnDefinition = "varchar(50)", nullable = false)
    String description;

    @Column(columnDefinition = "float default 0.0", nullable = false)
    Float holeCup;

    @Column(columnDefinition = "float default 0.0", nullable = false)
    Float height;

    @Column(columnDefinition = "float default 0.0", nullable = false)
    Float distance;

    @Column(columnDefinition = "int default 0", nullable = false)
    Integer greenSpeed;
}
