import { useEffect, useState } from "react";
import Navbar from "../components/Navbar";
import api from "../services/api";

function Admin() {
  const [movies, setMovies] = useState([]);
  const [shows, setShows] = useState([]);

  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [genre, setGenre] = useState("");
  const [language, setLanguage] = useState("");
  const [duration, setDuration] = useState("");
  const [releaseDate, setReleaseDate] = useState("");

  const [selectedMovieId, setSelectedMovieId] = useState("");
  const [showTime, setShowTime] = useState("");

  const [revenueData, setRevenueData] = useState(null);

  useEffect(() => {
    loadMovies();
  }, []);

  const loadMovies = async () => {
    try {
      const res = await api.get("/movies?page=0&size=100");

      let movieList = [];
      if (res.data?.content) {
        movieList = res.data.content;
      } else {
        movieList = res.data;
      }

      setMovies(movieList);
    } catch (err) {
      console.error("Error loading movies:", err);
    }
  };

  const loadShows = async (movieId) => {
    try {
      const res = await api.get(`/shows/movie/${movieId}`);
      setShows(res.data);
    } catch (err) {
      console.error("Error loading shows:", err);
    }
  };

  const createMovie = async () => {
    if (!title || !duration || !releaseDate) {
      alert("Fill required fields");
      return;
    }

    try {
      await api.post("/admin/movie", {
        title,
        description,
        genre,
        language,
        duration: parseInt(duration),
        releaseDate,
      });

      alert("Movie Created Successfully!");

      setTitle("");
      setDescription("");
      setGenre("");
      setLanguage("");
      setDuration("");
      setReleaseDate("");

      loadMovies();
    } catch (err) {
      console.error(err);
      alert("Error creating movie");
    }
  };

  const createShow = async () => {
    if (!selectedMovieId || !showTime) {
      alert("Select movie and show time");
      return;
    }

    try {
      await api.post(`/admin/show/${selectedMovieId}`, {
        showTime,
      });

      alert("Show Created & Seats Generated!");

      setShowTime("");
      loadShows(selectedMovieId);
    } catch (err) {
      console.error(err);
      alert("Error creating show");
    }
  };

  const fetchRevenue = async (showId) => {
    try {
      const res = await api.get(`/admin/revenue/${showId}`);
      setRevenueData(res.data);
    } catch (err) {
      console.error(err);
      alert("Error fetching revenue");
    }
  };

  return (
    <div className="min-h-screen bg-[#0f0f2d] text-white">
      <Navbar />

      <div className="p-10 space-y-12">

        <h1 className="text-4xl font-bold text-pink-500">
          Admin Dashboard
        </h1>

        {/* ================= CREATE MOVIE ================= */}
        <div className="bg-[#18184a] p-6 rounded-xl space-y-4">
          <h2 className="text-2xl font-semibold">Create Movie</h2>

          <input
            className="w-full p-2 rounded bg-[#0f0f2d]"
            placeholder="Title"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
          />

          <input
            className="w-full p-2 rounded bg-[#0f0f2d]"
            placeholder="Description"
            value={description}
            onChange={(e) => setDescription(e.target.value)}
          />

          <input
            className="w-full p-2 rounded bg-[#0f0f2d]"
            placeholder="Genre"
            value={genre}
            onChange={(e) => setGenre(e.target.value)}
          />

          <input
            className="w-full p-2 rounded bg-[#0f0f2d]"
            placeholder="Language"
            value={language}
            onChange={(e) => setLanguage(e.target.value)}
          />

          <input
            type="number"
            className="w-full p-2 rounded bg-[#0f0f2d]"
            placeholder="Duration (minutes)"
            value={duration}
            onChange={(e) => setDuration(e.target.value)}
          />

          <input
            type="date"
            className="w-full p-2 rounded bg-[#0f0f2d]"
            value={releaseDate}
            onChange={(e) => setReleaseDate(e.target.value)}
          />

          <button
            onClick={createMovie}
            className="bg-pink-600 px-4 py-2 rounded hover:bg-pink-700"
          >
            Create Movie
          </button>
        </div>

        {/* ================= CREATE SHOW ================= */}
        <div className="bg-[#18184a] p-6 rounded-xl space-y-4">
          <h2 className="text-2xl font-semibold">Create Show</h2>

          <select
            className="w-full p-2 rounded bg-[#0f0f2d]"
            onChange={(e) => {
              setSelectedMovieId(e.target.value);
              loadShows(e.target.value);
            }}
          >
            <option>Select Movie</option>
            {movies.map((movie) => (
              <option key={movie.id} value={movie.id}>
                {movie.title}
              </option>
            ))}
          </select>

          <input
            type="datetime-local"
            className="w-full p-2 rounded bg-[#0f0f2d]"
            value={showTime}
            onChange={(e) => setShowTime(e.target.value)}
          />

          <button
            onClick={createShow}
            className="bg-blue-600 px-4 py-2 rounded hover:bg-blue-700"
          >
            Create Show (Auto Seats)
          </button>
        </div>

        {/* ================= SHOW LIST ================= */}
        {shows.length > 0 && (
          <div className="bg-[#18184a] p-6 rounded-xl space-y-4">
            <h2 className="text-2xl font-semibold">Shows</h2>

            {shows.map((show) => (
              <div
                key={show.id}
                className="flex justify-between items-center bg-[#0f0f2d] p-4 rounded"
              >
                <span>{show.showTime}</span>

                <button
                  onClick={() => fetchRevenue(show.id)}
                  className="bg-yellow-600 px-3 py-1 rounded hover:bg-yellow-700"
                >
                  View Revenue
                </button>
              </div>
            ))}
          </div>
        )}

        {/* ================= REVENUE DISPLAY ================= */}
        {revenueData && (
          <div className="bg-[#18184a] p-6 rounded-xl">
            <h2 className="text-2xl font-semibold mb-3">
              Revenue Report
            </h2>
            <p>Total Bookings: {revenueData.totalBookings}</p>
            <p>Total Revenue: ₹{revenueData.totalRevenue}</p>
          </div>
        )}

      </div>
    </div>
  );
}

export default Admin;