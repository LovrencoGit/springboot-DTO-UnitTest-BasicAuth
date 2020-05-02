package com.certimetergroup.formazione.controller;

import com.certimetergroup.formazione.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;

@RestController
@RequestMapping("/time")
public class TimeController {

    final Logger logger = LoggerFactory.getLogger(this.getClass());


    @GetMapping("/now")
    public ResponseEntity<Response> now(){
        logger.info("---------- GET /time/now ----------");
        OffsetDateTime now = OffsetDateTime.now();
        return ResponseEntity.ok().body(
                new Response( 0, now.toString() )
        );
    }
}
