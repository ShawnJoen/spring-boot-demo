package com.idea.example.domain.dto;

import com.idea.example.enums.ActiveEnum;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Table(name="t_user")
@Entity
public class User implements Serializable {//Spring Security登录用的 User对象必须继承 Serializable

    @Id
    @GeneratedValue
    private Long id;

    private String email;

    private String pw;

    @Enumerated(EnumType.STRING)
    private ActiveEnum active;

    private String role;

    private LocalDateTime regDate;

}
