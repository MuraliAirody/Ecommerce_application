
import "./App.css";
import Navigation from "./customer/components/Navigation/Navigation";
import Home from "./customer/pages/Home";

function App() {

  return (
    <>
      <div>
        <Navigation></Navigation>
      </div>
      <div>
        <Home></Home>
      </div>
    </>
  );
}

export default App;
