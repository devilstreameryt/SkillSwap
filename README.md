# 🔄 SkillSwap – Peer-to-Peer Skill Sharing Platform

SkillSwap is a web-based application designed to connect users who want to **share or learn skills** from each other. Users can create profiles, list the skills they offer and want, send requests to exchange skills, and communicate via built-in chat.

---

## 🚀 Features

1. **User Registration & Login**

   * Users create an account with basic information.
   * Login is authenticated by email and password.

2. **Profile & Skills**

   * Users can manage their profile, including first/last name, bio, location, and photo.
   * Users can add skills they **offer** and skills they **want** to learn.

3. **Sending Requests**

   * Users browse others with desired skills.
   * Send skill exchange requests.
   * Notifications alert users of incoming requests.

4. **Request Response**

   * Receivers can accept or reject requests.
   * Upon acceptance, a chat is automatically created.

5. **Chat & Messaging**

   * Users can chat with connected peers.
   * Messages are timestamped and stored in the database.

---

## 🛠️ Tech Stack

| Layer           | Technology                  |
| --------------- | --------------------------- |
| Backend         | Java, Spring MVC, Spring Transaction Management, Hibernate/JPA |
| Frontend        | JSP, HTML, CSS, Bootstrap      |
| Database        | MySQL   |
| File Handling   | Multipart file uploads for profile photos  |
| ORM             | Hibernate                   |
| Build Tool      | Maven                       |
| Connection Pool | c3p0                        |
| Server          | Apache Tomcat               |

---

## 🗂️ Project Structure

```
src/
└── com.skillswap
    ├── controller         # UserController, handles HTTP requests
    ├── dao                # UserDAO, SkillDAO, RequestDAO, MessageDAO, NotificationDAO interfaces and implementations
    ├── model              # User, Skill, SkillsOffered, SkillsWanted, UserDetails, Request, Chat, Message, Notifications
    ├── service            # UserService, SkillService, RequestService, ChatService, MessageService, NotificationService
                            
WebContent/
├── WEB-INF/
│   ├── lib/               # External JARs
│   ├── view/              # JSP pages (home, signIn, singUp, dashboard, profile, sendRequests, viewRequests, notifications, chats, messages)
│   ├── spring-container.xml
│   └── web.xml
├── resources/
│   └── images/Screenshot_1.png
```

---

## 🧪 How to Run

1. Clone the repository:

```bash
git clone https://github.com/abanoubwagim/SkillSwap.git
cd SkillSwap
```

2. Import into Eclipse or IntelliJ as a Maven project.

3. Configure your MySQL database:

* Create a schema named `skillswap`
* Tables will be created automatically by Hibernate
* Update `spring-container.xml` with your DB username/password if needed

4. Deploy the project to Apache Tomcat server.

5. Open your browser:

```
http://localhost:8081/SkillSwap/
```

---

## 📄 License

This project is open-source and free to use under the MIT License.

---

## 🏷️ GitHub Topics

`Java` `Spring MVC` `Hibernate` `JSP` `MySQL` `MVC` `Web-Application` `Full-Stack` `Bootstrap` `JSTL` `CRUD` `c3p0` `XML` `Apache Tomcat` `SkillSwap` `Chat` `Notifications` `Maven` `Peer-to-Peer` `SkillSharing`

