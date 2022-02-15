package com.lagou.warehouseservice.dubbo;

import com.lagou.warehouseservice.dto.Stock;

public interface WarehouseService {

    Stock getStock(Long skuId);

    String getMsg();
}
