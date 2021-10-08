package com.platzimarket.web.controller;

import com.platzimarket.domain.Purchase;
import com.platzimarket.domain.service.PurchaseService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("/all")
    @ApiOperation("Get all Purchases ")
    @ApiResponses(
            {
                    @ApiResponse(code = 200, message = "OK"),
                    @ApiResponse(code = 403, message = "Don't fount")
            }
    )
    public ResponseEntity<List<Purchase>> getAll() {
        return new ResponseEntity<>(purchaseService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/client/{idClient}")
    @ApiOperation("Get purchases By client ")
    @ApiResponses(
            {
                    @ApiResponse(code = 200, message = "OK"),
                    @ApiResponse(code = 404, message = "Don't Work")
            }
    )
    public ResponseEntity<List<Purchase>> getByClient(@ApiParam (value = "Id of client", required = true, example = "4546221") @PathVariable("idClient") String clientId) {
        return purchaseService.getByClient(clientId).map(
                purchases -> new ResponseEntity<>(purchases, HttpStatus.OK)
        ).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    @ApiOperation("Get purchases By client ")
    @ApiResponses(
            {
                    @ApiResponse(code = 201, message = "OK"),
                    @ApiResponse(code = 404, message = "Don't save")
            }
    )
    public ResponseEntity<Purchase> save(@RequestBody Purchase purchase) {
        return new ResponseEntity<>(purchaseService.save(purchase), HttpStatus.CREATED);
    }
}
