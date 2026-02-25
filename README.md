**🎬 BookMyMovie – Full Stack Movie Booking System**
**Overview**

BookMyMovie is a full stack movie ticket booking system built to simulate how a real-world theatre booking platform works internally.

The focus of this project was backend system design, authentication flow, database relationships, and handling real-world edge cases like concurrent seat booking.

The frontend is built to interact fully with the backend APIs and simulate a production-like workflow.
____________________________________________________________________________________________________________________________________________________________________________________________________
**🧱 System Architecture**

The system is built using:

**Backend**

Spring Boot

Spring Security

JWT Authentication (Stateless)

Spring Data JPA (Hibernate)

MySQL

Optimistic Locking for concurrency control

**Frontend**

React (Vite)

Axios

React Router

Tailwind CSS
_____________________________________________________________________________________________________________________________________________________________________________________________________

The backend follows layered architecture:

Controller layer

Service layer

Repository layer

Security layer (JWT Filter + SecurityConfig)
____________________________________________________________________________________________________________________________________________________________________________________________________

**🔐 Authentication & Security**

The application uses stateless authentication using JWT.

Flow:

User logs in → server validates credentials.

JWT token is generated and returned.

Every subsequent request must send the token.

JWT Filter validates token and sets SecurityContext.

Backend extracts logged-in user from SecurityContext.

Role-based access control is implemented:

USER → booking access

ADMIN → movie & show management + revenue
____________________________________________________________________________________________________________________________________________________________________________________________________

**🎟 Booking Workflow**

Booking logic includes:

Seat selection via theatre-style layout

Seat status validation

Transactional booking method

User attached to booking via SecurityContext

Foreign key integrity enforcement

To prevent double booking, optimistic locking is implemented:

_@Version
private Integer version;_

If two users try to book the same seat simultaneously, one request fails safely.

____________________________________________________________________________________________________________________________________________________________________________________________________
**📊 Revenue System**

Admin can:

View total bookings per show

View total revenue per show

Revenue is calculated using custom JPQL aggregation queries.

____________________________________________________________________________________________________________________________________________________________________________________________________
**🗄 Database Model**

**Entities:**

User

Movie

Show

Seat

Booking

**Key relationships:**

One Movie → Many Shows

One Show → Many Seats

One User → Many Bookings

Booking linked to Show and User

Past shows are automatically filtered based on show time.

Seats are auto-generated when a show is created.

____________________________________________________________________________________________________________________________________________________________________________________________________
**🚀 Features Implemented**
User

Register & Login

View movies and shows

View only upcoming shows

Select seats

View seat prices before confirmation

Book seats

View booking history

Admin

Create movies

Create shows

Automatic seat generation

View revenue per show

____________________________________________________________________________________________________________________________________________________________________________________________________
**🧠 What This Project Demonstrates**

Understanding of stateless authentication

Deep knowledge of Spring Security flow

Proper use of SecurityContext

Handling concurrency with optimistic locking

Clean entity relationship mapping

Debugging real database and mapping issues

Full frontend-backend integration

This project was built to move beyond CRUD applications and explore real backend system behavior.

____________________________________________________________________________________________________________________________________________________________________________________________________
**📌 Future Improvements**

Payment integration

Booking cancellation & refund simulation

Dockerization

Deployment

Unit & integration tests

Performance optimization
____________________________________________________________________________________________________________________________________________________________________________________________________
