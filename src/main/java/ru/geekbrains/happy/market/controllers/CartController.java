package ru.geekbrains.happy.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.happy.market.beans.Cart;
import ru.geekbrains.happy.market.dto.CartDto;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final Cart cart;

    @GetMapping
    public CartDto getCart() {
        return new CartDto(cart);
    }

    @GetMapping("/add/{id}")
    public void addToCart(@PathVariable Long id) {
        cart.addToCart(id);
    }

    @GetMapping("/clear")
    public void clearCart() {
        cart.clear();
    }


    @GetMapping("/increment/{productTitle}")
    public void incrementCartItem(@PathVariable String productTitle) {
        cart.incrementCartItem(productTitle);
    }

    @GetMapping("/decrement/{productTitle}")
    public void decrementCartItem(@PathVariable String productTitle) {
        cart.decrementCartItem(productTitle);
    }

}
