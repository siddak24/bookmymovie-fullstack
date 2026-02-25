import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../services/api";
import Navbar from "../components/NavBar";
import MovieCard from "../components/MovieCard";
function Movies() {
  const navigate = useNavigate();

  const [movies, setMovies] = useState([]);
  const [loading, setLoading] = useState(true);

  // 🔐 Redirect if not logged in
  useEffect(() => {
    const token = localStorage.getItem("token");
    if (!token) {
      navigate("/login");
    }
  }, [navigate]);

  // 🎬 Fetch Movies
  useEffect(() => {
    setLoading(true);

    api.get("/movies?page=0&size=10")
      .then((response) => {
        console.log("Movies response:", response.data);

        // If backend returns Page
        if (response.data?.content) {
          setMovies(response.data.content);
        }
        // If backend returns List
        else if (Array.isArray(response.data)) {
          setMovies(response.data);
        }
        else {
          setMovies([]);
        }

        setLoading(false);
      })
      .catch((error) => {
        console.error("Error fetching movies:", error);
        setMovies([]);
        setLoading(false);
      });

  }, []);

  const handleLogout = () => {
    localStorage.removeItem("token");
    navigate("/login");
  };

  if (loading) {
    return <div style={{ padding: "40px" }}>Loading movies...</div>;
  }

  return (
  <div className="min-h-screen bg-[#0f0f2d] text-white">

    <Navbar />

    {/* Hero */}
    <div className="relative h-80 bg-gradient-to-r from-pink-600 via-purple-600 to-indigo-700 flex items-center justify-center">
      <h1 className="text-4xl md:text-5xl font-bold text-white drop-shadow-lg text-center px-6">
        Experience Cinema Like Never Before 🎬
      </h1>
    </div>

    <div className="px-8 mt-10">

      <h1 className="text-3xl font-bold mb-6">
        🔥 Now Showing
      </h1>

      {movies.length === 0 ? (
        <p>No movies available</p>
      ) : (
        <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-8 pb-16">
          {movies.map((movie) => (
            <MovieCard key={movie.id} movie={movie} />
          ))}
        </div>
      )}
    </div>
  </div>
);
}

export default Movies;