package com.plazoleta.usuarios.infrastructure.out.restconsumer.feign;

import com.plazoleta.usuarios.infrastructure.configuration.FeignConfig;
import com.plazoleta.usuarios.infrastructure.out.restconsumer.dto.EmpleadoRestauranteDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ms-plazoleta", url = "${empleado.restaurante.url}", configuration = FeignConfig.class)
public interface EmpleadoRestauranteFeignClient {

    @PostMapping("/empleado/restaurante/")
    void guardarEmpleadoRestaurante(@RequestBody EmpleadoRestauranteDto empleadoRestauranteDto);
}
