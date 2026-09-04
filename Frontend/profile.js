function toggleMenu() {
  document.getElementById("sidebar").classList.toggle("active");
}

function logoutUser() {
  localStorage.clear();

  window.location.href = "index.html";
}

const savedPhoto = localStorage.getItem("profilePhoto");

if (savedPhoto) {
  document.getElementById("profileImage").src = savedPhoto;
}

async function loadProfile() {
  const userId = localStorage.getItem("userId");

  const profileResponse = await fetch(
    `https://habit-tracker-project-production.up.railway.app/user/profile/${userId}`,
  );

  const user = await profileResponse.json();

  if (user.profileImage != null && user.profileImage != "") {
    document.getElementById("profileImage").src =
      "https://habit-tracker-project-production.up.railway.app/uploads/" +
      user.profileImage;
  } else {
    document.getElementById("profileImage").src =
      "https://habit-tracker-project-production.up.railway.app/uploads/default-avatar-1.png";
  }

  document.getElementById("userName").innerHTML = user.name;

  document.getElementById("userEmail").innerHTML = user.email;

  document.getElementById("memberSince").innerHTML =
    "Member Since : " + user.createdAt;
  // Dashboard Data
  //const userId = localStorage.getItem("userId");
  const dashboardResponse = await fetch(
    `https://habit-tracker-project-production.up.railway.app/tracking/dashboard/${userId}`,
  );

  const dashboard = await dashboardResponse.json();

  // Overall Statistics
  //const userId = localStorage.getItem("userId");
  const overallResponse = await fetch(
    `https://habit-tracker-project-production.up.railway.app/tracking/statistics/overall/${userId}`,
  );

  const overall = await overallResponse.json();

  // Total Points
  document.getElementById("totalPoints").innerHTML = overall.totalPoints;

  // Current Streak
  document.getElementById("currentStreak").innerHTML = overall.currentStreak;

  // Highest Streak
  document.getElementById("highestStreak").innerHTML = overall.highestStreak;

  // Total Habits
  document.getElementById("totalHabits").innerHTML = dashboard.totalHabits;

  // Progress
  document.getElementById("completionRate").innerHTML =
    overall.completionRate.toFixed(1) + "%";

  document.getElementById("completionText").innerHTML =
    overall.completionRate.toFixed(1) + "%";

  document.getElementById("progressFill").style.width =
    overall.completionRate + "%";

  // Completed Habits
  document.getElementById("totalCompleted").innerHTML =
    overall.totalCompletions;

  // Average Points
  document.getElementById("averagePoints").innerHTML =
    overall.averagePointsPerCompletion.toFixed(1);

  let rank = "🌱 Beginner";

  if (overall.totalPoints >= 500) {
    rank = "🔥 Consistent";
  }

  if (overall.totalPoints >= 1000) {
    rank = "💎 Pro";
  }

  if (overall.totalPoints >= 2000) {
    rank = "🚀 Master";
  }

  if (overall.totalPoints >= 5000) {
    rank = "👑 Legend";
  }

  document.getElementById("rankName").innerHTML = rank;

  //const userId = localStorage.getItem("userId");
  const badgeResponse = await fetch(
    `https://habit-tracker-project-production.up.railway.app/tracking/badges/all/${userId}`,
  );

  const badges = await badgeResponse.json();

  let badgeHTML = "";

  badges.slice(0, 4).forEach((badge) => {
    badgeHTML += `

<div class="badge-item">

<h1>

🏆

</h1>

<h3>

${badge.badgeName}

</h3>

</div>

`;
  });

  document.getElementById("recentBadges").innerHTML = badgeHTML;

  //const userId = localStorage.getItem("userId");
  const favoriteResponse = await fetch(
    `https://habit-tracker-project-production.up.railway.app/tracking/favorite-habit/${userId}`,
  );

  const favorite = await favoriteResponse.json();

  document.getElementById("favoriteHabit").innerHTML = favorite.habitName;

  document.getElementById("favoriteCompleted").innerHTML =
    favorite.completedCount;

  document.getElementById("favoriteConsistency").innerHTML =
    favorite.consistency.toFixed(1) + "%";
}

loadProfile();

function showEditProfile() {
  document.getElementById("editProfileModal").style.display = "block";

  document.getElementById("editName").value =
    document.getElementById("userName").innerHTML;

  document.getElementById("editEmail").value =
    document.getElementById("userEmail").innerHTML;
}

function closeEditProfile() {
  document.getElementById("editProfileModal").style.display = "none";
}

function showPasswordModal() {
  document.getElementById("passwordModal").style.display = "block";
}

function closePasswordModal() {
  document.getElementById("passwordModal").style.display = "none";
}

async function uploadPhoto() {
  alert("uploaded");
  const file = document.getElementById("photoInput").files[0];

  if (!file) {
    alert("Please select a photo first.");
    return;
  }

  const userId = localStorage.getItem("userId");

  const formData = new FormData();

  formData.append("file", file);

  const response = await fetch(
    `https://habit-tracker-project-production.up.railway.app/user/upload-photo/${userId}`,
    {
      method: "POST",
      body: formData,
    },
  );

  const user = await response.json();

  document.getElementById("profileImage").src =
    "https://habit-tracker-project-production.up.railway.app/uploads/" +
    user.profileImage;

  alert("Profile Photo Updated");
}

async function removePhoto() {
  const userId = localStorage.getItem("userId");

  const response = await fetch(
    `https://habit-tracker-project-production.up.railway.app/user/remove-photo/${userId}`,
    {
      method: "PUT",
    },
  );

  const user = await response.json();

  document.getElementById("profileImage").src =
    "https://habit-tracker-project-production.up.railway.app/uploads/default-avatar-1.png";
}

async function saveProfile() {
  const userId = localStorage.getItem("userId");

  const name = document.getElementById("editName").value;

  const email = document.getElementById("editEmail").value;

  const response = await fetch(
    `https://habit-tracker-project-production.up.railway.app/user/update-profile/${userId}`,
    {
      method: "PUT",

      headers: {
        "Content-Type": "application/json",
      },

      body: JSON.stringify({
        name: name,

        email: email,
      }),
    },
  );

  const user = await response.json();

  document.getElementById("userName").innerHTML = user.name;

  document.getElementById("userEmail").innerHTML = user.email;

  localStorage.setItem("userName", user.name);

  localStorage.setItem("userEmail", user.email);

  closeEditProfile();

  alert("Profile Updated Successfully");
}

async function changePassword() {
  const userId = localStorage.getItem("userId");

  const oldPassword = document.getElementById("oldPassword").value;

  const newPassword = document.getElementById("newPassword").value;

  const confirmPassword = document.getElementById("confirmPassword").value;

  if (newPassword !== confirmPassword) {
    alert("Passwords do not match");
    return;
  }

  const response = await fetch(
    `https://habit-tracker-project-production.up.railway.app/user/change-password/${userId}`,
    {
      method: "PUT",

      headers: {
        "Content-Type": "application/json",
      },

      body: JSON.stringify({
        oldPassword: oldPassword,

        newPassword: newPassword,
      }),
    },
  );

  const result = await response.text();

  alert(result);

  if (result === "Password Updated Successfully") {
    closePasswordModal();

    document.getElementById("oldPassword").value = "";

    document.getElementById("newPassword").value = "";

    document.getElementById("confirmPassword").value = "";
  }
}
