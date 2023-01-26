package com.orders;

import com.order_item.AddOrderItemDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddOrderDto {
    public String address;
    public List<AddOrderItemDto> orderItems = new ArrayList<>();
    public long userId;
    public long storeId;
}
