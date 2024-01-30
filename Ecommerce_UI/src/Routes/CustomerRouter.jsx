import React from "react";
import {
    Route,
  RouterProvider,
  Routes,
  createBrowserRouter,
  createRoutesFromElements,
} from "react-router-dom";
import Root from "../Root";
import Home from "../customer/pages/Home";
import Cart from "../customer/components/Cart/Cart";
import Product from "../customer/components/Product/Product";
import ProductDetails from "../customer/components/ProductDetails/ProductDetails";
import Checkout from "../customer/components/Checkout/Checkout";
import Order from "../customer/components/Order/Order";
import OrderDetail from "../customer/components/Order/OrderDetail";
import PaymentSuccess from "../customer/components/paymentSuccess/PaymentSuccess";
import RateProduct from "../customer/components/ReviewProduct/RateProduct";

function CustomerRouter() {
  console.log("user routes");
  return (
    <>
      <Routes>
        <Route path="/" element={<Root></Root>}>
          <Route path="/login" element={<Home></Home>}></Route>
          <Route path="/register" element={<Home></Home>}></Route>
          <Route path="/" element={<Home></Home>}></Route>
          <Route path="/cart" element={<Cart></Cart>}></Route>
          <Route path="/:levelOne/:levelTwo/:levelThree" element={<Product></Product>}></Route>
          <Route path="/product/:productID" element={<ProductDetails></ProductDetails>}></Route>
          <Route path="/checkout" element={<Checkout></Checkout>}></Route>
          <Route path="/account/order" element={<Order></Order>}></Route>
          <Route path="/account/order/:orderID" element={<OrderDetail></OrderDetail>}></Route>
          <Route path="/payment/:orderID" element={<PaymentSuccess></PaymentSuccess>}></Route>
          <Route path="/account/rate/:productId" element={<RateProduct />}></Route>
        </Route>
      </Routes>
    </>
  );
}

export default CustomerRouter;
