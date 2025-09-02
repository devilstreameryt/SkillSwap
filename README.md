# 🔄 Spring MVC CRUD - SkillSwap

A full-stack Java web application for skill exchange and networking. Built using Spring MVC, Hibernate, JSP, and MySQL, this project demonstrates a complete CRUD (Create, Read, Update, Delete) system following the MVC architecture.

---

## 🚀 Features

* 🔍 View all users and their skills
* ➕ Add new skills (offered/wanted)
* ✏️ Edit user profile and skills
* ❌ Delete users
* 🔔 Send and respond to skill swap requests
* 💬 Chat with other users after request acceptance
* 🧩 MySQL + Hibernate integration
* 🖥️ JSP views using JSTL and Spring Form tags
* 🎨 Styled with Bootstrap 4
* ⚙️ XML-based Spring configuration
* 🔁 Transactional session management with Hibernate

---

## 🛠️ Tech Stack

| Layer           | Technology                  |
| --------------- | --------------------------- |
| Backend         | Java, Spring MVC, Hibernate |
| Frontend        | JSP, JSTL, Bootstrap 4      |
| Database        | MySQL                       |
| ORM             | Hibernate                   |
| Build Tool      | Maven                       |
| Connection Pool | c3p0                        |
| Server          | Apache Tomcat               |

---

## 🗂️ Project Structure

```
src/
└── com.skillswap
    ├── controller         # UserController, ChatController
    ├── dao                # UserDAO, SkillDAO, RequestDAO, MessageDAO, NotificationDAO
    ├── model              # User.java, Skill.java, Request.java, Chat.java, Message.java, Notifications.java
    ├── service            # UserService, SkillService, RequestService, ChatService, MessageService, NotificationService

WebContent/
├── WEB-INF/
│   ├── lib/               # External JARs
│   ├── view/              # JSP pages (profile.jsp, dashboard.jsp, chats.jsp)
│   ├── spring-container.xml
│   └── web.xml
├── resources/
│   └── css/style.css
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

`Java` `Spring MVC` `Hibernate` `JSP` `MySQL` `MVC` `Web-Application` `Full-Stack` `Bootstrap` `JSTL` `CRUD` `c3p0` `XML` `Apache Tomcat` `SkillSwap` `Chat` `Notifications`
