package com.kiarsy.todo.hexagonal.infrastructure.presentation.controller;

import com.kiarsy.todo.hexagonal.infrastructure.presentation.pojo.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

@RestController()
@RequestMapping("/health")
public class HealthController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(path = "/live", method = RequestMethod.GET)
    public Response live(@RequestParam(value = "livenessProbe", required = false) boolean livenessProbe, @RequestParam(value = "readinessProbe", required = false)  boolean readinessProbe) {
        logger.info("HealthController called.livenessProbe:"+livenessProbe+",readinessProbe:"+readinessProbe);
        return new Response();
    }

}
