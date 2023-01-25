package com.products.products;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NonNull
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductDto extends AddProductDto {
    public String status;
}
