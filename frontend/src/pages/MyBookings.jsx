import { useEffect, useState } from "react";
import api from "../services/api";

function MyBookings() {
  const [bookings, setBookings] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    api.get("/bookings")
      .then((response) => {
        setBookings(response.data);
        setLoading(false);
      })
      .catch((error) => {
        console.error(error);
        setBookings([]);
        setLoading(false);
      });
  }, []);

  if (loading) {
    return <div style={{ padding: "40px" }}>Loading bookings...</div>;
  }

  return (
    <div style={{ padding: "40px" }}>
      <h1>📋 My Bookings</h1>

      {bookings.length === 0 ? (
        <p>No bookings yet</p>
      ) : (
        bookings.map((booking) => (
          <div key={booking.id} style={{ marginBottom: "15px" }}>
            <p><strong>Show ID:</strong> {booking.showId}</p>
            <p><strong>Amount:</strong> ₹{booking.totalAmount}</p>
            <p><strong>Time:</strong> {booking.bookingTime}</p>
          </div>
        ))
      )}
    </div>
  );
}

export default MyBookings;