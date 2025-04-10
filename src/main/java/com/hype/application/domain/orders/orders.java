package com.hype.application.domain.orders;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class orders {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;


}
