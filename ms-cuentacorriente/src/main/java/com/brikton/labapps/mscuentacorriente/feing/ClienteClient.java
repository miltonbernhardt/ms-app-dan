package com.brikton.labapps.mscuentacorriente.feing;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "ms-usuario", url = "localhost:9000")
public interface ClienteClient {
}
