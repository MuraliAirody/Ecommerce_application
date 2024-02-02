import React, { useEffect, useState } from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import { useSelector, useDispatch } from "react-redux";
import CustomerRouter from "./Routes/CustomerRouter";
import AdminRouter from "./Routes/AdminRouter";
import { getUser } from "./redux/Auth/action";
import NotFound from "./pages/Notfound";

function App() {
  const [profile, setProfile] = useState(null);
  const jwt = localStorage.getItem("jwt");
  const auth = useSelector((state) => state.auth);
  const dispatch = useDispatch();

  useEffect(() => {
    if (jwt) {
      dispatch(getUser(jwt));
    }
  }, [dispatch, jwt]);

  useEffect(() => {
    setProfile(auth.user);
  }, [auth]);

  return (
    <BrowserRouter>
      <Routes>
        <Route path="/*" element={<CustomerRouter />} />
        {profile?.role === "Admin" ? (
          <Route path="/admin/*" element={<AdminRouter />} />
        ) : (
          <Route path="/admin/*" element={<NotFound />} />
        )}
        <Route path="/error" element={<NotFound />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
