BookMyMovie – Full Stack Movie Booking System
About This Project

This is a full stack movie ticket booking system that I built to understand how real-world backend systems work beyond simple CRUD operations.

The goal of this project was not just to build a UI, but to deeply understand:

Authentication using JWT

Role-based access control

Database relationships

Concurrency issues like double booking

Transaction handling

Revenue calculation logic

Full frontend–backend integration

I focused mainly on backend architecture while also building a working frontend to simulate a real product.

🛠 Tech Stack
Backend

Spring Boot

Spring Security

JWT Authentication

Spring Data JPA (Hibernate)

MySQL

Optimistic Locking using @Version

Frontend

React (Vite)

React Router

Axios

Tailwind CSS

🔐 Features
User Side

Register & Login using JWT

View movies

View shows (expired shows are automatically filtered)

Theatre-style seat layout

See seat prices before booking

Book seats

View booking history

Double booking prevention using optimistic locking

Admin Side

Role-based access (ADMIN)

Create movies

Create shows

Automatic seat generation when show is created

View revenue per show (total bookings + earnings)

🧠 Key Backend Concepts Implemented
1. Stateless Authentication

The app uses JWT, so every request must send a token.
The backend extracts the user from SecurityContext and attaches it to bookings.

2. Optimistic Locking

To prevent race conditions (two users booking the same seat at the same time), I used:

@Version
private Integer version;

This ensures that only one booking succeeds if concurrent updates happen.

3. Proper Entity Relationships

Entities used:

User

Movie

Show

Seat

Booking

Relationships include:

One Movie → Many Shows

One Show → Many Seats

One User → Many Bookings

I also handled foreign key issues and relationship mapping (mappedBy) correctly.

4. Revenue Calculation

Admin can view:

Total bookings per show

Total revenue per show

Using custom JPQL queries.

🗄 Database Structure

Main Tables:

users

movies

shows

seats

bookings

The system ensures:

Seat status updates properly

Foreign keys are maintained

Bookings are linked to logged-in users

What I Learned From This Project

How Spring Security actually works internally

Difference between getPrincipal() and getName()

How JWT filters populate SecurityContext

Why HTTP is stateless

How to prevent concurrency bugs

Handling real debugging issues (FK constraints, mapping errors, auth bugs)

Structuring backend services properly

This project helped me move from just writing code to actually understanding backend system behavior.