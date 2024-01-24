import React, { useEffect } from "react";
import IconButton from "@mui/material/IconButton";
import RemoveCircleOutlineIcon from '@mui/icons-material/RemoveCircleOutline';
import AddCircleOutlineIcon from '@mui/icons-material/AddCircleOutline';
import { Button } from "@mui/material";
import { useDispatch } from "react-redux";
import { getCart, removeCartItem, updateCartItem } from "../../../redux/customer/cart/action";

function CartItem({item}) {
  const dispatch = useDispatch()
  const jwt = localStorage.getItem("jwt");


  // console.log("item:  ",item);

  const handleRemoveItemFromCart = () => {
    const data = { cartItemId: item?.id, jwt };
    dispatch(removeCartItem(data));
  };
  const handleUpdateCartItem=(num)=>{
    const data={data:{quantity:item?.quantity+num}, cartItemId:item?.id, jwt}
    dispatch(updateCartItem(data))
  }
  
  

  return (
    <div className="p-5 shadow-lg border rounded-md">
      <div className="flex items-center">
        <div className="w-[8rem] h-[8rem] lg:w-[8rem] lg:h-[10rem]">
          <img
            src={item?.product?.imageUrl}
            alt=""
            className="w-full h-full object-cover object-top"
          />
        </div>
        <div className="ml-5 space-y-1">
          <p className="font-semibold">
          {item?.product?.title}
          </p>
          <p className="opacity-70">{item?.size}, {item?.product?.color}</p>
          <p className="opacity-70 mt-2">Seller: {item?.product?.brand}</p>
          <div className="flex space-x-5 items-center mt-6">
            <p className="font-semibold">Rs.{item?.discountedPrice}</p>
            <p className="opacity-50 line-through">Rs.{item?.price}</p>
            <p className="text-green-600 font-semibold">{item?.product?.discountPersent}% off</p>
          </div>
        </div>
      </div>
      <div className="lg:flex items-center lg:space-x-3 pt-4">
        <div className="flex items-center space-x-3">
          <IconButton aria-label="" sx={{color:'red'}} onClick={()=>handleUpdateCartItem(-1)} disabled={item?.quantity<=1}>
            <RemoveCircleOutlineIcon />
          </IconButton>
          <span className="py-3 px-3 border rounded-sm">{item?.quantity}</span>
          <IconButton aria-label="" sx={{color:'green'}} onClick={()=>handleUpdateCartItem(1)}>
            <AddCircleOutlineIcon />
          </IconButton>
        </div>
        <div>
          <Button onClick={handleRemoveItemFromCart} sx={{color:'gray'}}>Remove</Button>
        </div>
      </div>
    </div>
  );
}

export default CartItem;
