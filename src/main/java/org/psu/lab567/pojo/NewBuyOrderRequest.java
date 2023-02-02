package org.psu.lab567.pojo;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class NewBuyOrderRequest {
    @NotEmpty(message = "Пустое наименование продукта")
    private final String product;
    @NotEmpty(message = "Пустой адрес")
    private final String address;

    private final String comment;
}
