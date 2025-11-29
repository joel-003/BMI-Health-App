<%--
  Created by IntelliJ IDEA.
  User: Hp
  Date: 28-11-2025
  Time: 01:24 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Body Fat % Calculator</title>

    <link rel="stylesheet" href="./css/bmi-adult.css">
    <link rel="stylesheet" href="./css/bodyfat.css">
</head>

<body>

<!-- NAVBAR -->
<header class="navbar">
    <div class="nav-logo">
        <img src="./images/app-logo.png" class="logo-img" alt="Logo">
        <span class="logo-text">BMI Health</span>
    </div>

    <div class="hamburger">
        <span></span>
        <span></span>
        <span></span>
    </div>

    <nav class="nav-links">
        <a href="index.jsp">Home</a>
        <a href="#info">Info</a>
        <a href="#faq">FAQ</a>
    </nav>
</header>

<h1>Body Fat % Calculator</h1>
<p class="subtitle">Estimate your body fat percentage using the U.S. Navy Body Fat Formula.</p>

<div class="container">

    <!-- INPUT CARD -->
    <form class="card" action="bodyfat-calc" method="post">
        <div class="card-header gradient-blue">Enter Your Measurements</div>

        <!-- Gender -->
        <div class="input-group gender-section">
            <label>Gender</label>
            <div class="gender-radio">
                <label><input type="radio" name="gender" id="male" value="male" required> Male</label>
                <label><input type="radio" name="gender" id="female" value="female" required> Female</label>
            </div>

            <!-- HIP field (show only for female) -->
            <div class="input-group hip-input">
                <label for="hip">Hip (cm)</label>
                <input type="number" id="hip" name="hip" step="0.1">
            </div>
        </div>

        <!-- Height -->
        <div class="input-group">
            <label for="height">Height (cm)</label>
            <input type="number" id="height" name="height" step="0.1" required>
        </div>

        <!-- Waist -->
        <div class="input-group">
            <label for="waist">Waist (cm)</label>
            <input type="number" id="waist" name="waist" step="0.1" required>
        </div>

        <!-- Neck -->
        <div class="input-group">
            <label for="neck">Neck (cm)</label>
            <input type="number" id="neck" name="neck" step="0.1" required>
        </div>

        <button class="btn">Calculate Body Fat %</button>
    </form>

    <!-- RESULT CARD -->
    <div class="card result-card">
        <div class="card-header gradient-green">Your Body Fat Result</div>

        <% if (request.getAttribute("error") != null) { %>
        <div class="error-box">
            <%= request.getAttribute("error") %>
        </div>
        <% } %>

        <% if (request.getAttribute("bodyFatValue") != null) {
            String bodyFat = request.getAttribute("bodyFatValue").toString();
            String category = request.getAttribute("category").toString();
        %>

        <div class="bmi-value"><%= bodyFat %>%</div>
        <div class="bmi-status normal"><%= category %></div>

        <div class="note-box">
            <p><strong>Healthy Body Fat Categories (ACE):</strong></p>

            <table class="fat-table">
                <tr>
                    <th>Description</th>
                    <th>Women</th>
                    <th>Men</th>
                </tr>
                <tr>
                    <td>Essential Fat</td>
                    <td>10–13%</td>
                    <td>2–5%</td>
                </tr>
                <tr>
                    <td>Athletes</td>
                    <td>14–20%</td>
                    <td>6–13%</td>
                </tr>
                <tr>
                    <td>Fitness</td>
                    <td>21–24%</td>
                    <td>14–17%</td>
                </tr>
                <tr>
                    <td>Average</td>
                    <td>25–31%</td>
                    <td>18–24%</td>
                </tr>
                <tr>
                    <td>Obese</td>
                    <td>32%+</td>
                    <td>25%+</td>
                </tr>
            </table>
        </div>

        <% } else { %>
        <div class="bmi-value-if-null"><img src="./images/calc-no-value.png" class="bmi-icon" alt="no-result-img"></div>
        <div class="bmi-status-if-null">Enter measurements to calculate body fat %</div>
        <% } %>

    </div>
</div>

<!-- INFO SECTION -->
<div id="info" class="info-section">

    <h2>What Is Body Fat Percentage?</h2>
    <p>Body fat percentage is the amount of fat your body contains compared to your total weight.
        It includes both essential fat and storage fat. Essential fat is needed for normal body
        function, while storage fat is used for energy and protection.</p>

    <p>Having too much or too little body fat can affect your health. Body fat percentage is more
        accurate than BMI because it distinguishes between fat, muscle, and bone.</p>

    <h2>Why Body Fat % Matters</h2>
    <ul>
        <li>Helps assess fitness level</li>
        <li>Shows muscle-to-fat balance</li>
        <li>Indicates health risks</li>
        <li>Useful for weight-loss planning</li>
        <li>Tracks physical progress more accurately than BMI</li>
    </ul>

    <h2>How Body Fat Is Calculated (U.S. Navy Method – Metric)</h2>
    <p>This calculator uses the scientifically accepted U.S. Navy Body Fat Formula developed by
        Hodgdon and Beckett (1984). It uses simple circumference measurements:</p>

    <ul>
        <li><strong>Waist:</strong> At the navel (men), smallest waist point (women)</li>
        <li><strong>Neck:</strong> Below the larynx</li>
        <li><strong>Hip (women only):</strong> Widest hip circumference</li>
    </ul>

    <h3>Male Formula (Metric)</h3>
    <p>
        <code>
            BFP = 495 / (1.0324 − 0.19077 × log10(waist − neck) + 0.15456 × log10(height)) − 450
        </code>
    </p>

    <h3>Female Formula (Metric)</h3>
    <p>
        <code>
            BFP = 495 / (1.29579 − 0.35004 × log10(waist + hip − neck) + 0.22100 × log10(height)) − 450
        </code>
    </p>

    <p>These formulas provide a reliable estimate of body fat. For clinical accuracy, methods like
        DEXA scans or bioelectrical impedance devices are more precise.</p>

    <h2>Body Fat Categories</h2>
    <ul>
        <li><strong>Men:</strong> Essential (2–5%), Athletes (6–13%), Fitness (14–17%), Average (18–24%), Obese (25%+)</li>
        <li><strong>Women:</strong> Essential (10–13%), Athletes (14–20%), Fitness (21–24%), Average (25–31%), Obese (32%+)</li>
    </ul>

    <h2>Health Notes</h2>
    <p>A healthy range is typically <strong>10–20% for men</strong> and <strong>18–28% for women</strong>. Excess visceral fat
        (belly fat) may increase risk of heart disease, diabetes, and hormonal imbalance. Too little body fat
        may also affect energy, hormone levels, and overall health.</p>

    <h2 id="faq">Frequently Asked Questions (FAQs)</h2>

    <p><strong>Q: Is the Navy method accurate?</strong><br>
        It provides a good estimate. For medical-level accuracy, DEXA or impedance scans are recommended.</p>

    <p><strong>Q: Why does the female formula include the hip measurement?</strong><br>
        Women naturally store fat around hips and thighs, so this improves accuracy.</p>

    <p><strong>Q: How often should I check my body fat?</strong><br>
        Every 2–4 weeks is ideal. Daily changes are not meaningful.</p>

    <p><strong>Q: Can muscular people show high BMI but normal body fat?</strong><br>
        Yes. BMI can't differentiate muscle from fat, but body fat percentage can.</p>

    <p><strong>Q: Is low body fat unhealthy?</strong><br>
        Yes. Below essential fat levels can harm hormones, energy, and overall health.</p>

</div>


<footer class="footer">
    <p>For educational purposes only. Always consult a healthcare professional.</p>
    <p><a href="https://github.com/joel-003" target="_blank">GitHub: joel-003</a></p>
</footer>


<script>
    const maleRadio = document.getElementById("male");
    const femaleRadio = document.getElementById("female");
    const hipInput = document.querySelector(".hip-input");

    // hide hip initially
    hipInput.style.display = "none";

    maleRadio.addEventListener("change", () => {
        hipInput.style.display = "none";
    });

    femaleRadio.addEventListener("change", () => {
        hipInput.style.display = "block";
    });
</script>

<script src="./javascript/navbar.js"></script>
</body>
</html>

