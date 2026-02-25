import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import api from "../services/api";
import Navbar from "../components/Navbar";

function Seats() {
  const { showId } = useParams();
  const [seats, setSeats] = useState([]);
  const [selectedSeats, setSelectedSeats] = useState([]);

  useEffect(() => {
    api.get(`/seats/show/${showId}`)
      .then((res) => setSeats(res.data))
      .catch((err) => console.error(err));
  }, [showId]);

  const toggleSeat = (seat) => {
    if (seat.status === "BOOKED") return;

    if (selectedSeats.includes(seat.id)) {
      setSelectedSeats(selectedSeats.filter(id => id !== seat.id));
    } else {
      setSelectedSeats([...selectedSeats, seat.id]);
    }
  };

  const handleBooking = async () => {
    try {
      for (let id of selectedSeats) {
        await api.post(`/bookings/${id}`);
      }

      alert("Booking successful!");

      const updated = await api.get(`/seats/show/${showId}`);
      setSeats(updated.data);
      setSelectedSeats([]);

    } catch (err) {
      alert("Booking failed!");
    }
  };

  // Group seats by row
  const groupedSeats = seats.reduce((acc, seat) => {
    const row = seat.seatNumber[0];
    if (!acc[row]) acc[row] = [];
    acc[row].push(seat);
    return acc;
  }, {});

  return (
    <div className="min-h-screen bg-[#0f0f2d] text-white">
      <Navbar />

      <div className="flex flex-col items-center mt-10">

        <h1 className="text-3xl font-bold mb-6">🎟 Choose Your Seats</h1>

        {/* Curved Screen */}
        <div className="w-2/3 h-6 bg-gray-300 rounded-t-full mb-10 shadow-lg" />

        {/* Theatre */}
        <div className="space-y-6">
          {Object.keys(groupedSeats).sort().map((row) => (
            <div key={row} className="flex items-center gap-4">

              {/* Row Label */}
              <span className="w-6 text-gray-400">{row}</span>

              {/* Seats */}
              <div className="flex gap-2">
                {groupedSeats[row].map((seat, index) => {
                  const isBooked = seat.status === "BOOKED";
                  const isSelected = selectedSeats.includes(seat.id);

                  return (
                    <div
                      key={seat.id}
                      onClick={() => toggleSeat(seat)}
                      className={`
                        w-10 h-10 text-xs flex items-center justify-center rounded-md cursor-pointer
                        transition
                        ${isBooked ? "bg-red-600 cursor-not-allowed" : ""}
                        ${!isBooked && !isSelected ? "bg-green-500 hover:bg-green-600" : ""}
                        ${isSelected ? "bg-yellow-400 text-black" : ""}
                      `}
                    >
                      {seat.seatNumber.slice(1)}
                    </div>
                  );
                })}
              </div>

            </div>
          ))}
        </div>

        {/* Legend */}
        <div className="flex gap-6 mt-10 text-sm">
          <div className="flex items-center gap-2">
            <div className="w-4 h-4 bg-green-500 rounded"></div>
            Available
          </div>
          <div className="flex items-center gap-2">
            <div className="w-4 h-4 bg-red-600 rounded"></div>
            Booked
          </div>
          <div className="flex items-center gap-2">
            <div className="w-4 h-4 bg-yellow-400 rounded"></div>
            Selected
          </div>
        </div>

{/* Confirm */}
{selectedSeats.length > 0 && (
  <div className="mt-10 flex flex-col items-center gap-4">
    <div className="text-lg font-semibold">
      Selected Seats: {selectedSeats.length}
    </div>
    <button
      onClick={handleBooking}
      className="px-6 py-3 bg-blue-600 hover:bg-blue-700 rounded-lg font-semibold transition"
    >
      Confirm Booking
    </button>
  </div>
)}

      </div>
    </div>
  );
}

export default Seats;