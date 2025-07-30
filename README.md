
# Call Center Simulation 📞

This Java project simulates a basic **Call Center Management System**, showcasing fundamental object-oriented programming concepts such as encapsulation, queue management, and time-based event handling.

## 📦 Project Structure

- **Call.java**  
  Models a caller with a name, VIP status, and call duration.

- **CallCenter.java**  
  Manages incoming calls using an array-based queue. Handles answering calls, prioritizing VIPs, simulating the passage of time, and tracking the current state of the system.

## 🛠️ Features

- Add new calls (VIP or regular) to the queue.
- Automatically prioritize VIP calls.
- Simulate answering calls and progressing time using `clockTick()`.
- Track:
  - The number of calls waiting
  - The current call on the line
  - Queue capacity
  - Call positions in line

## 🧪 Testing

Unit tests are included:
- `CallTest.java`: Verifies behavior of the `Call` class.
- `CallCenterTest.java`: Tests all major functionality of the `CallCenter` class.

## ✅ Learning Objectives

- Practice class design and object state management
- Work with arrays to simulate queues
- Learn conditional logic and time-based simulations

---

© Jay Anupoju | Version: July 29, 2025
