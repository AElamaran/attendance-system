const form = document.getElementById("login-form");
const loginMessage = document.getElementById("login-message");

form.addEventListener("submit", async function(event) {
    event.preventDefault();

    // Get input values
    const username = document.getElementById("username").value.trim();
    const password = document.getElementById("password").value.trim();

    // API call to validate user credentials
    if (username && password) {
        const user = { username, password };

        try {
            const response = await fetch('YOUR_BACKEND_API_URL/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(user),
            });

            if (response.ok) {
                const data = await response.json();

                if (data.success && data.token) {
                    // Store JWT token in localStorage or sessionStorage
                    localStorage.setItem('jwt_token', data.token); // Or use sessionStorage

                    // Redirect to dashboard or home page on successful login
                    window.location.href = '/dashboard'; // Update this with your actual redirect URL
                } else {
                    loginMessage.textContent = "Invalid credentials, please try again.";
                }
            } else {
                loginMessage.textContent = "There was an error with the login request.";
            }
        } catch (error) {
            loginMessage.textContent = "Network error, please try again later.";
        }
    } else {
        loginMessage.textContent = "Please fill in both fields.";
    }
});
