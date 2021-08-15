package com.brikton.labapps.mscuentacorriente.feing;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "ms-pedido", url = "localhost:9003")
public interface PedidoClient {

}
