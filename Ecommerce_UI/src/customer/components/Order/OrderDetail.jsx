import React from "react";
import AddressCard from "../AddressCard/AddressCard";
import OrderTracker from "./OrderTracker";
import { Box, Grid } from "@mui/material";
import { deepPurple } from "@mui/material/colors";
import StarBorderIcon from "@mui/icons-material/StarBorder";
function OrderDetail() {
  return (
    <div className="lg:px-20 px-5">
      <div>
        <h1 className="font-bold text-lg py-7">Delivery Address</h1>
        <AddressCard />
      </div>
      <div className="py-20">
        <OrderTracker />
      </div>
      <Grid container className="space-y-5">
        {[1, 1, 1, 1].map((item,index) => (
          <Grid
            key={index}
            item
            container
            className="shadow-xl rounded-md p-5 border"
            sx={{ alignItems: "center", justifyContent: "space-between" }}
          >
            <Grid item xs={6}>
              <div className="flex items-center space-x-3">
                <img
                  className="w-[8rem] h-[8rem] object-cover object-top"
                  src="https://rukminim1.flixcart.com/image/612/612/l5h2xe80/kurta/x/6/n/xl-kast-tile-green-majestic-man-original-imagg4z33hu4kzpv.jpeg?q=70"
                  alt=""
                />
                <div className="space-y-3 ml-5">
                  <p className="font-bold">
                    Men Printed Pure Cotton Straight Kurta
                  </p>
                  <p className="opacity-50 text-xs font-semibold space-x-5">
                    <span>Color:Blue</span>
                    <span>Size:L</span>
                  </p>
                  <p className="opacity-50 text-xs font-semibold">
                    Seller:Brand
                  </p>
                  <p className="text-sm font-bold">Rs.500</p>
                </div>
              </div>
            </Grid>
            <Grid item>
              <Box sx={{ color: deepPurple[500] }}>
                <StarBorderIcon
                  sx={{ fontSize: "2rem" }}
                  className="px-2"
                ></StarBorderIcon>
                <span>Rate and Review Product</span>
              </Box>
            </Grid>
          </Grid>
        ))}
      </Grid>
    </div>
  );
}

export default OrderDetail;
