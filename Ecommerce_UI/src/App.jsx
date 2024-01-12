
import { RouterProvider, createBrowserRouter } from "react-router-dom";
import "./App.css";

import Root from "./Root";
import CustomerRouter from "./Routes/CustomerRouter";

function App() {


  return (
    <>
      <CustomerRouter/>
    </>
  );
}

export default App;
