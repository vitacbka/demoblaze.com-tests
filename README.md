##                        ![img_1.png](img_1.png)
# 🧪 Demoblaze Test Automation

Automated UI tests for [Demoblaze](https://www.demoblaze.com) using **Java + JUnit 5 + Selenide**.

## 🛠️ Tech Stack
- Java 11+
- JUnit 5
- Selenide
- Maven
- Chrome / Firefox
- AssertJ

## 📌 What’s Covered
🔐 **Login:**
- valid & invalid credentials
- empty fields

🖥️ **Header:**
- modals for Contact
- About Us (with video)
- Sign Up
- Log In

📄 **Footer:**
- company description
- address
- phone
- email

**Add item to cart**
- click on item icon on main page
- check opened page title an url 

🖼️ Image validation: detects broken or missing product images

💲 **Item Prices Validation**
- Prices are displayed correctly for all products
- No negative values (e.g., `-10$`)
- No missing or `null` prices

🛒 Cart navigation

## ▶️ Run Tests

```bash
# Chrome (default)
mvn test

# Firefox
mvn test -Dbrowser=firefox