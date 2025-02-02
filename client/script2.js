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

 else{
    window.location.href = "Form.html"; 
 }
});