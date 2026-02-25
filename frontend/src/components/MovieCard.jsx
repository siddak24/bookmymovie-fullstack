import { useNavigate } from "react-router-dom";

function MovieCard({ movie }) {
  const navigate = useNavigate();

  return (
    <div
      onClick={() => navigate(`/shows/${movie.id}`)}
      className="bg-[#18184a] rounded-xl overflow-hidden hover:scale-105 transition-all duration-300 shadow-xl cursor-pointer group"
    >
      <div className="relative">
        <img
          src="https://images.unsplash.com/photo-1524985069026-dd778a71c7b4"
          alt={movie.title}
          className="h-72 w-full object-cover group-hover:brightness-75 transition-all duration-300"
        />

        {/* Rating badge */}
        <div className="absolute top-3 left-3 bg-black/70 px-3 py-1 rounded-full text-sm">
          ⭐ 4.5
        </div>

        {/* Hover overlay */}
        <div className="absolute inset-0 flex items-center justify-center opacity-0 group-hover:opacity-100 transition duration-300">
          <button className="bg-pink-600 px-5 py-2 rounded-lg font-semibold hover:bg-pink-700 transition">
            View Shows
          </button>
        </div>
      </div>

      <div className="p-4">
        <h2 className="font-semibold text-lg">{movie.title}</h2>
        <p className="text-gray-400 text-sm mt-1">
          {movie.genre} / {movie.language}
        </p>
      </div>
    </div>
  );
}

export default MovieCard;