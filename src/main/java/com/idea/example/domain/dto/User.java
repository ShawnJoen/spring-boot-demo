package com.idea.example.domain.dto;

import com.idea.example.enums.ActiveEnum;
import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Table(name="t_user")
@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String email;

    private String pw;

    private String otpHash;

    @Enumerated(EnumType.STRING)
    private ActiveEnum active;

    private String role;

    private LocalDateTime regDate;

}
