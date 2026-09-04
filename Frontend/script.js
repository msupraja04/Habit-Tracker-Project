function showLogin() {
  document.getElementById("loginForm").style.display = "block";

  document.getElementById("registerForm").style.display = "none";
}

function showRegister() {
  document.getElementById("loginForm").style.display = "none";

  document.getElementById("registerForm").style.display = "block";
}

// async function loginUser()
// {
//     const email =
//         document.getElementById("loginEmail").value;

//     const password =
//         document.getElementById("loginPassword").value;

//     const response =
//         await fetch(
//         "https://habit-tracker-project-production.up.railway.app/user/login",
//         {
//             method:"POST",
//             headers:{
//                 "Content-Type":"application/json"
//             },
//             body:JSON.stringify({
//                 email:email,
//                 password:password
//             })
//         });

//     const result =
//         await response.text();

//     if(result === "Login Successful")
//     {
//         window.location.href="dashboard.html";
//     }
//     else
//     {
//         document.getElementById("loginMessage")
//             .innerHTML=result;
//     }

// }

async function loginUser() {
  const email = document.getElementById("loginEmail").value;

  const password = document.getElementById("loginPassword").value;

  // const userId = localStorage.getItem("userId");
  const response = await fetch(
    `https://habit-tracker-project-production.up.railway.app/user/login`,
    {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        email: email,
        password: password,
      }),
    },
  );

  const result = await response.json();

  if (result.message === "Login Successful") {
    localStorage.setItem("userId", result.id);

    localStorage.setItem("userName", result.name);

    localStorage.setItem("userEmail", result.email);

    window.location.href = "dashboard.html";
  } else {
    document.getElementById("loginMessage").innerHTML = result.message;
  }
}

async function registerUser() {
  const name = document.getElementById("name").value;

  const email = document.getElementById("registerEmail").value;

  const password = document.getElementById("registerPassword").value;

  // const userId = localStorage.getItem("userId");
  await fetch(
    `https://habit-tracker-project-production.up.railway.app/user/register`,
    {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        name: name,
        email: email,
        password: password,
      }),
    },
  );

  document.getElementById("registerMessage").innerHTML =
    "Registration Successful";
}
