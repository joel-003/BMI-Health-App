<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BMR Calculator</title>
    <link rel="stylesheet" href="./css/bmi-adult.css" />
    <link rel="stylesheet" href="./css/bmr.css" />
</head>

<body>

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
        <a href="#info">Info</a>
        <a href="#faq">FAQ</a>
    </nav>
</header>


<h1>BMR Calculator</h1>
<p class="subtitle">Calculate how many calories your body burns at rest (Basal Metabolic Rate).</p>

<div class="container">

    <!-- INPUT CARD -->
    <form class="card" action="bmr-calc" method="post">
        <div class="card-header gradient-blue">Enter Your Details</div>

        <!-- Age -->
        <div class="input-group short-input">
            <label for="age">Age</label>
            <input type="number" id="age" name="age" required min="15" max="100"/>
        </div>

        <!-- Gender -->
        <div class="input-group">
            <label>Gender</label>
            <div class="gender-radio">
                <label><input type="radio" name="gender" value="male" required> Male</label>
                <label><input type="radio" name="gender" value="female" required> Female</label>
            </div>
        </div>

        <!-- Height -->
        <div class="input-group">
            <label for="heightValue">Height</label>
            <div class="input-flex">
                <input type="number" id="heightValue" name="heightValue" step="0.01" required />
                <select name="heightUnit">
                    <option value="cm">cm</option>
                    <option value="m">m</option>
                    <option value="ft">ft</option>
                </select>
            </div>
        </div>

        <!-- Weight -->
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

        <!-- Activity Level -->
        <div class="input-group">
            <label for="activity">Activity Level</label>
            <select id="activity" name="activity" required>
                <option value="1.2">Sedentary (Little or no exercise)</option>
                <option value="1.375">Light Activity (1–3 days/week)</option>
                <option value="1.55">Moderate (3–5 days/week)</option>
                <option value="1.725">Very Active (6–7 days/week)</option>
                <option value="1.9">Extra Active (Intense training)</option>
            </select>
        </div>

        <button class="btn">Calculate BMR</button>
    </form>


    <!-- RESULT CARD -->
    <div class="card result-card">
        <div class="card-header gradient-green">Your BMR Result</div>

        <% if (request.getAttribute("error") != null) { %>
        <div class="error-box">
            <%= request.getAttribute("error") %>
        </div>
        <% } %>

        <% if (request.getAttribute("bmrValue") != null) {
            String bmr = request.getAttribute("bmrValue").toString();
            String tdeeStr = request.getAttribute("tdee").toString();
            int tdee = Integer.parseInt(tdeeStr);
        %>

        <div class="bmi-value"><%= bmr %> kcal/day</div>
        <div class="bmi-status normal">Calories your body burns at rest</div>

        <div class="tdee-table">
            <div class="tdee-row">
                <span>Maintain weight</span>
                <span class="cal"><%= tdee %> kcal/day</span>
            </div>

            <div class="tdee-row">
                <span>Mild weight loss (0.25 kg/week)</span>
                <span class="cal"><%= tdee - 250 %> kcal/day</span>
            </div>

            <div class="tdee-row">
                <span>Weight loss (0.5 kg/week)</span>
                <span class="cal"><%= tdee - 500 %> kcal/day</span>
            </div>

            <div class="tdee-row">
                <span>Extreme weight loss (1 kg/week)</span>
                <span class="cal extreme"><%= Math.max(tdee - 1000, 0) %> kcal/day</span>
            </div>
        </div>

        <% } else { %>
        <div class="bmi-value-if-null"><img src="./images/calc-no-value.png" class="bmi-icon" alt="no result image"/></div>
        <div class="bmi-status-if-null">Enter your details to see your BMR</div>
        <% } %>
    </div>
</div>


<!-- INFO SECTION -->
<div class="info-section" id="info">
    <h2>What is BMR?</h2>
    <p>The basal metabolic rate (BMR) is the amount of energy needed while resting in a temperate environment
        when the digestive system is inactive. It is the equivalent of figuring out how much gas an idle car
        consumes while parked. In such a state, energy will be used only to maintain vital organs, which include
        the heart, brain, kidneys, nervous system, intestines, liver, lungs, sex organs, muscles, and skin.
        For most people, upwards of ~70% of total energy (calories) burned each day is due to upkeep. Physical activity
        makes up ~20% of expenditure and ~10% is used for the digestion of food, also known as thermogenesis.

        The BMR is measured under very restrictive circumstances while awake. An accurate BMR measurement
        requires that a person's sympathetic nervous system is inactive, which means the person must be completely
        rested. Basal metabolism is usually the largest component of a person's total caloric needs. The daily caloric
        need is the BMR value multiplied by a factor with a value between 1.2 and 1.9, depending on activity level.</p>

    <h2>How is BMR Calculated?</h2>
    <ul>
        <li><strong>Men:</strong> (10 × weight kg) + (6.25 × height cm) – (5 × age) + 5</li>
        <li><strong>Women:</strong> (10 × weight kg) + (6.25 × height cm) – (5 × age) – 161</li>
    </ul>

    <!-- How to Use -->
    <h2>How to Use the BMR Calculator</h2>
    <ol>
        <li><strong>Enter Your Age:</strong> Provide your current age in years.</li>
        <li><strong>Select Gender:</strong> Choose Male or Female. BMR formula differs slightly.</li>
        <li><strong>Enter Height:</strong> Provide height in centimeters, meters, or feet.</li>
        <li><strong>Enter Weight:</strong> Provide weight in kilograms or pounds.</li>
        <li><strong>Select Activity Level:</strong> Choose how active you are weekly.</li>
        <li><strong>Click “Calculate BMR”:</strong> Your BMR and TDEE values will be displayed instantly.</li>
        <li><strong>Review the Calorie Table:</strong> Use the recommended daily calorie targets for maintaining or losing weight.</li>
    </ol>

    <!-- What is TDEE -->
    <h2>What is TDEE?</h2>
    <ul>
        <li><strong>Total Daily Energy Expenditure (TDEE)</strong> is the total number of calories your body burns in a full day.</li>
        <li>TDEE includes:
            <ul>
                <li>Calories burned at rest (BMR)</li>
                <li>Movement</li>
                <li>Daily activities</li>
                <li>Exercise</li>
            </ul>
        </li>
        <li><strong>TDEE = BMR × Activity Level</strong></li>
        <li>TDEE helps you understand:
            <ul>
                <li>How many calories you can eat to maintain weight</li>
                <li>How many calories to reduce for safe weight loss</li>
                <li>How many calories to increase to gain weight</li>
            </ul>
        </li>
        <li>TDEE is widely used in fitness, diet planning, and health monitoring apps.</li>
    </ul>

    <!-- FAQs -->
    <h2 id="faq">Frequently Asked Questions (FAQs)</h2>

    <p><strong>Q: What is the difference between BMR and TDEE?</strong><br>
        <strong>BMR</strong> is the calories your body needs at rest.
        <strong>TDEE</strong> is the total calories you burn in a day including activity.</p>

    <p><strong>Q: Is BMR different for men and women?</strong><br>
        Yes. Women naturally have a lower BMR due to differences in body composition.</p>

    <p><strong>Q: Why is my TDEE higher than my BMR?</strong><br>
        Because TDEE includes walking, exercise, activity, and daily movements.</p>

    <p><strong>Q: How often should I check my BMR/TDEE?</strong><br>
        Check it when your weight, activity level, or health goals change.</p>

    <p><strong>Q: Can TDEE help with weight loss?</strong><br>
        Yes. Eating 300–500 calories below TDEE helps you lose weight safely.</p>

    <p><strong>Q: Is the BMR formula accurate?</strong><br>
        We use the Mifflin–St Jeor equation, the most accurate and widely used formula.</p>

</div>

<!-- FOOTER -->
<footer class="footer">
    <p>For educational purposes only. Always consult a healthcare professional.</p>
    <p><a href="https://github.com/joel-003" target="_blank">GitHub: joel-003</a></p>
</footer>

<script src="javascript/navbar.js"></script>
</body>
</html>
