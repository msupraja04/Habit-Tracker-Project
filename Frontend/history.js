showDateHistory();

function showDateHistory() {
  document.getElementById("historyControls").innerHTML = `
    <input type="date"
           id="historyDate">

    <button onclick="loadHistoryByDate()">
        View History
    </button>
    `;
}

function showWeeklyHistory() {
  document.getElementById("historyControls").innerHTML = `
    From :
    <input type="date"
           id="fromDate">

    To :
    <input type="date"
           id="toDate">

    <button onclick="loadWeeklyHistory()">
        View Weekly History
    </button>
    `;
}

function showMonthlyHistory() {
  document.getElementById("historyControls").innerHTML = `
    <input type="month"
           id="historyMonth">

    <button onclick="loadMonthlyHistory()">
        View Monthly History
    </button>
    `;
}

/*async function loadHistoryByDate()
{
    const date =
    document.getElementById(
        "historyDate"
    ).value;

    const response =
    await fetch(
    `https://habit-tracker-project-production.up.railway.app/tracking/history/${date}`);

    const history =
    await response.json();

    let totalPoints = 0;

    history.forEach(track =>
    {
        totalPoints += track.pointsEarned;
    });

    let output = `
    <div class="summary-card">

        <h2>Date : ${date}</h2>

        <p>
        Completed : ${history.length}
        </p>

        <p>
        Points : ${totalPoints}
        </p>

    </div>
    `;

    history.forEach(track =>
    {
        output +=
        `
        <div class="card">

            <h3>${track.habit.name}</h3>

            <p>
            ✅ Completed
            </p>

            <p>
            Points :
            ${track.pointsEarned}
            </p>

        </div>
        `;
    });

    document.getElementById(
        "historyList"
    ).innerHTML = output;
}*/

async function loadHistoryByDate() {
  const date = document.getElementById("historyDate").value;

  if (date === "") {
    alert("Please Select Date");
    return;
  }

  const userId = localStorage.getItem("userId");
  const response = await fetch(
    `https://habit-tracker-project-production.up.railway.app/tracking/history-summary/${userId}/${date}`,
  );

  const data = await response.json();

  let output = `
    <div class="summary-card">

        <h2>Date : ${date}</h2>

        <p>
        Total Habits :
        ${data.totalHabits}
        </p>

        <p>
        Completed :
        ${data.completedHabits}
        </p>

        <p>
        Pending :
        ${data.pendingHabits}
        </p>

        <p>
        Points Earned :
        ${data.totalPoints}
        </p>

    </div>

    <div class="card">

        <h3>
        ✅ Completed Habits
        </h3>
    `;

  data.completedHabitNames.forEach((habit) => {
    output += `<p>✅ ${habit}</p>`;
  });

  output += `
    </div>

    <div class="card">

        <h3>
        ❌ Pending Habits
        </h3>
    `;

  data.pendingHabitNames.forEach((habit) => {
    output += `<p>❌ ${habit}</p>`;
  });

  output += `
    </div>
    `;

  document.getElementById("historyList").innerHTML = output;
}

async function loadWeeklyHistory() {
  const start = document.getElementById("fromDate").value;

  const end = document.getElementById("toDate").value;

  if (start === "" || end === "") {
    alert("Please Select Dates");
    return;
  }

  const userId = localStorage.getItem("userId");
  const response = await fetch(
    `https://habit-tracker-project-production.up.railway.app/tracking/weekly-summary/${userId}?start=${start}&end=${end}`,
  );

  const data = await response.json();

  let output = `
    <div class="summary-card">

        <h2>📆 Weekly Summary</h2>

        <p>
        Date Range :
        ${start} → ${end}
        </p>

        <p>
        Total Habits :
        ${data.totalHabits}
        </p>

        <p>
        Completed :
        ${data.completedHabits}
        </p>

        <p>
        Pending :
        ${data.pendingHabits}
        </p>

        <p>
        Points :
        ${data.totalPoints}
        </p>

    </div>
    `;

  data.dailySummary.forEach((day) => {
    output += `
        <div class="card">
            <p>${day}</p>
        </div>
        `;
  });

  document.getElementById("historyList").innerHTML = output;
}

function toggleMenu() {
  document.getElementById("sidebar").classList.toggle("active");
}

function logoutUser() {
  localStorage.clear();

  window.location.href = "index.html";
}

async function loadMonthlyHistory() {
  const monthInput = document.getElementById("historyMonth").value;

  if (monthInput === "") {
    alert("Please Select Month");
    return;
  }

  const parts = monthInput.split("-");

  const year = parts[0];
  const month = parts[1];

  const userId = localStorage.getItem("userId");
  const response = await fetch(
    `https://habit-tracker-project-production.up.railway.app/tracking/monthly-summary/${userId}?year=${year}&month=${month}`,
  );

  const data = await response.json();

  let output = `
    <div class="summary-card">

        <h2>📅 Monthly Summary</h2>

        <p>
        Month :
        ${monthInput}
        </p>

        <p>
        Total Habits :
        ${data.totalHabits}
        </p>

        <p>
        Completed :
        ${data.completedHabits}
        </p>

        <p>
        Pending :
        ${data.pendingHabits}
        </p>

        <p>
        Points Earned :
        ${data.totalPoints}
        </p>

    </div>
    `;

  document.getElementById("historyList").innerHTML = output;
}
