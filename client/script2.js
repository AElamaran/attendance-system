const form = document.getElementById("login-form");
const loginMessage = document.getElementById("login-message");

form.addEventListener("submit", async function (event) {
    event.preventDefault();

  
    const username = document.getElementById("username").value.trim();
    const password = document.getElementById("password").value.trim();

    
    if (!username || !password) {
        loginMessage.textContent = "Please fill in both fields.";
        loginMessage.style.color = "red";
        return;
    }

    try {
        const response = await fetch("http://localhost:8080/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            credentials: "include",
            body: JSON.stringify({ username, password }),
        });

       
        if (response.ok) {
            const data = await response.json();

           
            if (data.token) {
                
                localStorage.setItem("jwt_token", data.token);

                
                window.location.href = "Form.html"; 
               
                loginMessage.textContent = "Invalid credentials, please try again.";
                loginMessage.style.color = "red";
            }
        } else {
            
            const errorData = await response.json();
            loginMessage.textContent = errorData.message || "Invalid credentials, please try again.";
            loginMessage.style.color = "red";
        }
    } catch (error) {

        console.error("Error:", error);
        loginMessage.textContent = "Network error, please try again later.";
        loginMessage.style.color = "red";
    }


});