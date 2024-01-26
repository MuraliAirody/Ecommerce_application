import { Grid } from "@mui/material";
import React, { useEffect } from "react";
import OrderCard from "./OrderCard";
import { useDispatch, useSelector } from "react-redux";
import { getOrderHistory } from "../../../redux/customer/order/action";

const orderStatus = [
  {
    label: "On The way",
    value: "on_the_way",
  },
  {
    label: "Delivered",
    value: "delivered",
  },
  {
    label: "Cancelled",
    value: "cancelled",
  },
  {
    label: "Returned",
    value: "returned",
  },
];

function Order() {
  const dispatch = useDispatch();
  const jwt = localStorage.getItem("jwt");
  const order = useSelector((store) => store.order);

  console.log("orders : ",order?.orders)

  useEffect(() => {
    dispatch(getOrderHistory({ jwt }));
  }, [jwt]);

  return (
    <div className="lg:px-20 px-5 mt-2">
      <Grid container sx={{ justifyContent: "space-between" }}>
        <Grid item xs={2.5}>
          <div className="h-auto shadow-lg bg-white p-5 sticky top-5">
            <h1 className="font-bld text-lg">Filter</h1>
            <div className="space-y-4 mt-10">
              <h1 className="font-semibold">ORDER STATUS</h1>
              {orderStatus.map((option, index) => (
                <div key={index} className="flex items-center">
                  <input
                    defaultValue={option.value}
                    type="checkbox"
                    className="h-4 w-4 border-gray-300 text-indigo-700 focus:ring-indigo-500"
                  />
                  <label
                    className="ml-3 text-sm text-gray-600"
                    htmlFor={option.value}
                  >
                    {option.label}
                  </label>
                </div>
              ))}
            </div>
          </div>
        </Grid>
        <Grid item xs={9}>
          <div className="space-y-2">
            {order.orders?.length > 0 &&
              order.orders?.map((order) => {
                return order?.orderItems?.map((item, index) => (
                  <OrderCard item={item} order={order} key={index}/>
                ));
              })}
          </div>
        </Grid>
      </Grid>
    </div>
  );
}

export default Order;
