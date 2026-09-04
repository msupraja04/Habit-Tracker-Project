let currentMonth = new Date().getMonth();

let currentYear = new Date().getFullYear();
showDateStats();

function showDateStats() {
  document.getElementById("statsControls").innerHTML = `
    <input type="date"
           id="statsDate">

    <button onclick="loadDateStats()">
        View Statistics
    </button>
    `;
}
async function loadDateStats() {
  const date = document.getElementById("statsDate").value;

  const userId = localStorage.getItem("userId");
  const response = await fetch(
    `https://habit-tracker-project-production.up.railway.app/tracking/statistics/date/${userId}/${date}`,
  );

  const data = await response.json();

  document.getElementById("statsResult").innerHTML = `
    <div class="card">

        <h2>
        Selected Date :
        ${date}
        </h2>

        <p>
        Completed :
        ${data.completed}
        </p>

        <p>
        Pending :
        ${data.pending}
        </p>

        <p>
        Points :
        ${data.points}
        </p>

        <p>
        Average Points :
        ${data.averagePoints.toFixed(1)}
        </p>

        <p>
        Progress :
        ${data.progress.toFixed(1)}%
        </p>

    </div>

    <div class="progress-bar">
        <div class="progress-fill"
        style="width:${data.progress}%">
        </div>
    </div>
    `;
  document.getElementById("chartContainer").innerHTML = `
<div class="chart-card">
    <h3>Progress</h3>

    <div class="progress-bar">
        <div class="progress-fill"
        style="width:${data.progress}%">
        </div>
    </div>

    <h3>${data.progress.toFixed(1)}%</h3>
</div>
`;

  new Chart(document.getElementById("statsChart"), {
    type: "doughnut",
    data: {
      labels: ["Completed", "Pending"],
      datasets: [
        {
          data: [data.completed, data.pending],
        },
      ],
    },
  });
}

function toggleMenu() {
  document.getElementById("sidebar").classList.toggle("active");
}

function logoutUser() {
  localStorage.clear();
  window.location.href = "index.html";
}

function showWeeklyStats() {
  document.getElementById("statsControls").innerHTML = `
    From :
    <input type="date"
           id="fromDate">

    To :
    <input type="date"
           id="toDate">

    <button onclick="loadWeeklyStats()">
        View Weekly Statistics
    </button>
    `;
}

async function loadWeeklyStats() {
  const start = document.getElementById("fromDate").value;

  const end = document.getElementById("toDate").value;

  const userId = localStorage.getItem("userId");
  const response = await fetch(
    `https://habit-tracker-project-production.up.railway.app/tracking/statistics/weekly/${userId}?start=${start}&end=${end}`,
  );

  const data = await response.json();

  document.getElementById("statsResult").innerHTML = `
    <div class="card">

        <h2>📆 Weekly Statistics</h2>

        <p>Completed : ${data.completed}</p>

        <p>Pending : ${data.pending}</p>

        <p>Points : ${data.points}</p>

        <p>
        Average Per Day :
        ${data.averagePerDay.toFixed(1)}
        </p>

        <p>
        Progress :
        ${data.progress.toFixed(1)}%
        </p>

    </div>

    <div class="progress-bar">
        <div class="progress-fill"
        style="width:${data.progress}%">
        </div>
    </div>
    `;
  document.getElementById("chartContainer").innerHTML = `
<div class="chart-card">

<h3>Weekly Completion Trend</h3>

<p>Mon : 2</p>
<p>Tue : 4</p>
<p>Wed : 3</p>
<p>Thu : 5</p>
<p>Fri : 2</p>
<p>Sat : 4</p>
<p>Sun : 2</p>

<div class="progress-bar">
<div class="progress-fill"
style="width:${data.progress}%">
</div>
</div>

</div>
`;

  document.getElementById("chartContainer").innerHTML = `
<canvas id="weeklyChart"></canvas>
`;

  new Chart(document.getElementById("weeklyChart"), {
    type: "bar",
    data: {
      labels: ["Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"],
      datasets: [
        {
          label: "Completed Habits",
          data: data.dailyCounts,
          backgroundColor: "#36A2EB",
        },
      ],
    },
    options: {
      responsive: true,
      scales: {
        y: {
          beginAtZero: true,
        },
      },
    },
  });

  localStorage.setItem("statsTab", "weekly");
}

function showMonthlyStats() {
  document.getElementById("statsControls").innerHTML = `
    <input type="month"
           id="statsMonth">

    <button onclick="loadMonthlyStats()">
        View Monthly Statistics
    </button>
    `;
}
async function loadMonthlyStats() {
  const value = document.getElementById("statsMonth").value;

  const parts = value.split("-");

  const year = parts[0];

  const month = parts[1];

  const userId = localStorage.getItem("userId");
  const response = await fetch(
    `https://habit-tracker-project-production.up.railway.app/tracking/statistics/monthly/${userId}?year=${year}&month=${month}`,
  );

  const data = await response.json();

  document.getElementById("statsResult").innerHTML = `
    <div class="card">

        <h2>
        📅 Monthly Statistics
        </h2>

        <p>
        Completed :
        ${data.completed}
        </p>

        <p>
        Pending :
        ${data.pending}
        </p>

        <p>
        Points :
        ${data.points}
        </p>

        <p>
        Highest Streak :
        ${data.highestStreak}
        </p>

        <p>
        Average Daily Points :
        ${data.averageDailyPoints.toFixed(1)}
        </p>

        <p>
        Progress :
        ${data.progress.toFixed(1)}%
        </p>

    </div>

    <div class="progress-bar">
        <div class="progress-fill"
        style="width:${data.progress}%">
        </div>
    </div>
    `;
  document.getElementById("chartContainer").innerHTML = `
<div class="chart-card">

<h3>Monthly Trend</h3>

<p>Week 1 : 120 Points</p>
<p>Week 2 : 180 Points</p>
<p>Week 3 : 210 Points</p>
<p>Week 4 : 240 Points</p>

<div class="progress-bar">
<div class="progress-fill"
style="width:${data.progress}%">
</div>
</div>

</div>
`;

  document.getElementById("chartContainer").innerHTML = `
<canvas id="monthlyChart"></canvas>
`;

  new Chart(document.getElementById("monthlyChart"), {
    type: "line",
    data: {
      labels: ["Week 1", "Week 2", "Week 3", "Week 4", "Week 5"],
      datasets: [
        {
          label: "Points",
          data: data.weeklyPoints,
          borderColor: "#36A2EB",
          tension: 0.4,
          fill: false,
        },
      ],
    },
    options: {
      responsive: true,
    },
  });

  localStorage.setItem("statsTab", "monthly");
}

function showOverallStats() {
  loadOverallStats();
}
async function loadOverallStats() {
  const userId = localStorage.getItem("userId");
  const response = await fetch(
    `https://habit-tracker-project-production.up.railway.app/tracking/statistics/overall/${userId}`,
  );

  const data = await response.json();

  document.getElementById("statsResult").innerHTML = `
    <div class="card">

        <h2>
        🌍 Overall Statistics
        </h2>

        <p>
        Total Habits created :
        ${data.totalHabits}
        </p>

        <p>
        Total Completions :
        ${data.totalCompletions}
        </p>

        <p>
        Total Points :
        ${data.totalPoints}
        </p>

        <p>
        Current Streak :
        ${data.currentStreak}
        </p>

        <p>
        Highest Streak :
        ${data.highestStreak}
        </p> 

        <p>
Completion Rate :
${data.completionRate.toFixed(1)}%
</p>
        <p>
        Average Points :
        ${data.averagePointsPerCompletion.toFixed(1)}
        </p>

    </div>

        `;
  document.getElementById("chartContainer").innerHTML = `
<div class="chart-card">
   <canvas id="statsChart"></canvas>
</div>
`;

  new Chart(document.getElementById("statsChart"), {
    type: "doughnut",

    data: {
      labels: ["Completed %", "Remaining %"],

      datasets: [
        {
          data: [data.completionRate, 100 - data.completionRate],
        },
      ],
    },

    options: {
      responsive: true,
    },
  });

  localStorage.setItem("statsTab", "overall");
}

window.onload = function () {
  const tab = localStorage.getItem("statsTab");

  if (tab === "weekly") {
    showWeeklyStats();
  } else if (tab === "monthly") {
    showMonthlyStats();
  } else if (tab === "overall") {
    showOverallStats();
  } else {
    showDateStats();
  }
};

async function loadHeatmap() {
  const userId = localStorage.getItem("userId");
  const response = await fetch(
    `https://habit-tracker-project-production.up.railway.app/tracking/heatmap/${userId}`,
  );

  const data = await response.json();

  let output = "";

  data.forEach((day) => {
    output += `
        <div
        class="heatmap-box completed-box"
        title="${day.date}">
        </div>
        `;
  });

  document.getElementById("heatmapContainer").innerHTML = output;
}

loadHeatmap();

function previousMonth() {
  currentMonth--;

  if (currentMonth < 0) {
    currentMonth = 11;
    currentYear--;
  }

  loadCalendar();
}

function nextMonth() {
  currentMonth++;

  if (currentMonth > 11) {
    currentMonth = 0;
    currentYear++;
  }

  loadCalendar();
}

async function loadCalendar() {
  const monthNames = [
    "January",
    "February",
    "March",
    "April",
    "May",
    "June",
    "July",
    "August",
    "September",
    "October",
    "November",
    "December",
  ];

  document.getElementById("monthYear").innerHTML =
    monthNames[currentMonth] + " " + currentYear;

  const userId = localStorage.getItem("userId");
  const response = await fetch(
    `https://habit-tracker-project-production.up.railway.app/tracking/calendar/${userId}/${currentYear}/${currentMonth + 1}`,
  );

  const calendarData = await response.json();

  const daysInMonth = new Date(currentYear, currentMonth + 1, 0).getDate();

  let output = "";

  window.calendarData = calendarData;

  for (let day = 1; day <= daysInMonth; day++) {
    const currentDay = calendarData.find(
      (d) => new Date(d.date).getDate() === day,
    );

    const today = new Date();
    today.setHours(0, 0, 0, 0);

    let className = "future";

    if (currentDay) {
      const thisDate = new Date(currentDay.date);
      thisDate.setHours(0, 0, 0, 0);

      if (thisDate > today) {
        className = "future";
      } else if (currentDay.completedHabits == 0) {
        className = "missed";
      } else if (currentDay.completedHabits == currentDay.totalHabits) {
        className = "completed";
      } else {
        className = "partial";
      }
    }

    let formattedDate = "";

    if (currentDay) {
      formattedDate = new Date(currentDay.date).toLocaleDateString("en-US", {
        year: "numeric",
        month: "long",
        day: "numeric",
      });
    }

    let tooltipContent = "";

    if (currentDay) {
      const thisDate = new Date(currentDay.date);
      thisDate.setHours(0, 0, 0, 0);

      const today = new Date();
      today.setHours(0, 0, 0, 0);

      if (thisDate > today) {
        tooltipContent = `
<b>📅 ${formattedDate}</b>

<br><br>

⏳ Future Date

<br><br>

No data available yet.
`;
      } else {
        // tooltipContent = `
        // <b>📅 ${formattedDate}</b>

        // <br><br>

        // ✅ Completed :
        // ${currentDay.completedHabits}/${currentDay.totalHabits}

        // <br>

        // 📈 Completion :
        // ${currentDay.completionRate.toFixed(1)}%

        // <br>

        // ⭐ Points :
        // ${currentDay.pointsEarned}
        // `;
        tooltipContent = `
<b>📅 ${formattedDate}</b>

<br><br>

✅ Completed :
${currentDay.completedHabits}/${currentDay.totalHabits}

<br><br>

📈 Completion :
${currentDay.completionRate.toFixed(1)}%

<br><br>

⭐ Points :
${currentDay.pointsEarned}

<br><br>

${currentDay.streakMaintained ? "🔥 Streak Maintained" : "⚠️ Streak Broken"}

<br><br>

<b>👆 Click to view habits</b>
`;
      }
    }
    output += `
<div class="day calendar-day ${className}"
     onclick="openDayDetails('${currentDay ? currentDay.date : ""}')">

    <span>${day}</span>

    <div class="tooltip">
        ${tooltipContent}
    </div>

</div>
`;
  }

  document.getElementById("calendarGrid").innerHTML = output;
}

loadCalendar();

function closeModal() {
  document.getElementById("habitModal").style.display = "none";
}

window.onclick = function (event) {
  const modal = document.getElementById("habitModal");

  if (event.target == modal) {
    modal.style.display = "none";
  }
};

function openDayDetails(date) {
  const day = window.calendarData.find((d) => d.date === date);
  if (!day) {
    return;
  }

  let completed = "";

  day.completedHabitNames.forEach((h) => {
    completed += `<li>✅ ${h}</li>`;
  });

  let pending = "";

  day.pendingHabitNames.forEach((h) => {
    pending += `<li>❌ ${h}</li>`;
  });

  document.getElementById("modalBody").innerHTML = `
<h2>
📅 ${new Date(day.date).toLocaleDateString("en-US", {
    year: "numeric",
    month: "long",
    day: "numeric",
  })}
</h2>

<h3>
Completed Habits
</h3>

<ul>

${completed}

</ul>

<hr>

<h3>
Pending Habits
</h3>

<ul>

${pending}

</ul>

<hr>

<p>

<b>
Completion :
</b>

${day.completedHabits}
/
${day.totalHabits}

</p>

<p>

<b>
Completion Rate :
</b>

${day.completionRate.toFixed(1)}%

</p>

<p>

<b>
⭐ Points :
</b>

${day.pointsEarned}

</p>

<p>

${day.streakMaintained ? "🔥 Streak Maintained" : "⚠️ Streak Broken"}

</p>
`;

  document.getElementById("habitModal").style.display = "block";
}
