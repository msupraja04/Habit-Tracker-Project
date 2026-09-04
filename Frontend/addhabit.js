/*async function addHabit()
{
    alert("button working");
    const name =
        document.getElementById("name").value;

    const category =
        document.getElementById("category").value;

    const frequency =
        document.getElementById("frequency").value;

    alert(name);
    const response =
        await fetch(
        `https://habit-tracker-project-production.up.railway.app/habit/add/${userId}`,
        {
            method:"POST",

            headers:{
                "Content-Type":"application/json"
            },

            body:JSON.stringify({
                name:name,
                category:category,
                frequency:frequency
            })
        });
    alert("api called");
    document.getElementById("message")
            .innerHTML =
            "Habit Added Successfully";
}*/

async function addHabit() {
  try {
    const name = document.getElementById("name").value;

    const category = document.getElementById("category").value;

    const frequency = document.getElementById("frequency").value;

    const userId = localStorage.getItem("userId");
    const response = await fetch(
      `https://habit-tracker-project-production.up.railway.app/habit/add/${userId}`,
      {
        method: "POST",
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

    document.getElementById("message").innerHTML = "Habit Added Successfully";
  } catch (error) {
    alert(error);
    console.log(error);
  }
}

function toggleMenu() {
  document.getElementById("sidebar").classList.toggle("active");
}

function logoutUser() {
  localStorage.clear();

  window.location.href = "index.html";
}
