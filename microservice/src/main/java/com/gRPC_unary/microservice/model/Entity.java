package com.gRPC_unary.microservice.model;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@jakarta.persistence.Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Entity {
    @Id
    private Long id;
    private String name;
    private String description;
}
