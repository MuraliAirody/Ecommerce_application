import React from 'react'
import Cart from "./customer/components/Cart/Cart";
import Checkout from "./customer/components/Checkout/Checkout";
import Footer from "./customer/components/Footer/Footer";
import Navigation from "./customer/components/Navigation/Navigation";
import Product from "./customer/components/Product/Product";
import ProductDetails from "./customer/components/ProductDetails/ProductDetails";
import Home from "./customer/pages/Home";

function Root() {
  return (
    <div>
      <div>
        <Navigation></Navigation>
      </div>
      <div>
        {/* <Home></Home> */}
        {/* <Product/> */}
        {/* <ProductDetails/> */}
        {/* <Cart/> */}
        <Checkout/>
      </div>
      <div>
        <Footer></Footer>
      </div>
    </div>
  )
}

export default Root
