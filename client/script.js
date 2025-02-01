let studentId = 1; // ID counter
const form = document.getElementById("attendance-form");
const tableBody = document.getElementById("attendance-table");

// Handle form submission
form.addEventListener("submit", async function(event) {
    event.preventDefault();

    // Get input values
    const name = document.getElementById("student-name").value.trim();
    const email = document.getElementById("student-email").value.trim();

    // Call API to add student
    if (name && email) {
        const student = { name, email };
        
        // Make API call to add student
        const response = await fetch('YOUR_BACKEND_API_URL/students', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(student),
        });

        if (response.ok) {
            const data = await response.json();
            addStudentToTable(data.id, data.name, data.email); // Assuming the API returns the student object with id
            studentId++;
            form.reset(); // Clear form after submission
        } else {
            alert('Error adding student');
        }
    }
});

// Function to add a student to the table
function addStudentToTable(id, name, email) {
    const row = document.createElement("tr");

    row.innerHTML = `
        <td>${id}</td>
        <td>${name}</td>
        <td>${email}</td>
        <td>
            <button class="action-btn edit" onclick="editStudent(this)">Update</button>
            <button class="action-btn delete" onclick="deleteStudent(this)">Delete</button>
        </td>
    `;

    tableBody.appendChild(row);
}

// Function to delete a student
async function deleteStudent(button) {
    const row = button.parentElement.parentElement;
    const studentId = row.cells[0].innerText; // Get student ID from the table

    // Make API call to delete student
    const response = await fetch(`YOUR_BACKEND_API_URL/students/${studentId}`, {
        method: 'DELETE',
    });

    if (response.ok) {
        row.remove(); // Remove the row from the table if the API call is successful
    } else {
        alert('Error deleting student');
    }
}

// Function to update student details
async function editStudent(button) {
    const row = button.parentElement.parentElement;
    const name = row.cells[1].innerText;
    const email = row.cells[2].innerText;
    const studentId = row.cells[0].innerText;

    document.getElementById("student-name").value = name;
    document.getElementById("student-email").value = email;

    // Remove the existing row after selecting for update
    row.remove();

    // Call the API to update the student
    form.addEventListener("submit", async function updateStudent(event) {
        event.preventDefault();

        const updatedName = document.getElementById("student-name").value.trim();
        const updatedEmail = document.getElementById("student-email").value.trim();

        if (updatedName && updatedEmail) {
            const updatedStudent = { name: updatedName, email: updatedEmail };

            // Make API call to update student
            const response = await fetch(`YOUR_BACKEND_API_URL/students/${studentId}`, {
                method: 'PUT', // or PATCH
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(updatedStudent),
            });

            if (response.ok) {
                const data = await response.json();
                addStudentToTable(data.id, data.name, data.email);
                form.reset(); // Clear form after update
            } else {
                alert('Error updating student');
            }
        }
    });
}

// Function to search students in the table
function searchStudent() {
    const searchInput = document.getElementById("search").value.toLowerCase();
    const rows = document.querySelectorAll("#attendance-table tr");

    rows.forEach(row => {
        const name = row.cells[1].innerText.toLowerCase();
        const email = row.cells[2].innerText.toLowerCase();
        row.style.display = (name.includes(searchInput) || email.includes(searchInput)) ? "" : "none";
    });
}

// Fetch students from API when the page loads
window.addEventListener("load", async function() {
    const response = await fetch('YOUR_BACKEND_API_URL/students');
    const students = await response.json();
    
    students.forEach(student => {
        addStudentToTable(student.id, student.name, student.email);
    });
});
