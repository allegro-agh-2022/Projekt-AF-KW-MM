package com.orders;

import lombok.Data;

import java.util.Date;

@Data
public class OrderDto {
    public Date date;
    public String status;
    public String address;
}
