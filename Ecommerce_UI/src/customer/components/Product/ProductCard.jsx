import React from "react";
import './productCard.css'
import { useNavigate } from "react-router-dom";

function ProductCard({product}) {
  const navigate = useNavigate()
  return (
    <div onClick={()=>navigate(`/product/${product.id}`)} className="productCard w-[15rem] mx-2 my-3 transition-all cursor-pointer">
      <div>
        <img
          className="w-full h-full object-cover object-left-top"
          src={product.imageUrl}
          alt=""
        />
      </div>
      <div className="textPart bg-white p-3">
        <div>
          <p className="font-bold">{product.brand}</p>
          <p>{product.title}</p>
        </div>
        <div className="flex  items-center space-x-2">
          <p className="font-semibold">Rs.{product.discountedPrice}</p>
          <p className="line-through opacity-50">Rs.{product.price}</p>
          <p className="font-semibold text-green-600">{product.discountPersent}% off</p>
        </div>
      </div>
    </div>
  );
}

export default ProductCard;
