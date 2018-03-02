package com.idea.example.domain;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

/*lombok
* @Data 包含Setter,Getter 等等
*
* */
@Data
@Table(name="t_city")
public class City implements Serializable {

    @Id //注解一个主键, @GeneratedValue默认为GenerationType.AUTO
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY) //注释定义了标识字段生成方式
    private Long id;

    @Column(name="provinceId")
    private Long provinceId;

    @Column(name="cityName", length=30, nullable=false)
    private String cityName;

    @Column(name="description", length=100)
    private String description;
}
