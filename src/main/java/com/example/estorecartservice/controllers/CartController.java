package com.example.estorecartservice.controllers;

import com.example.estorecartservice.entities.Cart;
import com.example.estorecartservice.repositories.CartRepo;
import com.example.estorecartservice.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @Autowired
    CartRepo cartRepo;

    @GetMapping("/getcart")
    public ResponseEntity<List<Cart>> getCart(){
        try {
            return new ResponseEntity<>(cartService.getAllCart(), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getcart/{id}")
    public ResponseEntity<Optional<Cart>> getCartById(@PathVariable("id") Long id){
        try {
            return new ResponseEntity<>(cartService.getCartById(id), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/create")
    public Cart create_Cart(@RequestBody Cart cart){
        return cartRepo.save(cart);
//        try {
//            return new ResponseEntity<>(cartService.createCart(cart),HttpStatus.CREATED);
//        } catch (Exception e){
//            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
    }

    @PutMapping("/updateQuantity/{cartId}/product/{productId}")
    public ResponseEntity<Void> updateProductQuantity(
            @PathVariable Long cartId,
            @PathVariable Long productId,
            @RequestParam int newQuantity
    ) {
        try {
            boolean success = cartService.updateProductQuantity(cartId, productId, newQuantity);

            if (success) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCart (@PathVariable Long id){
            try {
                return new ResponseEntity<>(cartService.deleteCartById(id), HttpStatus.OK);
            } catch (Exception e){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
    }



}
