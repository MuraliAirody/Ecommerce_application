
import "./App.css";
import Footer from "./customer/components/Footer/Footer";
import Navigation from "./customer/components/Navigation/Navigation";
import Product from "./customer/components/Product/Product";
import ProductDetails from "./customer/components/ProductDetails/ProductDetails";
import Home from "./customer/pages/Home";

function App() {

  return (
    <>
      <div>
        <Navigation></Navigation>
      </div>
      <div>
        {/* <Home></Home> */}
        {/* <Product/> */}
        <ProductDetails/>
      </div>
      <div>
        <Footer></Footer>
      </div>
    </>
  );
}

export default App;
