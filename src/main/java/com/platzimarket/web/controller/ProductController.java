package com.platzimarket.web.controller;

import com.platzimarket.domain.Product;
import com.platzimarket.domain.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    @ApiOperation("Get all supermarket products")
    @ApiResponse(code =200, message = "OK")
    public ResponseEntity<List<Product>> getAll(){
        return  new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/{productId}")
    @ApiOperation("Search a product with id")
    @ApiResponses(
            {
                    @ApiResponse(code = 200, message = "OK"),
                    @ApiResponse(code = 404, message = "Product not found")
            }
    )
    public ResponseEntity<Product> getProduct (@ApiParam(value =  "id  of the product", required = true, example = "7")  @PathVariable("productId") int productId){
        return productService.getProduct(productId).map( product -> new ResponseEntity<>(
                product, HttpStatus.OK)).orElse(
                        new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/category/{categoryId}")
    @ApiOperation("Search a products with id of Category")
    @ApiResponses(
            {
                    @ApiResponse(code = 200, message = "OK"),
                    @ApiResponse(code = 404, message = "category not found")
            }
    )
    public ResponseEntity<List<Product>> getByCategory(@ApiParam (value = "Id of Category ", required = true, example = "1") @PathVariable("categoryId") int categoryId){
        return productService.getByCategory(categoryId)
                .map(products -> new ResponseEntity<>(products, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PostMapping("/save")
    @ApiOperation("Save a product")
    @ApiResponses(
            {
                    @ApiResponse(code = 201, message = "Product Saved"),
                    @ApiResponse(code = 403, message = "Error to Insert")
            }
    )
    public ResponseEntity<Product> save (@ApiParam (value = "In body like Json on Example", required = true)@RequestBody Product product){
        return new ResponseEntity<>( productService.save(product), HttpStatus.CREATED);
    }
    @DeleteMapping ("/delete/{ProductId}")
    @ApiOperation("DELETE product")
    @ApiResponses(
            {
                    @ApiResponse(code = 201, message = "Product deleted"),
                    @ApiResponse(code = 404, message = "Product not found")
            }
    )
    public ResponseEntity  delete (@ApiParam(value = "id of product to delete", required = true, example = "7") @PathVariable ("ProductId") int productId){
        if (productService.delete(productId)){
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
