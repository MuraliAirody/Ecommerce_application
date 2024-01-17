package com.ecommerce.service;

import com.ecommerce.dao.CartDao;
import com.ecommerce.dao.CartItemDao;
import com.ecommerce.exception.CartItemException;
import com.ecommerce.exception.UserException;
import com.ecommerce.models.Cart;
import com.ecommerce.models.CartItem;
import com.ecommerce.models.Product;
import com.ecommerce.models.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class CartItemServiceImpl implements CartItemService{
    @Autowired
    CartItemDao cartItemDao;
    @Autowired
    UserService userService;
    @Autowired
    CartDao cartDao;

    @Override
    public CartItem createCartItem(CartItem cartItem) {
        cartItem.setQuantity(1);
        cartItem.setPrice(cartItem.getProduct().getPrice()*cartItem.getQuantity());
        cartItem.setDiscountedPrice(cartItem.getProduct().getDiscountedPrice()*cartItem.getQuantity());

        CartItem createdCartItem=cartItemDao.save(cartItem);

        return createdCartItem;
    }

    @Override
    public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException {
        CartItem item=findCartItemById(id);
        User user=userService.findUserById(item.getUserId());

        if(user.getId().equals(userId)) {

            item.setQuantity(cartItem.getQuantity());
            item.setPrice(item.getQuantity()*item.getProduct().getPrice());
            item.setDiscountedPrice(item.getQuantity()*item.getProduct().getDiscountedPrice());

            return cartItemDao.save(item);
        }
        else {
            throw new CartItemException("You can't update  another users cart_item");
        }
    }

    @Override
    public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId) {
        CartItem cartItem=cartItemDao.isCartItemExist(cart, product, size, userId);

        return cartItem;
    }

    @Override
    public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException {
        log.info("remove cart item "+"userId- "+userId+" cartItemId "+cartItemId);

        CartItem cartItem=findCartItemById(cartItemId);

        User user=userService.findUserById(cartItem.getUserId());
        User reqUser=userService.findUserById(userId);

        if(user.getId().equals(reqUser.getId())) {
            cartItemDao.deleteById(cartItem.getId());
        }
        else {
            throw new UserException("you can't remove anothor users item");
        }
    }

    @Override
    public CartItem findCartItemById(Long cartItemId) throws CartItemException {
        Optional<CartItem> opt=cartItemDao.findById(cartItemId);

        if(opt.isPresent()) {
            return opt.get();
        }
        throw new CartItemException("cartItem not found with id : "+cartItemId);
    }
}
