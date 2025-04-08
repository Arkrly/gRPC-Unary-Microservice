package com.gRPC_unary.microservice.repository;

import com.gRPC_unary.microservice.model.Entity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntityRepository extends JpaRepository<Entity, Long> {
}
