package com.example.estorecartservice.services;

import com.example.estorecartservice.entities.Cart;
import com.example.estorecartservice.entities.Product;
import com.example.estorecartservice.repositories.CartRepo;
import com.example.estorecartservice.repositories.ProductRepo;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Data
public class CartService {
    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private ProductRepo productRepo;

    public Cart createCart(Cart cart){
        return cartRepo.save(cart);
    }

    public List<Cart> getAllCart(){
        return cartRepo.findAll();
    }

    public Optional<Cart> getCartById(Long id) {
            return cartRepo.findById(id);
    }

//
//
//    public Boolean updateProductQuantity(Long cartId, Long productId, int newQuantity) {
//        Optional<Cart> optionalCart = cartRepo.findById(cartId);
//
//        if (optionalCart.isPresent()) {
//            Cart cart = optionalCart.get();
//
//            // Find the product in the cart
//            Optional<Product> optionalProduct = cart.getProductList().stream()
//                    .filter(product -> product.getProductId().equals(productId))
//                    .findFirst();
//
//            if (optionalProduct.isPresent()) {
//                Product productToUpdate = optionalProduct.get();
//                productToUpdate.setOrderQuantity(newQuantity);
//
//                // Update the total price of the cart
//                 cart.setTotalPrice(calculateTotalPrice(cart.getProductList()));
//
//                // Save the updated cart
//                cartRepo.save(cart);
//                return true;
//            } else {
//                // Product not found in the cart
//                return false;
//            }
//        } else {
//            // Cart not found
//            return false;
//        }
//    }
//
    private BigDecimal calculateTotalPrice(List<Product> productList) {
        return productList.stream()
                .map(product -> product.getPricePerUnit().multiply(BigDecimal.valueOf(product.getOrderQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


  //  public void updateProductQty()


//    Optional<Cart> c = cartRepo.findById(id);
//        if (c.isPresent()){
//        Cart existingCart = c.get();
//        Product existingProduct = existingCart.
//    }
    public String deleteCartById(Long id){
        Optional<Cart> cart = cartRepo.findById(id);

        if (cart.isPresent()){
            cartRepo.deleteById(id);
            return "Delete Success";
        }
        else
            return "Cart not found";
    }

}
