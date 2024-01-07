
import "./App.css";
import Navigation from "./customer/components/Navigation/Navigation";
import Product from "./customer/components/Product/Product";
import Home from "./customer/pages/Home";

function App() {

  return (
    <>
      <div>
        <Navigation></Navigation>
      </div>
      <div>
        {/* <Home></Home> */}
        <Product/>
      </div>
    </>
  );
}

export default App;
