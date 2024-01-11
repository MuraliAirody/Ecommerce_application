import React from "react";
import CartItem from "./CartItem";
import { Button, Divider } from "@mui/material";

function Cart() {
  return (
    <div>
      <div className="lg:grid grid-cols-3 lg:px-16 relative">
        <div className="col-span-2">
          {[1,1,1].map((item)=><CartItem />)}
        </div>
        <div className="px-5 sticky top-0 h-[100vh] mt-5 lg:mt-0">
          <div className="border p-4 mb-2">
            <p className="uppercase font-bold opacity-60 pb-4">Price details</p>
            <hr />
            <div className="space-y-3 font-semibold p-4">
              <div className="flex justify-between pt-3 text-black">
                <span>Price</span>
                <span>Rs.500</span>
              </div>
              <div className="flex justify-between pt-3">
                <span>Discount</span>
                <span className=" text-red-600">- Rs.100</span>
              </div>
              <div className="flex justify-between pt-3 ">
                <span>Delivery Charge</span>
                <span className="text-yellow-600">Free</span>
              </div>
              <div className="flex justify-between pt-3 font-bold">
                <span>Total Amount</span>
                <span className="text-green-600">Rs.400</span>
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
  );
}

export default Cart;
