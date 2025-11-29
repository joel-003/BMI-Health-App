<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BMI Health – Home</title>

    <link rel="stylesheet" href="./css/bmi-adult.css">
    <link rel="stylesheet" href="./css/index.css">
</head>

<body>

<!-- NAVBAR -->
<header class="navbar">
    <div class="nav-logo">
        <img src="./images/app-logo.png" class="logo-img" alt="Logo">
        <span class="logo-text">BMI Health</span>
    </div>

    <div class="hamburger">
        <span></span><span></span><span></span>
    </div>

    <nav class="nav-links">
        <a href="index.jsp">Home</a>
        <a href="bmr.jsp">BMR</a>
        <a href="bodyfat.jsp">Body Fat %</a>
        <a href="water.jsp">Water Intake</a>
        <a href="bmi-adult.jsp">BMI Calculator</a>
    </nav>
</header>


<!-- HERO SECTION -->
<section class="hero">
    <h1 class="hero-title">Find the Calculator That Matches Your<br>Next Health Goal</h1>

    <p class="hero-subtitle">
        Compare features and jump straight into the health or lifestyle calculator that fits your needs.
        Each tool is fast, free, and easy to use.
    </p>
</section>


<!-- SUGGESTION BOX -->
<section class="suggest-box">
    <h2>Not sure where to begin?</h2>

    <p class="suggest-text">
        Start with the BMI Calculator to understand your baseline, then explore other tools whenever you want
        deeper insights into your health.
    </p>

    <div class="suggest-btns">
        <a href="bmi-adult.jsp" class="primary-btn">Go to BMI Calculator</a>
        <a href="bmr.jsp" class="outline-btn">Go to BMR Calculator</a>
    </div>
</section>


<!-- CALCULATOR CARDS -->
<section class="cards-area">

    <!-- BMI -->
    <div class="calc-card">
        <h3>BMI Calculator</h3>
        <p>Measure your body mass index with metric or imperial units and instantly check your BMI category.</p>

        <ul>
            <li>Supports metric & imperial</li>
            <li>Immediate BMI results</li>
            <li>Perfect for weight tracking</li>
        </ul>

        <a href="bmi-adult.jsp" class="card-btn">Calculate BMI →</a>
    </div>

    <!-- BMR -->
    <div class="calc-card">
        <h3>BMR & TDEE Calculator</h3>
        <p>Estimate your basal metabolic rate and daily calorie needs using modern, accurate formulas.</p>

        <ul>
            <li>Mifflin–St Jeor formula</li>
            <li>Activity-based calorie targets</li>
            <li>Ideal for diet or fitness planning</li>
        </ul>

        <a href="bmr.jsp" class="card-btn">Calculate BMR →</a>
    </div>

    <!-- Body Fat -->
    <div class="calc-card">
        <h3>Body Fat % Calculator</h3>
        <p>Estimate your body fat percentage using the U.S. Navy method (waist, neck, hip, height).</p>

        <ul>
            <li>Male & Female formulas</li>
            <li>Instant body fat category</li>
            <li>Scientifically validated</li>
        </ul>

        <a href="bodyfat.jsp" class="card-btn">Calculate Body Fat →</a>
    </div>

    <!-- Water Intake -->
    <div class="calc-card">
        <h3>Water Intake Calculator</h3>
        <p>Find out how much water you need daily based on your weight and lifestyle activity.</p>

        <ul>
            <li>Weight-based formula</li>
            <li>Activity-adjusted output</li>
            <li>Improves hydration & health</li>
        </ul>

        <a href="water.jsp" class="card-btn">Calculate Water Intake →</a>
    </div>

</section>


<!-- FOOTER -->
<footer class="footer">
    <p>For educational purposes only. Always consult a healthcare professional.</p>
    <p><a href="https://github.com/joel-003" target="_blank">GitHub: joel-003</a></p>
</footer>

<script src="./javascript/navbar.js"></script>

</body>
</html>
