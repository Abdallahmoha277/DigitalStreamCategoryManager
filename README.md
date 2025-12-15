# DigitalStreamCategoryManager

A Java-based desktop application for managing content categories in a digital media platform.  
The system allows administrators to create, view, and manage categories through a clean JavaFX user interface.

---

## 🚀 Features

- Admin login screen (JavaFX)
- Dashboard with sidebar navigation
- Categories management:
  - View all categories
  - Add new category (Code + Description)
- Clean separation of concerns:
  - UI layer (JavaFX)
  - Service layer
  - Data model layer
- Uses **MySQL** database
- Built with **NetBeans** and **JavaFX**

---

## 🧱 Project Structure
رsrc/
├── models/ # Data models (Category, ContentItem)
├── services/ # Business logic and database access
├── proxy/ # Proxy pattern for access control
├── datastore/ # In-memory data store (used in early stages)
├── ui/ # Swing-based UI (legacy / optional)
└── ui/fx/ # JavaFX UI (main application)


---

## 🛠️ Technologies Used

- Java (JDK 17+)
- JavaFX
- MySQL
- JDBC
- NetBeans IDE
- Git & GitHub

---

## 🗄️ Database Schema

**Table: `categories`**

| Column       | Type         | Constraints |
|-------------|--------------|-------------|
| code        | VARCHAR(50)  | PRIMARY KEY |
| description | VARCHAR(255) | NOT NULL    |

> ⚠️ The project currently uses only `code` and `description`.

---

## ▶️ How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/DigitalStreamCategoryManager.git

   ui.fx.LoginFX

   Username: admin
Password: 1234
📌 Notes

Some legacy Swing classes still exist but are not used in the main workflow.

The project is gradually transitioning to JavaFX-only UI.

Proxy pattern is used for basic access control simulation.

👤 Author

Abdallah Adam
Computer Science Student
Java & Database Enthusiast

📄 License
This project is for educational purposes.

