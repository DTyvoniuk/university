import Navigation from "./Navigation";
import {Route, Routes, BrowserRouter} from "react-router-dom";
import Department from "../pages/Department/Department";
import Lector from "../pages/Lector/Lector";
import { NotificationContainer } from 'react-notifications';
import 'react-notifications/lib/notifications.css';


function Container() {
  return (
      <>
        <NotificationContainer />
        <BrowserRouter>
          <div>
            <Navigation />
            <Routes>
              <Route path="/" element={<Department />} />
              <Route path="/lector" element={<Lector />} />
          </Routes>
          </div>
        </BrowserRouter>
      </>
  );
}

export default Container;
