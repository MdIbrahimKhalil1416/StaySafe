# StayEase

A hotel management system - Spring Boot + MongoDB + Thymeleaf, structured
around SOLID principles.

## Run locally

1. Have MongoDB running locally (or point MONGODB_URI at Atlas, see below).
2. `mvn spring-boot:run`
3. Open http://localhost:8080

## How it works

- Add rooms first (`/rooms/new`).
- Create a booking (`/bookings/new`) - only rooms with status AVAILABLE show up
  in the dropdown. Booking a room automatically sets it to OCCUPIED and stores
  that room's price on the booking.
- Checking out a booking sets the room back to AVAILABLE **and** auto-generates
  an invoice (nights x price) under `/invoices`. Cancelling just frees the room.
- Manage employees under `/staff` (independent of rooms/bookings).

## Project layout

```
model/       Room, RoomType, RoomStatus, Booking, BookingStatus,
             Staff, StaffRole, Invoice - data only
repository/  RoomRepository, BookingRepository, StaffRepository, InvoiceRepository
service/     RoomService, BookingService, StaffService, InvoiceService (interfaces)
service/impl RoomServiceImpl, BookingServiceImpl, StaffServiceImpl, InvoiceServiceImpl
             (BookingServiceImpl depends on RoomService + InvoiceService,
              both via their interfaces, to update room status and bill on checkout)
controller/  HomeController, RoomController, BookingController,
             StaffController, InvoiceController
exception/   ResourceNotFoundException, InvalidBookingException
templates/   dashboard.html, rooms.html, room-form.html,
             bookings.html, booking-form.html, staff.html, staff-form.html,
             invoices.html, fragments/nav.html
static/css/  style.css
```

## Deploying (MongoDB Atlas + Render)

1. **Database:** create a free cluster at mongodb.com/cloud/atlas, add a database
   user, allow network access from anywhere (0.0.0.0/0), copy the connection
   string (e.g. `mongodb+srv://user:pass@cluster.mongodb.net/stayease`).
2. Push this project to a GitHub repo.
3. **Render (render.com):** New > Web Service > connect your repo.
   - Build command: `mvn clean package -DskipTests`
   - Start command: `java -jar target/stayease-0.0.1-SNAPSHOT.jar`
   - Env var: `MONGODB_URI` = your Atlas connection string
   - Render sets `PORT` automatically - the app already reads it.
4. Your live domain (e.g. `https://stayease-xxxx.onrender.com`) is what you submit.

Railway.app works the same way if you prefer it over Render.
