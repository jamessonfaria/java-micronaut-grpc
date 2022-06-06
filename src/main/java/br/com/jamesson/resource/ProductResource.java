package br.com.jamesson.resource;

import br.com.jamesson.ProductRequest;
import br.com.jamesson.ProductResponse;
import br.com.jamesson.ProductsServiceGrpc;
import br.com.jamesson.dto.ProductRequestDto;
import br.com.jamesson.service.ProductService;
import com.google.protobuf.DoubleValue;
import com.google.protobuf.Int64Value;
import com.google.protobuf.StringValue;
import io.grpc.stub.StreamObserver;
import io.micronaut.grpc.annotation.GrpcService;

@GrpcService
public class ProductResource extends ProductsServiceGrpc.ProductsServiceImplBase {
    private final ProductService productService;

    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void create(ProductRequest request, StreamObserver<ProductResponse> responseObserver) {
        var productCreated = productService.create(
                new ProductRequestDto(
                        request.getName().getValue(),
                        request.getPrice().getValue())
        );

        responseObserver.onNext(ProductResponse.newBuilder()
                .setId(Int64Value.of(productCreated.id()))
                .setName(StringValue.of(productCreated.name()))
                .setPrice(DoubleValue.of(productCreated.price()))
                .build());

        responseObserver.onCompleted();
    }
}
