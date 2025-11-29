<%--
  Created by IntelliJ IDEA.
  User: Hp
  Date: 28-11-2025
  Time: 06:04 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Water Intake Calculator</title>

    <link rel="stylesheet" href="./css/bmi-adult.css">
    <link rel="stylesheet" href="./css/water.css">
</head>

<body>

<!-- NAVBAR -->
<header class="navbar">
    <div class="nav-logo">
        <img src="./images/app-logo.png" class="logo-img" alt="logo">
        <span class="logo-text">BMI Health</span>
    </div>

    <div class="hamburger">
        <span></span><span></span><span></span>
    </div>

    <nav class="nav-links">
        <a href="index.jsp">Home</a>
        <a href="#info">Info</a>
        <a href="#faq">FAQ</a>
    </nav>
</header>


<h1>Water Intake Calculator</h1>
<p class="subtitle">Calculate your ideal daily water requirement based on your weight and activity level.</p>

<div class="container">

    <!-- INPUT CARD -->
    <form class="form-card" action="water-calc" method="post">
        <div class="card-header gradient-blue">Enter Your Details</div>

        <!-- Weight -->
        <div class="input-group">
            <label for="weight">Weight (kg)</label>
            <input type="number" id="weight" name="weight" step="0.1" required>
        </div>

        <!-- Activity level -->
        <div class="input-group">
            <label for="activity">Activity Level</label>
            <select id="activity" name="activity" required>
                <option value="0">Sedentary (Little exercise)</option>
                <option value="0.3">Light Activity (1–2 days/week)</option>
                <option value="0.7">Moderate Activity (3–5 days/week)</option>
                <option value="1">Heavy Activity (6–7 days/week)</option>
            </select>
        </div>

        <button class="btn">Calculate Water Intake</button>
    </form>


    <!-- RESULT CARD -->
    <div class="form-card result-card">
        <div class="card-header gradient-green">Your Water Requirement</div>

        <% if (request.getAttribute("error") != null) { %>
        <div class="error-box">
            <%= request.getAttribute("error") %>
        </div>
        <% } %>

        <% if (request.getAttribute("waterLiters") != null) { %>

        <div class="bmi-value"><%= request.getAttribute("waterLiters") %> L/day</div>
        <div class="bmi-status normal">Recommended Daily Intake</div>

        <div class="note-box">
            <p><strong>Tip:</strong> Staying hydrated improves metabolism, skin health, digestion, and energy levels.</p>
        </div>

        <% } else { %>

        <div class="bmi-value-if-null">
            <img src="./images/calc-no-value.png" class="bmi-icon" alt="no-result-img">
        </div>
        <div class="bmi-status-if-null">Enter your details to calculate water intake</div>

        <% } %>
    </div>
</div>


<!-- INFO SECTION -->
<div id="info" class="info-section">

    <h2>Daily Water Intake – Why It Matters</h2>
    <p>
        Your body is made of nearly <strong>60% water</strong>, and staying hydrated is essential for energy,
        digestion, brain function, temperature regulation, and overall health. Instead of following the outdated
        “8 glasses a day” rule, this calculator gives you a <strong>personalized daily water intake</strong> based on
        your weight, activity level, and climate.
    </p>

    <h2>How the Calculator Works</h2>
    <p>This calculator estimates your ideal daily water intake using scientifically supported hydration factors:</p>

    <ul>
        <li><strong>Body Weight:</strong> Larger bodies require more water for metabolic processes.</li>
        <li><strong>Activity Level:</strong> More activity means more sweat → higher water need.</li>
        <li><strong>Climate:</strong> Hot or humid environments increase hydration requirements.</li>
        <li><strong>Age & Gender:</strong> Hydration needs vary slightly between men and women.</li>
        <li><strong>Special Conditions:</strong> Pregnancy and breastfeeding require extra fluids.</li>
    </ul>

    <h2>Formula Used</h2>
    <ul>
        <li><strong>Base Daily Water:</strong> weight × 35 ml</li>
        <li><strong>Activity Add-ons:</strong>
            <ul>
                <li>Light Activity: +0.3 L</li>
                <li>Moderate Activity: +0.7 L</li>
                <li>Heavy Activity: +1.0 L</li>
            </ul>
        </li>
        <li><strong>Climate Adjustments:</strong>
            <ul>
                <li>Hot/Humid Weather: +0.5 to 1 L</li>
                <li>Cold Weather: +0.2 L (thirst decreases but needs remain)</li>
            </ul>
        </li>
        <li><strong>Pregnancy:</strong> +300 ml/day</li>
        <li><strong>Breastfeeding:</strong> +600–700 ml/day</li>
    </ul>

    <h2>Why Hydration Is Important</h2>
    <p>Proper hydration supports:</p>

    <ul>
        <li>Transport of nutrients</li>
        <li>Temperature regulation</li>
        <li>Joint lubrication</li>
        <li>Healthy skin</li>
        <li>Kidney function & detoxification</li>
        <li>Brain performance and concentration</li>
    </ul>

    <p>
        Fun fact: The <strong>brain and heart are 73% water</strong>, lungs are 83%, skin is 64%, and even bones contain 31% water.
    </p>

    <h2>Dehydration Risks</h2>
    <p>Lack of proper water intake can lead to:</p>

    <ul>
        <li>Headaches and fatigue</li>
        <li>Dizziness and poor concentration</li>
        <li>Constipation</li>
        <li>Dry skin & cracked lips</li>
        <li>Reduced urine output</li>
        <li>Heat exhaustion or heatstroke (severe cases)</li>
    </ul>

    <h2>Tips for Staying Hydrated</h2>
    <ul>
        <li>Drink water consistently throughout the day.</li>
        <li>Consume water-rich foods like fruits and vegetables.</li>
        <li>Limit sugary drinks and excessive caffeine.</li>
        <li>Increase intake during exercise or hot weather.</li>
    </ul>

    <h2 id="faq">Frequently Asked Questions</h2>

    <p><strong>Q: Do coffee or tea count as water?</strong><br>
        Yes. They contribute to hydration, but excessive caffeine may slightly increase urination.
    </p>

    <p><strong>Q: Can I drink too much water?</strong><br>
        Yes. Overhydration can cause low sodium levels (hyponatremia). Drink gradually throughout the day.
    </p>

    <p><strong>Q: Do I need more water during workouts?</strong><br>
        Absolutely. Increase intake before, during, and after exercise to replace sweat loss.
    </p>

    <p><strong>Q: Is this calculator suitable for children?</strong><br>
        No. Hydration needs for children vary by age and should follow pediatric recommendations.
    </p>

    <p><strong>Q: What are signs I need to drink more water?</strong><br>
        Dry mouth, yellow urine, fatigue, headaches, or low energy commonly indicate dehydration.
    </p>

</div>



<!-- FOOTER -->
<footer class="footer">
    <p>For educational purposes only. Always consult a healthcare professional.</p>
    <p><a href="https://github.com/joel-003" target="_blank">GitHub: joel-003</a></p>
</footer>

<script src="./javascript/navbar.js"></script>
</body>
</html>

