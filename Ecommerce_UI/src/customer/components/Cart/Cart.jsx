import React, { useEffect } from "react";
import CartItem from "./CartItem";
import { Button, Divider } from "@mui/material";
import { useNavigate } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { getCart } from "../../../redux/customer/cart/action";


function Cart() {
  const navigate = useNavigate()
  const dispatch = useDispatch();

  const jwt = localStorage.getItem("jwt");
  const cart=useSelector(store=>store.cart);
  console.log("cart ",cart)

  useEffect(() => {
    dispatch(getCart(jwt));
    console.log("fetching carts");
  }, [jwt]);


  function handleCheckout(){
    navigate("/checkout?step=2")
  }

  return (
    <div>
      <div className="lg:grid grid-cols-3 lg:px-16 relative">
        <div className="col-span-2">
          {cart.cartItems?.map((item,index)=><CartItem key={index} item={item}/>)}
        </div>
        <div className="px-5 sticky top-0 h-[100vh] mt-5 lg:mt-0">
          <div className="border p-4 mb-2">
            <p className="uppercase font-bold opacity-60 pb-4">Price details</p>
            <hr />
            <div className="space-y-3 font-semibold p-4">
              <div className="flex justify-between pt-3 text-black">
                <span>Price</span>
                <span>Rs.{cart?.cart?.totalPrice}</span>
              </div>
              <div className="flex justify-between pt-3">
                <span>Discount</span>
                <span className=" text-red-600">- Rs.{cart?.cart?.discounte}</span>
              </div>
              <div className="flex justify-between pt-3 ">
                <span>Delivery Charge</span>
                <span className="text-yellow-600">Free</span>
              </div>
              <div className="flex justify-between pt-3 font-bold">
                <span>Total Amount</span>
                <span className="text-green-600">Rs.{cart?.cart?.totalDiscountedPrice}</span>
              </div>
            </div>
            <Button
              className="w-full"
              sx={{ bgcolor: "#32CD32", color: "black" }}
              variant="contained"
              color="success"
              onClick={handleCheckout}
            >
              Checkout
            </Button>{" "}
          </div>
        </div>
      </div>
    </div>
  );
}

export default Cart;
