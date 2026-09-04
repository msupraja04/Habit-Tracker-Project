async function loadBadges() {
  const userId = localStorage.getItem("userId");
  const response = await fetch(
    `https://habit-tracker-project-production.up.railway.app/tracking/badges/all/${userId}`,
  );

  const badges = await response.json();

  let output = "";

  badges.forEach((badge) => {
    let percentage = (badge.progress / badge.target) * 100;

    if (percentage > 100) {
      percentage = 100;
    }

    output += `
    <div class="badge-card">

        <h2 style="font-size:32px;">
        ${badge.badgeName}
        </h2>

        <p>
        ${badge.description}
        </p>

        <p>
        Progress :
        ${badge.progress}
        /
        ${badge.target}
        </p>

        <div class="progress-bar">

            <div class="progress-fill
            ${badge.unlocked ? "completed-bar" : "locked-bar"}"
            style="width:${percentage}%">
            </div>

        </div>

        <p>
${percentage.toFixed(0)}%
Completed
</p>

        <h3 class="${badge.unlocked ? "unlocked" : "locked"}">

        ${badge.unlocked ? "✅ Unlocked" : "🔒 Locked"}

        </h3>

    </div>
    `;
  });

  document.getElementById("badgeContainer").innerHTML = output;
}

function toggleMenu() {
  document.getElementById("sidebar").classList.toggle("active");
}

loadBadges();
function logoutUser() {
  localStorage.clear();

  window.location.href = "index.html";
}
