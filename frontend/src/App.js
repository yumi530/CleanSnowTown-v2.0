import { BrowserRouter, Routes, Route } from "react-router-dom";
import LandingPage from "./components/views/LandingPage/LandingPage";
import LoginPage from "./components/views/LoginPage/LoginPage";

export default function App() {
  return (
    <div>
      <BrowserRouter>
        {/* Routes nest inside one another. Nested route paths build upon
              parent route paths, and nested route elements render inside
              parent route elements. See the note about <Outlet> below. */}
        <Routes>
          <Route exact path="/" element={<LandingPage/>} />
          <Route path="/login" element={<LoginPage/>} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}