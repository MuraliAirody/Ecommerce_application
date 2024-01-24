import React, { useEffect } from "react";
import AddressCard from "../AddressCard/AddressCard";
import { Button } from "@mui/material";
import CartItem from "../Cart/CartItem";
import { useLocation } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { getOrderById } from "../../../redux/customer/order/action";

function OrderSummary() {
  const location = useLocation();
  const searchParams = new URLSearchParams(location.search);
  const orderId = searchParams.get("order_id");
  const dispatch = useDispatch();
  const jwt = localStorage.getItem("jwt");
  const order = useSelector((state) => state.order);

  console.log("orderId ", order.order);

  useEffect(() => {
    dispatch(getOrderById(orderId));
  }, [orderId]);

  // const handleCreatePayment = () => {
  //   const data = { orderId: order.order?.id, jwt };
  //   dispatch(createPayment(data));
  // };

  return (
    <div>
      <div className="p-5 shadow-lg rounded-md border">
        <AddressCard address={order?.order?.shippingAddress}/>
      </div>
      <div>
        <div className="lg:grid grid-cols-3  relative">
          <div className="col-span-2">
            {order?.order?.orderItems.map((item) => (
              <div key={item.id}>
                <CartItem item={item}/>
              </div>
            ))}
          </div>
          <div className="px-5 sticky top-0 h-[100vh] mt-5 lg:mt-0">
            <div className="border p-4 mb-2">
              <p className="uppercase font-bold opacity-60 pb-4">
                Price details
              </p>
              <hr />
              <div className="space-y-3 font-semibold p-4">
                <div className="flex justify-between pt-3 text-black">
                  <span>Price</span>
                  <span>Rs.{order?.order?.totalPrice}</span>
                </div>
                <div className="flex justify-between pt-3">
                  <span>Discount</span>
                  <span className=" text-red-600">- Rs.{order?.order?.discounte}</span>
                </div>
                <div className="flex justify-between pt-3 ">
                  <span>Delivery Charge</span>
                  <span className="text-yellow-600">Free</span>
                </div>
                <div className="flex justify-between pt-3 font-bold">
                  <span>Total Amount</span>
                  <span className="text-green-600">Rs.{order?.order?.totalDiscountedPrice}</span>
                </div>
              </div>
              <Button
                className="w-full"
                sx={{ bgcolor: "#32CD32", color: "black" }}
                variant="contained"
                color="success"
              >
                Checkout
              </Button>{" "}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default OrderSummary;
