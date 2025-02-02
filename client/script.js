let studentId = 1; 
const form = document.getElementById("attendance-form");
const tableBody = document.getElementById("attendance-table");

document.getElementById('logout-button').addEventListener('click', function() {
    if (confirm('Are you sure you want to logout?')) {
        localStorage.removeItem("jwt_token");
        window.location.href = '/login.html'; 
    }
});

// function searchStudent() {
//     const searchInput = document.getElementById("search").value.toLowerCase();
//     const rows = document.querySelectorAll("#attendance-table tr");

//     rows.forEach(row => {
//         const name = row.cells[1].innerText.toLowerCase();
//         const email = row.cells[2].innerText.toLowerCase();
//         row.style.display = (name.includes(searchInput) || email.includes(searchInput)) ? "" : "none";
//     });
// }

// form.addEventListener("submit", async function(event) {
//     event.preventDefault();

//     // Get input values
//     const name = document.getElementById("student-name").value.trim();
//     const email = document.getElementById("student-email").value.trim();

//     // Call API to add student
//     if (name && email) {
//         const student = { name, email };
        
//         const response = await fetch('http://localhost:8080/students', {
//             method: 'POST',
//             credentials: "include",
//             headers: {
//                'Content-Type': 'application/json',
//                 'Authorization': `Bearer ${localStorage.getItem("jwt_token")}`,
//             },
//             credentials: "include",  // Include credentials to send the JWT token
//             body: JSON.stringify(student),
//         });

//         if (response.ok) {
//             // After successfully adding the student, fetch all students from the backend
//             fetchAllStudents();
//             form.reset(); // Clear form after submission
//         } else {
//             alert('Error adding student');
//         }
//     }
// });

// // Function to add a student to the table
// function addStudentToTable(id, name, email) {
//     const row = document.createElement("tr");

//     row.innerHTML = `
//         <td>${id}</td>
//         <td>${name}</td>
//         <td>${email}</td>
//         <td>
//             <button class="action-btn edit" onclick="editStudent(this)">Update</button>
//             <button class="action-btn delete" onclick="deleteStudent(this)">Delete</button>
//         </td>
//     `;

//     tableBody.appendChild(row);
// }
