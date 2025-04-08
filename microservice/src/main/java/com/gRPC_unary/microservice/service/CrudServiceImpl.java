package com.gRPC_unary.microservice.service;

import com.gRPC_unary.microservice.model.Entity;
import com.gRPC_unary.microservice.repository.EntityRepository;
import com.grpc_unary.microservice.proto.CrudServiceGrpc;
import com.grpc_unary.microservice.proto.DeleteResponse;
import com.grpc_unary.microservice.proto.EntityIdRequest;
import com.grpc_unary.microservice.proto.EntityRequest;
import com.grpc_unary.microservice.proto.EntityResponse;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class CrudServiceImpl extends CrudServiceGrpc.CrudServiceImplBase {

    private final EntityRepository repository;

    @Override
    public void create(EntityRequest request, StreamObserver<EntityResponse> responseObserver) {
        Entity entity = new Entity(request.getId(), request.getName(), request.getDescription());
        repository.save(entity);
        responseObserver.onNext(toProto(entity));
        responseObserver.onCompleted();
    }

    @Override
    public void read(EntityIdRequest request, StreamObserver<EntityResponse> responseObserver) {
        Entity entity = repository.findById(request.getId()).orElse(null);
        if (entity != null) {
            responseObserver.onNext(toProto(entity));
        }
        responseObserver.onCompleted();
    }

    @Override
    public void update(EntityRequest request, StreamObserver<EntityResponse> responseObserver) {
        Entity entity = new Entity(request.getId(), request.getName(), request.getDescription());
        repository.save(entity);
        responseObserver.onNext(toProto(entity));
        responseObserver.onCompleted();
    }

    @Override
    public void delete(EntityIdRequest request, StreamObserver<DeleteResponse> responseObserver) {
        repository.deleteById(request.getId());
        responseObserver.onNext(DeleteResponse.newBuilder().setSuccess(true).build());
        responseObserver.onCompleted();
    }

    private EntityResponse toProto(Entity entity) {
        return EntityResponse.newBuilder()
                .setId(entity.getId())
                .setName(entity.getName())
                .setDescription(entity.getDescription())
                .build();
    }
}