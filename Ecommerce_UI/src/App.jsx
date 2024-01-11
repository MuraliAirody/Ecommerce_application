
import { RouterProvider, createBrowserRouter } from "react-router-dom";
import "./App.css";

import Root from "./Root";

function App() {

  const router = createBrowserRouter([
    {
      path:'/',
      element:<Root/>
    }
  ])

  return (
    <>
      <RouterProvider router={router}/>
    </>
  );
}

export default App;
