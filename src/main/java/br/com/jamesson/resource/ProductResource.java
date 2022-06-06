package br.com.jamesson.resource;

import br.com.jamesson.*;
import br.com.jamesson.Void;
import br.com.jamesson.dto.ProductRequestDto;
import br.com.jamesson.dto.ProductResponseDto;
import br.com.jamesson.exception.BusinessException;
import br.com.jamesson.service.ProductService;
import br.com.jamesson.service.ValidationService;
import com.google.protobuf.DoubleValue;
import com.google.protobuf.Int64Value;
import com.google.protobuf.StringValue;
import io.grpc.stub.StreamObserver;
import io.micronaut.grpc.annotation.GrpcService;

import java.util.List;
import java.util.stream.Collectors;

@GrpcService
public class ProductResource extends ProductsServiceGrpc.ProductsServiceImplBase {
    private final ProductService productService;
    private final ValidationService validationService;

    public ProductResource(ProductService productService, ValidationService validationService) {
        this.productService = productService;
        this.validationService = validationService;
    }

    @Override
    public void create(ProductRequest request, StreamObserver<ProductResponse> responseObserver) {
        try{
            this.validationService.validate(request);

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
        }catch (BusinessException e){
            responseObserver.onError(e.getStatus()
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
    }

    @Override
    public void list(Void request, StreamObserver<ProductListResponse> responseObserver) {
        List<ProductResponseDto> products = productService.getAll();

        List<ProductResponse> listReturn = products.stream().map(e -> {
            return ProductResponse.newBuilder().setId(Int64Value.of(e.id()))
                    .setName(StringValue.of(e.name()))
                    .setPrice(DoubleValue.of(e.price())).build();
        }).collect(Collectors.toList());


        responseObserver.onNext(ProductListResponse.newBuilder().addAllProducts(listReturn).build());

        responseObserver.onCompleted();
    }
}
