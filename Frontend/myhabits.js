const userId = 1;

async function loadHabits() {
  const userId = localStorage.getItem("userId");
  const response = await fetch(
    `https://habit-tracker-project-production.up.railway.app/habit/user/${userId}`,
  );

  const habits = await response.json();

  const trackingResponse = await fetch(
    `https://habit-tracker-project-production.up.railway.app/tracking/today/${userId}`,
  );

  const completedHabits = await trackingResponse.json();

  let output = "";

  habits.forEach((habit) => {
    let status = "Status : Not Completed";

    completedHabits.forEach((track) => {
      if (track.habit.id === habit.id) {
        status = "Status : ✅ Completed Today";
      }
    });
    output += `
<div class="card">

    <h3>${habit.name}</h3>

    <p>Category : ${habit.category}</p>

    <p>Frequency : ${habit.frequency}</p>

    <button onclick="completeHabit(${habit.id})">
        Complete
    </button>

    <button onclick="editHabit(${habit.id})">
        Edit
    </button>

    <button onclick="deleteHabit(${habit.id})">
        Delete
    </button>
    <div id="editForm-${habit.id}" style="display:none">

    <input type="text"
           id="name-${habit.id}"
           value="${habit.name}">

    <input type="text"
           id="category-${habit.id}"
           value="${habit.category}">

    <select id="frequency-${habit.id}">
        <option value="DAILY">DAILY</option>
        <option value="WEEKLY">WEEKLY</option>
    </select>

    <button onclick="updateHabit(${habit.id})">
        Update
    </button>

</div>

    <p id="status-${habit.id}">
    ${status}
</p>

</div>
`;
  });

  document.getElementById("habitList").innerHTML = output;
}

// loadHabits();

async function completeHabit(habitId) {
  const userId = localStorage.getItem("userId");
  const response = await fetch(
    `https://habit-tracker-project-production.up.railway.app/tracking/complete/${habitId}/${userId}`,
    {
      method: "POST",
    },
  );

  document.getElementById(`status-${habitId}`).innerHTML =
    "Status : ✅ Completed Today";
}
loadHabits();

async function deleteHabit(habitId) {
  try {
    const userId = localStorage.getItem("userId");

    const response = await fetch(
      `https://habit-tracker-project-production.up.railway.app/habit/delete/${habitId}?userId=${userId}`,
      {
        method: "DELETE",
      },
    );

    const result = await response.text();

    alert(result);

    loadHabits();
  } catch (error) {
    console.log(error);
    alert(error);
  }
}

function editHabit(habitId) {
  document.getElementById(`editForm-${habitId}`).style.display = "block";
}

async function updateHabit(habitId) {
  const name = document.getElementById(`name-${habitId}`).value;

  const category = document.getElementById(`category-${habitId}`).value;

  const frequency = document.getElementById(`frequency-${habitId}`).value;

  const userId = localStorage.getItem("userId");
  await fetch(
    `https://habit-tracker-project-production.up.railway.app/habit/update/${habitId}?userId=${userId}`,
    {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        name: name,
        category: category,
        frequency: frequency,
      }),
    },
  );

  alert("Habit Updated Successfully");

  setTimeout(() => {
    loadHabits();
  }, 1000);
  loadHabits();
}

function toggleMenu() {
  document.getElementById("sidebar").classList.toggle("active");
}

function logoutUser() {
  localStorage.clear();

  window.location.href = "index.html";
}
