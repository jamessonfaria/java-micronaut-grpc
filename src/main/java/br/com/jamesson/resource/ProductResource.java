package br.com.jamesson.resource;

import br.com.jamesson.ProductRequest;
import br.com.jamesson.ProductResponse;
import br.com.jamesson.ProductsServiceGrpc;
import com.google.protobuf.Int64Value;
import io.grpc.stub.StreamObserver;
import io.micronaut.grpc.annotation.GrpcService;

@GrpcService
public class ProductResource extends ProductsServiceGrpc.ProductsServiceImplBase {

    @Override
    public void create(ProductRequest request, StreamObserver<ProductResponse> responseObserver) {
        responseObserver.onNext(ProductResponse.newBuilder()
                .setId(Int64Value.of(1l))
                .setName(request.getName())
                .setPrice(request.getPrice())
                .build());

        responseObserver.onCompleted();
    }
}
