const users = [
    { id: 1, address: "6/4D Lai Thieu Street", fullname: "Khanh Minh", phonenumber: "123456789", user_id: null },
    { id: 2, address: "123", fullname: "Khanh Minh", phonenumber: "123", user_id: null },
    { id: 52, address: "1235", fullname: "Khanh Minh 123", phonenumber: "1234", user_id: 6 },
];

const requests = [
    { id: 1, name: "John Doe", request: "Booking Villa A", requestid: "1" },
    { id: 2, name: "Jane Smith", request: "Cancel Reservation", requestid: "2" },
];

function showSection(sectionId) {
    document.querySelectorAll('.section-content').forEach(section => {
        section.classList.remove('active');
    });
    document.getElementById(sectionId).classList.add('active');
}

function loadRequests() {
    const requestTable = document.getElementById('request-table');
    requestTable.innerHTML = '';
    requests.forEach((req, index) => {
        const row = `
                  <tr>
                      <td>${index + 1}</td>
                      <td>${req.name}</td>
                      <td>${req.request}</td>
                      <td>${req.requestid}</td>
                      <td>
                          <button class="btn btn-success btn-sm" onclick="acceptRequest(${req.id})">Accept</button>
                          <button class="btn btn-danger btn-sm" onclick="declineRequest(${req.id})">Decline</button>
                      </td>
                  </tr>`;
        requestTable.innerHTML += row;
    });
}

function loadUsers() {
    const userTable = document.getElementById('user-table');
    userTable.innerHTML = '';
    users.forEach((user, index) => {
        const row = `
                  <tr id="row-${user.id}">
                      <td>${user.id}</td>
                      <td>${user.address}</td>
                      <td>${user.fullname}</td>
                      <td>${user.phonenumber}</td>
                      <td>${user.user_id || ''}</td>
                      <td>
                          <button class="btn btn-primary btn-sm" onclick="editUser(${user.id})">Edit</button>
                          <button class="btn btn-danger btn-sm" onclick="deleteUser(${user.id})">Delete</button>
                      </td>
                  </tr>`;
        userTable.innerHTML += row;
    });
}


function acceptRequest(id) {
    alert(`Request ${id} accepted.`);
    const requestIndex = requests.findIndex(req => req.id === id);
    if (requestIndex !== -1) {
        requests.splice(requestIndex, 1);
        loadRequests();
    }
}

function declineRequest(id) {
    alert(`Request ${id} declined.`);
    const requestIndex = requests.findIndex(req => req.id === id);
    if (requestIndex !== -1) {
        requests.splice(requestIndex, 1);
        loadRequests();
    }
}

function editUser(id) {
    const user = users.find(u => u.id === id);
    const row = document.getElementById(`row-${id}`);
    if (user && row) {
        row.innerHTML = `
                  <td>${user.id}</td>
                  <td><input type="text" class="form-control" value="${user.address}" id="edit-address-${id}"></td>
                  <td><input type="text" class="form-control" value="${user.fullname}" id="edit-fullname-${id}"></td>
                  <td><input type="text" class="form-control" value="${user.phonenumber}" id="edit-phonenumber-${id}"></td>
                  <td><input type="text" class="form-control" value="${user.user_id || ''}" id="edit-user_id-${id}"></td>
                  <td>
                      <button class="btn btn-success btn-sm" onclick="saveUser(${id})">Save</button>
                      <button class="btn btn-secondary btn-sm" onclick="cancelEdit(${id})">Cancel</button>
                  </td>`;
    }
}

function saveUser(id) {
    // Lấy thông tin từ input
    const address = document.getElementById(`edit-address-${id}`).value.trim();
    const fullname = document.getElementById(`edit-fullname-${id}`).value.trim();
    const phonenumber = document.getElementById(`edit-phonenumber-${id}`).value.trim();
    const user_id = document.getElementById(`edit-user_id-${id}`).value.trim() || null;

    const userIndex = users.findIndex(u => u.id === id);
    if (userIndex !== -1) {
        users[userIndex] = { id, address, fullname, phonenumber, user_id };
        loadUsers();
        alert('User information updated successfully.');
    }
}

function cancelEdit(id) {
    loadUsers();
}

function deleteUser(id) {
    const userIndex = users.findIndex(u => u.id === id);
    if (userIndex !== -1) {
        users.splice(userIndex, 1);
        loadUsers();
        alert(`User ${id} deleted.`);
    }
}

document.addEventListener('DOMContentLoaded', () => {
    loadRequests();
    loadUsers();
});

// log out
function showLogoutPopup() {
    $('#logout-popup').removeClass('hidden');
    document.querySelectorAll('.section-content').forEach(section => {
        section.classList.remove('active');
    });
}

function hideLogoutPopup() {
    $('#logout-popup').addClass('hidden');
}

$(document).ready(function () {
    $('.sidebar ul li:nth-child(5) a').click(function (e) {
        e.preventDefault();
        showLogoutPopup();
    });

    $('#confirm-logout').click(function () {
        hideLogoutPopup();
        window.location.href = "/logout";
    });

    $('#cancel-logout').click(function () {
        hideLogoutPopup();
    });
});