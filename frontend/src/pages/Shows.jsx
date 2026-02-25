import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import api from "../services/api";
import Navbar from "../components/Navbar";

function Shows() {
  const { movieId } = useParams();
  const navigate = useNavigate();

  const [shows, setShows] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    setLoading(true);

    api.get(`/shows/movie/${movieId}`)
      .then((res) => {
        console.log("Shows response:", res.data);

        if (Array.isArray(res.data)) {
          setShows(res.data);
        } else if (res.data?.content) {
          setShows(res.data.content);
        } else {
          setShows([]);
        }

        setLoading(false);
      })
      .catch((err) => {
        console.error("Error loading shows:", err);
        setShows([]);
        setLoading(false);
      });

  }, [movieId]);

  if (loading) {
    return (
      <div className="min-h-screen bg-[#0f0f2d] text-white flex items-center justify-center">
        Loading shows...
      </div>
    );
  }

  return (
    <div className="min-h-screen bg-[#0f0f2d] text-white">
      <Navbar />

      <div className="p-10">
        <h1 className="text-3xl font-bold mb-8">🎭 Available Shows</h1>

        {shows.length === 0 ? (
          <p>No shows available</p>
        ) : (
          <div className="space-y-4">
            {shows.map((show) => (
              <div
                key={show.id}
                className="bg-[#18184a] p-6 rounded-lg flex justify-between items-center"
              >
                <span>{show.showTime}</span>
                <button
                  onClick={() => navigate(`/seats/${show.id}`)}
                  className="bg-pink-600 hover:bg-pink-700 px-4 py-2 rounded"
                >
                  Select Seats
                </button>
              </div>
            ))}
          </div>
        )}
      </div>
    </div>
  );
}

export default Shows;