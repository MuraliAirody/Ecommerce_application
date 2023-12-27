import { useState } from "react";
import reactLogo from "./assets/react.svg";
import viteLogo from "/vite.svg";
import "./App.css";
import Navigation from "./customer/components/Navigation/Navigation";
import Home from "./customer/pages/Home";

function App() {
  const [count, setCount] = useState(0);

  return (
    <>
     <Navigation></Navigation>
    <div>
       <Home></Home>
    </div>
    </>
  );
}

export default App;
