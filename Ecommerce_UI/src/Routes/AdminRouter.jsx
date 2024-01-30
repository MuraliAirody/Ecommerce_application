import React from "react";
import {
  Route,
  Routes,
} from "react-router-dom";
import AdminPanel from "../admin/AdminPanel";



function AdminRouter() {
  return (
    <div>
      <Routes>
        <Route path="/*" element={<AdminPanel></AdminPanel>}></Route>
      </Routes>
    </div>
  );
}

export default AdminRouter;
