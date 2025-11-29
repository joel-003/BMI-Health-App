<%--
  Created by IntelliJ IDEA.
  User: Hp
  Date: 26-11-2025
  Time: 06:54 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BMI Calculator</title>
    <link rel="stylesheet" href="./css/bmi-adult.css" />
</head>

<body>

<!-- NAVBAR -->
<!-- NAVBAR -->
<header class="navbar">
    <div class="nav-logo">
        <img src="./images/app-logo.png" class="logo-img" alt="Logo">
        <span class="logo-text">BMI Health</span>
    </div>

    <!-- Hamburger Button -->
    <div class="hamburger">
        <span></span>
        <span></span>
        <span></span>
    </div>

    <!-- Navigation Links -->
    <nav class="nav-links">
        <a href="index.jsp">Home</a>
        <a href="#how-to-use">How-to-Use</a>
        <a href="#faq">FAQ</a>
    </nav>
</header>



<h1>BMI Calculator</h1>
<p class="subtitle">Calculate your Body Mass Index instantly with our accurate and easy-to-use tool.</p>

<div class="container">

    <!-- INPUT CARD -->
    <form class="card" action="bmi-adult" method="post">
        <div class="card-header gradient-blue">Enter Your Details</div>
        <!-- Height input -->
        <div class="input-group">
            <label for="heightValue">Height</label>
            <div class="input-flex">
                <input type="number" id="heightValue" name="heightValue" step="0.01" required />
                <select name="heightUnit">
                    <option value="m">m</option>
                    <option value="cm">cm</option>
                    <option value="ft">ft</option>
                </select>
            </div>
        </div>

        <!-- Weight input -->
        <div class="input-group">
            <label for="weightValue">Weight</label>
            <div class="input-flex">
                <input type="number" id="weightValue" name="weightValue" step="0.1" required />
                <select name="weightUnit">
                    <option value="kg">kg</option>
                    <option value="lb">lb</option>
                </select>
            </div>
        </div>

        <button class="btn">Calculate BMI</button>
    </form>

    <!-- RESULT CARD -->
    <div class="card">
        <div class="card-header gradient-green">Your BMI Result</div>

        <% if (request.getAttribute("error") != null) { %>
        <div class="error-box">
            <%= request.getAttribute("error") %>
        </div>
        <% } %>

        <%
            if (request.getAttribute("bmi") != null) {
                String category = (String) request.getAttribute("category");
                String categoryClass = "";

                if ("Underweight".equals(category)) {
                    categoryClass = "underweight";
                } else if ("Normal".equals(category)) {
                    categoryClass = "normal";
                } else if ("Overweight".equals(category)) {
                    categoryClass = "overweight";
                } else if ("Obese".equals(category)) {
                    categoryClass = "obese";
                }

                String bmi = (String) request.getAttribute("bmi");
                String[] tips = (String[]) request.getAttribute("tips");
        %>

        <div class="bmi-value"><%= bmi %></div>
        <div class="bmi-status <%= categoryClass %>"><%= category %></div>

        <div class="note-box">
            <ul>
                <% if (tips != null) {
                    for (String t : tips) { %>
                <li class="bmi-scale"><%= t %></li>
                <% } } %>
            </ul>
        </div>

        <% } else { %>

        <!-- Default message before submission -->
        <div class="bmi-value-if-null "><img src="./images/calc-no-value.png" alt="calc" class="bmi-icon"></div>
        <div class="bmi-status-if-null">Enter your height and weight to see your BMI result</div>
<%--        <div class="note-box">💬</div>--%>

        <% } %>
    </div>
</div>

<!-- INFO SECTION -->
<div class="info-section">

    <!-- Why Use -->
    <h2>Why Use Our BMI Calculator?</h2>
    <ul>
        <li><strong>Accurate Results:</strong> Get precise BMI calculations in seconds.</li>
        <li><strong>Interactive Inputs:</strong> Enter your height, weight for personalized results.</li>
        <li><strong>BMI Categories:</strong> Understand your BMI category—underweight, normal, overweight, or obese.</li>
        <li><strong>Free and Easy to Use:</strong> No hidden charges or complicated steps.</li>
        <li><strong>Health Awareness:</strong> Track your health and fitness goals effectively.</li>
    </ul>

    <!-- How to Use -->
    <h2 id="how-to-use">How to Use the BMI Calculator</h2>
    <ol>
        <li>Set Your Height: Input in feet and inches.</li>
        <li>Enter Your Weight: Input in kilograms.</li>
        <li>Click “Calculate BMI” to see your score and category.</li>
        <li>View Results: Understand your health status instantly.</li>
    </ol>

    <!-- Benefits -->
    <h2>Benefits of Using a BMI Calculator</h2>
    <ul>
        <li><strong>Health Monitoring:</strong> Track your BMI regularly to stay informed.</li>
        <li><strong>Goal Setting:</strong> Use results to set realistic fitness goals.</li>
        <li><strong>Quick and Convenient:</strong> Instant results without manual math.</li>
        <li><strong>Universal Standard:</strong> Based on WHO guidelines.</li>
        <li><strong>Visual Feedback:</strong> Understand your BMI category at a glance.</li>
    </ul>

    <!-- FAQs -->
    <h2 id="faq">Frequently Asked Questions (FAQs)</h2>
    <p><strong>Q: What is BMI?</strong><br>
        A: BMI stands for Body Mass Index. It measures body fat based on weight and height.</p>

    <p><strong>Q: How accurate is the BMI Calculator?</strong><br>
        A: It uses the standard formula (kg/m²). It doesn’t account for muscle mass or body composition.</p>

    <p><strong>Q: Can I use the BMI Calculator for children?</strong><br>
        A: No, ages 20+. Interpretation differs slightly for children.</p>

    <p><strong>Q: What is a healthy BMI range?</strong><br>
        A: 18.5–24.9 is healthy. Below 18.5 = underweight, 25–29.9 = overweight, 30+ = obese.</p>

    <p><strong>Q: How often should I check my BMI?</strong><br>
        A: Periodically, especially if working on fitness or weight management.</p>

    <!-- Call to Action -->
    <h2>Start Tracking Your Health Today</h2>
    <p>Whether you’re aiming to lose weight, gain muscle, or maintain a healthy lifestyle,
        our BMI Calculator helps you calculate your BMI, understand your health status,
        and take the first step toward your fitness goals. Stay informed, stay healthy!</p>

    <p><em>For official BMI classifications, refer to
        <a href="https://www.who.int/news-room/fact-sheets/detail/obesity-and-overweight" target="_blank">the World Health Organization (WHO) BMI Chart</a>
    </em></p>
</div>

<!-- FOOTER -->
<footer class="footer">
    <p>For educational purposes only. Always consult a healthcare professional.</p>
    <p>
        <a href="https://github.com/joel-003" target="_blank">GitHub: joel-003</a>
    </p>
</footer>

<script src="./javascript/navbar.js"></script>

</body>
</html>
