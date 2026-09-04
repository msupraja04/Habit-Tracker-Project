async function loadDashboard() {
  document.getElementById("welcomeUser").innerHTML =
    "Welcome " + localStorage.getItem("userName") + " 👋";
  const userId = localStorage.getItem("userId");

  const response = await fetch(
    `https://habit-tracker-project-production.up.railway.app/tracking/dashboard/${userId}`,
  );

  const data = await response.json();

  document.getElementById("totalHabits").innerHTML = data.totalHabits;

  document.getElementById("completedHabits").innerHTML = data.completedHabits;

  document.getElementById("pendingHabits").innerHTML = data.pendingHabits;

  document.getElementById("pointsToday").innerHTML = data.pointsToday;

  document.getElementById("currentStreak").innerHTML = data.currentStreak;
  loadHeroCard();
}

loadDashboard();
loadChallenges();

async function loadChallenges() {
  const userId = localStorage.getItem("userId");
  const response = await fetch(
    `https://habit-tracker-project-production.up.railway.app/tracking/challenges/${userId}`,
  );

  const data = await response.json();

  document.getElementById("dailyCount").innerHTML =
    data.dailyCompleted + " / " + data.dailyTarget;

  document.getElementById("weeklyCount").innerHTML =
    data.weeklyCompleted + " / " + data.weeklyTarget;

  document.getElementById("monthlyCount").innerHTML =
    data.monthlyCompleted + " / " + data.monthlyTarget;

  document.getElementById("weeklyTarget").innerHTML =
    "Complete " + data.weeklyTarget + " habits this week";

  document.getElementById("monthlyTarget").innerHTML =
    "Complete " + data.monthlyTarget + " habits this month";

  document.getElementById("dailyFill").style.width =
    (data.dailyCompleted / data.dailyTarget) * 100 + "%";

  document.getElementById("weeklyFill").style.width =
    (data.weeklyCompleted / data.weeklyTarget) * 100 + "%";

  document.getElementById("monthlyFill").style.width =
    (data.monthlyCompleted / data.monthlyTarget) * 100 + "%";
}

async function loadHeroCard() {
  const userId = localStorage.getItem("userId");
  const response = await fetch(
    `https://habit-tracker-project-production.up.railway.app/tracking/dashboard-hero/${userId}`,
  );

  const data = await response.json();

  document.getElementById("heroCard").innerHTML = `
<div class="hero-card">

    <h2>
    🔥 You're on a
    ${data.currentStreak}
    Day Streak!
    </h2>

    <p class="hero-subtitle">
    Keep going. Don't break the chain.
    </p>

    <div class="hero-stats">

        <div>
            🏆
            <br>
            ${data.badgesEarned}
            Badges
        </div>

        <div>
            ⭐
            <br>
            ${data.totalPoints}
            Points
        </div>

        <div>
            ⚠️
            <br>
            ${data.pendingHabits}
            Pending
        </div>

    </div>

    <button onclick=
    "window.location.href='myhabits.html'">
    Complete Today's Habits
    </button>

</div>
`;
}

function toggleNotifications() {
  document.getElementById("notificationPanel").classList.toggle("active");
}

async function loadNotifications() {
  const userId = localStorage.getItem("userId");
  const response = await fetch(
    `https://habit-tracker-project-production.up.railway.app/tracking/dashboard-hero/${userId}`,
  );

  const data = await response.json();

  let notifications = [];

  if (data.streakMessage) {
    notifications.push(data.streakMessage);
  }

  if (data.pendingMessage) {
    notifications.push(data.pendingMessage);
  }

  if (data.nextBadgeMessage) {
    notifications.push(data.nextBadgeMessage);
  }

  notifications.push(
    `🏆 ${data.badgesEarned}
Badges Earned`,
  );

  document.getElementById("notificationCount").innerHTML = notifications.length;

  let output = "";

  notifications.forEach((item) => {
    output += `
        <div class="notification-item">
            ${item}
        </div>
        `;
  });

  document.getElementById("notificationPanel").innerHTML = output;
}

loadNotifications();

function requestNotificationPermission() {
  if ("Notification" in window) {
    Notification.requestPermission().then((permission) => {
      console.log("Notification Permission:", permission);
    });
  }
  console.log(Notification.permission);
}

requestNotificationPermission();

async function checkHabitReminder() {
  const userId = localStorage.getItem("userId");

  const response = await fetch(
    `https://habit-tracker-project-production.up.railway.app/tracking/dashboard-hero/${userId}`,
  );

  const data = await response.json();

  const pending = data.pendingHabits;

  if (pending <= 0) {
    return;
  }

  const today = new Date().toISOString().split("T")[0];

  const now = new Date();

  const hour = now.getHours();

  const minute = now.getMinutes();

  if (hour === 22) {
    const key = "notif10_" + today;

    if (!localStorage.getItem(key)) {
      new Notification("Habit Tracker", {
        body: `⚠️ ${pending} Habits Pending Today.
            Only 2 Hours Left.`,
      });

      localStorage.setItem(key, "sent");
    }
  }

  if (hour === 23 && minute < 30) {
    const key = "notif11_" + today;

    if (!localStorage.getItem(key)) {
      new Notification("Habit Tracker", {
        body: `⚠️ ${pending} Habits Pending Today.
            Only 1 Hour Left.`,
      });

      localStorage.setItem(key, "sent");
    }
  }

  if (hour === 23 && minute >= 30 && minute < 45) {
    const key = "notif1130_" + today;

    if (!localStorage.getItem(key)) {
      new Notification("Habit Tracker", {
        body: `🚨 ${pending} Habits Pending Today.
            Only 30 Minutes Left.`,
      });

      localStorage.setItem(key, "sent");
    }
  }

  if (hour === 23 && minute >= 45) {
    const key = "notif1145_" + today;

    if (!localStorage.getItem(key)) {
      new Notification("Habit Tracker", {
        body: `🚨 Final Reminder.
            Only 15 Minutes Left.`,
      });

      localStorage.setItem(key, "sent");
    }
  }
}
checkHabitReminder();
setInterval(checkHabitReminder, 60000);

function toggleMenu() {
  document.getElementById("sidebar").classList.toggle("active");
}

function logoutUser() {
  localStorage.clear();
  window.location.href = "index.html";
}
