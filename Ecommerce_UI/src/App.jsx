import {
  BrowserRouter,
  Route,
  Router,
  RouterProvider,
  Routes,
  createBrowserRouter,
} from "react-router-dom";
import "./App.css";

import CustomerRouter from "./Routes/CustomerRouter";
import { Provider } from "react-redux";
import { store } from "./redux/store";
import AdminRouter from "./Routes/AdminRouter";

function App() {
  return (
    <Provider store={store}>
      <BrowserRouter>
        <Routes>
          <Route path="/*" element={<CustomerRouter />} />
          <Route path="/admin/*" element={<AdminRouter />} />
        </Routes>
      </BrowserRouter>
    </Provider>
  );
}

export default App;
