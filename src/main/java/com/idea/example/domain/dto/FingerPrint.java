package com.idea.example.domain.dto;

import lombok.Data;
import javax.persistence.*;

@Data
@Table(name="t_finger_print")
@Entity
public class FingerPrint {

    @Id
    private Long userId;

    private String hashKey;
}