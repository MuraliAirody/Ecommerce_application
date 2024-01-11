import React from "react";
import IconButton from "@mui/material/IconButton";
import RemoveCircleOutlineIcon from '@mui/icons-material/RemoveCircleOutline';
import AddCircleOutlineIcon from '@mui/icons-material/AddCircleOutline';
import { Button } from "@mui/material";

function CartItem() {
  return (
    <div className="p-5 shadow-lg border rounded-md">
      <div className="flex items-center">
        <div className="w-[8rem] h-[8rem] lg:w-[8rem] lg:h-[10rem]">
          <img
            src="https://rukminim1.flixcart.com/image/612/612/l5h2xe80/kurta/x/6/n/xl-kast-tile-green-majestic-man-original-imagg4z33hu4kzpv.jpeg?q=70"
            alt=""
            className="w-full h-full object-cover object-top"
          />
        </div>
        <div className="ml-5 space-y-1">
          <p className="font-semibold">
            Men Printed Pure Cotton Straight Kurta
          </p>
          <p className="opacity-70">Size:L, White</p>
          <p className="opacity-70 mt-2">Seller: Majestic Man</p>
          <div className="flex space-x-5 items-center mt-6">
            <p className="font-semibold">Rs.199</p>
            <p className="opacity-50 line-through">Rs.200</p>
            <p className="text-green-600 font-semibold">50% off</p>
          </div>
        </div>
      </div>
      <div className="lg:flex items-center lg:space-x-3 pt-4">
        <div className="flex items-center space-x-3">
          <IconButton aria-label="" sx={{color:'red'}}>
            <RemoveCircleOutlineIcon />
          </IconButton>
          <span className="py-3 px-3 border rounded-sm">3</span>
          <IconButton aria-label="" sx={{color:'green'}}>
            <AddCircleOutlineIcon />
          </IconButton>
        </div>
        <div>
          <Button sx={{color:'gray'}}>Remove</Button>
        </div>
      </div>
    </div>
  );
}

export default CartItem;
