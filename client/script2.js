const form = document.getElementById("login-form");
const loginMessage = document.getElementById("login-message");

form.addEventListener("submit", async function (event) {
    event.preventDefault();

    // Get input values
    const username = document.getElementById("username").value.trim();
    const password = document.getElementById("password").value.trim();

    // Validate inputs
    if (!username || !password) {
        loginMessage.textContent = "Please fill in both fields.";
        loginMessage.style.color = "red";
        return;
    }

    // API call to validate user credentials
    try {
        const response = await fetch("localhost:8080/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({ username, password }),
        });

        if (response.ok) {
            const data = await response.json();

            if (data.response) {
                // Store JWT token in localStorage
                localStorage.setItem("jwt_token", data.response);

                // Redirect to the home page
                window.location.href = "/Form.html"; // Update this with your actual home page URL
            } else {
                loginMessage.textContent = "Invalid credentials, please try again.";
                loginMessage.style.color = "red";
            }
        } else {
            loginMessage.textContent = "There was an error with the login request.";
            loginMessage.style.color = "red";
        }
    } catch (error) {
        loginMessage.textContent = "Network error, please try again later.";
        loginMessage.style.color = "red";
    }
});