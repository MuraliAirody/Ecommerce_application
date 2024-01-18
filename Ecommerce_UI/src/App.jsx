import { RouterProvider, createBrowserRouter } from "react-router-dom";
import "./App.css";

import Root from "./Root";
import CustomerRouter from "./Routes/CustomerRouter";
import { Provider } from "react-redux";
import { store } from "./redux/store";

function App() {
  return (
    <>
      <Provider store={store}>
        <CustomerRouter />
      </Provider>
    </>
  );
}

export default App;
