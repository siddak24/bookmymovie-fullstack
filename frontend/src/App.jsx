import { Routes, Route } from "react-router-dom";
import Movies from "./pages/Movies";
import Login from "./pages/Login";
import Seats from "./pages/Seats";
import Shows from "./pages/Shows";
import MyBookings from "./pages/MyBookings";
import Admin from "./pages/Admin";

<Route path="/seats/:showId" element={<Seats />} />

function App() {
  return (
    <Routes>
      <Route path="/" element={<Movies />} />
      <Route path="/login" element={<Login />} />
      <Route path="/shows/:movieId" element={<Shows />} />
      <Route path="/seats/:showId" element={<Seats />} />
      <Route path="/admin" element={<Admin />} />
      <Route path="/my-bookings" element={<MyBookings />} />
    </Routes>
  );
}

export default App;