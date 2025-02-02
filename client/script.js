let studentId = 1; 
const form = document.getElementById("attendance-form");
const tableBody = document.getElementById("attendance-table");

document.getElementById('logout-button').addEventListener('click', function() {
    if (confirm('Are you sure you want to logout?')) {
        localStorage.removeItem("jwt_token");
        window.location.href = '/login.html'; 
    }
});

