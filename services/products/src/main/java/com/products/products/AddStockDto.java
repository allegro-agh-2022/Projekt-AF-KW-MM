package com.products.products;

import lombok.Data;
import lombok.NonNull;

@Data
@NonNull
public class AddStockDto {
    private int amount;
}
