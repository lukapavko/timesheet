package com.gateway.api_gateway.rest_controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GatewayController {
    @RequestMapping("/defaultFallbackUrl")
    public String defaultMessage(){
        return "Please wait for server to respond on your request";
    }
}
