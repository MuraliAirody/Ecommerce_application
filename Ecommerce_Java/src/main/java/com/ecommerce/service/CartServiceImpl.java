package com.ecommerce.service;

import com.ecommerce.dao.CartDao;
import com.ecommerce.exception.ProductException;
import com.ecommerce.models.Cart;
import com.ecommerce.models.CartItem;
import com.ecommerce.models.Product;
import com.ecommerce.models.User;
import com.ecommerce.request.AddItemRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CartServiceImpl implements CartService {
    @Autowired
    CartDao cartDao;
    @Autowired
    CartItemService cartItemService;
    @Autowired
    ProductService productService;
    @Override
    public Cart createCart(User user) {
        Cart cart = new Cart();
        cart.setUser(user);
        Cart createdCart=cartDao.save(cart);
        return createdCart;
    }

    @Override
    public String addCartItem(Long userId, AddItemRequest req) throws ProductException {
        Cart cart=cartDao.findByUserId(userId);
        Product product=productService.findProductById(req.getProductId());

        CartItem isPresent=cartItemService.isCartItemExist(cart, product, req.getSize(),userId);

        if(isPresent == null) {
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setCart(cart);
            cartItem.setQuantity(req.getQuantity());
            cartItem.setUserId(userId);


            int price=req.getQuantity()*product.getDiscountedPrice();
            cartItem.setPrice(price);
            cartItem.setSize(req.getSize());

            CartItem createdCartItem=cartItemService.createCartItem(cartItem);
            log.info("created cart item {}",createdCartItem);
            cart.getCartItems().add(createdCartItem);
        }


        return "Item Add To Cart";
    }

    @Override
    public Cart findUserCart(Long userId) {
        Cart cart =	cartDao.findByUserId(userId);
        log.info("cart {}",cart);
        int totalPrice=0;
        int totalDiscountedPrice=0;
        int totalItem=0;
        for(CartItem cartsItem : cart.getCartItems()) {
            totalPrice+=cartsItem.getPrice();
            totalDiscountedPrice+=cartsItem.getDiscountedPrice();
            totalItem+=cartsItem.getQuantity();
        }
        log.info("cart items {}",cart.getCartItems());
        cart.setTotalPrice(totalPrice);
        cart.setTotalItem(cart.getCartItems().size());
        cart.setTotalDiscountedPrice(totalDiscountedPrice);
        cart.setDiscount(totalPrice-totalDiscountedPrice);
        cart.setTotalItem(totalItem);

        return cartDao.save(cart);
    }
}
