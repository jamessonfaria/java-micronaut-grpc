package br.com.jamesson.service.impl;

import br.com.jamesson.ProductRequest;
import br.com.jamesson.exception.InvalidArgumentException;
import br.com.jamesson.service.ValidationService;
import com.google.protobuf.DoubleValue;
import jakarta.inject.Singleton;

@Singleton
public class ValidationServiceImpl implements ValidationService {

    private static final String PRICE_IS_REQUIRED = "Price is required !";
    private static final String NAME_IS_REQUIRED = "Name is required !";

    @Override
    public void validate(ProductRequest request) {
        if(request != null && request.getPrice().equals(DoubleValue.getDefaultInstance()))
            throw new InvalidArgumentException(PRICE_IS_REQUIRED);

        if(request != null && Double.isNaN(request.getPrice().getValue()))
            throw new InvalidArgumentException(PRICE_IS_REQUIRED);

        if(!request.hasName() || request.getName().getValue().isBlank())
            throw new InvalidArgumentException(NAME_IS_REQUIRED);

    }
}
