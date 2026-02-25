import { Search, MapPin, LogOut, Ticket } from "lucide-react";
import { useNavigate } from "react-router-dom";

function Navbar() {
  const navigate = useNavigate();

  const handleLogout = () => {
    localStorage.removeItem("token");
    navigate("/login");
  };

  return (
    <div className="bg-[#14143c] px-8 py-4 flex items-center justify-between shadow-lg">

      {/* Logo */}
      <div
        className="flex items-center gap-3 cursor-pointer"
        onClick={() => navigate("/")}
      >
        <div className="bg-pink-500 p-2 rounded-lg text-lg">
          🎬
        </div>
        <h1 className="text-xl font-bold text-pink-400">
          BookMyMovies
        </h1>
      </div>

      {/* Search */}
      <div className="hidden md:flex items-center bg-[#1c1c4f] px-4 py-2 rounded-lg w-1/3">
        <Search size={18} className="text-gray-400" />
        <input
          type="text"
          placeholder="Search for movies..."
          className="bg-transparent outline-none ml-2 w-full text-sm"
        />
      </div>

      {/* Right side */}
      <div className="flex items-center gap-6 text-sm">
        <div
          className="flex items-center gap-2 cursor-pointer hover:text-pink-400 transition"
          onClick={() => navigate("/my-bookings")}
        >
          <Ticket size={18} />
          My Bookings
        </div>

        <div className="flex items-center gap-1 text-gray-300">
          <MapPin size={16} />
          Mumbai
        </div>

        <button
          onClick={handleLogout}
          className="bg-pink-600 hover:bg-pink-700 px-4 py-1 rounded-lg transition flex items-center gap-1"
        >
          <LogOut size={16} />
          Logout
        </button>
      </div>
    </div>
  );
}

export default Navbar;