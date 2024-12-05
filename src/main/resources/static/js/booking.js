// amount of accommodation
document.addEventListener("DOMContentLoaded", () => {
    if (window.location.pathname === "/showproduct") {
        localStorage.removeItem("bookings");
        localStorage.removeItem("totalAmount");
    }

    let bookings = JSON.parse(localStorage.getItem("bookings")) || [];
    let totalAmount = parseInt(localStorage.getItem("totalAmount")) || 0;

    const totalAmountEl = document.getElementById("total-amount");
    const bookButtons = document.querySelectorAll(".book-btn");
    const reserveButton = document.getElementById("export-booking");

    // let totalAmount = 0;
    let totalPrice = 0;
    // let bookings = [];

    bookButtons.forEach(button => {
        button.addEventListener("click", () => {
            const card = button.closest(".room-card");
            const roomName = card.querySelector("h3").innerText;
            const remainedQuantityEl = card.querySelector(".remained-quantity");
            const remainedQuantity = parseInt(remainedQuantityEl.innerText);
            const quantityInput = card.querySelector(".quantity");
            const quantity = parseFloat(quantityInput.value);
            const priceText = card.querySelector(".price strong").innerText;
            const price = parseFloat(priceText.replace(/[^0-9.]/g, ""));

            if (!quantity || quantity <= 0) {
                alert("Please enter a valid quantity!");
                return;
            }

            if (quantity > remainedQuantity) {
                alert("Not enough rooms available!");
                return;
            }

            totalAmount += quantity;
            totalPrice =  price * quantity;
            totalAmountEl.innerText = totalAmount;

            bookings.push({
                roomName: roomName,
                quantity: quantity,
                price: price,
                totalPrice: totalPrice,
            });
        });
    });

    reserveButton.addEventListener("click", () => {
        localStorage.setItem("bookings", JSON.stringify(bookings));
        localStorage.setItem("totalAmount", totalAmount);
        console.log("Current Bookings:", bookings);
    });
});

document.addEventListener("DOMContentLoaded", () => {
    const arrivingDateText = document.getElementById("arriving-date").innerText;
    const departingDateText = document.getElementById("departing-date").innerText;

    const arrivingDateMatch = arrivingDateText.match(/(\w+), (\w+) (\d+), (\d{4})/);
    const departingDateMatch = departingDateText.match(/(\w+), (\w+) (\d+), (\d{4})/);

    if (arrivingDateMatch && departingDateMatch) {
        const arrivingDate = new Date(`${arrivingDateMatch[2]} ${arrivingDateMatch[3]}, ${arrivingDateMatch[4]}`);
        const departingDate = new Date(`${departingDateMatch[2]} ${departingDateMatch[3]}, ${departingDateMatch[4]}`);

        const totalLengthOfStay = (departingDate - arrivingDate) / (1000 * 60 * 60 * 24); // Convert milliseconds to days

        const totalLengthEl = document.getElementById("total-length-of-stay");
        totalLengthEl.innerText = `${totalLengthOfStay} nights`;
    }
});

document.addEventListener("DOMContentLoaded", () => {
    const bookings = JSON.parse(localStorage.getItem("bookings")) || [];
    const totalAmount = localStorage.getItem("totalAmount") || 0;

    const selectedRoomsEl = document.getElementById("selected-rooms");
    const subtotalPriceEl = document.getElementById("subtotal-price");
    const taxesFeesEl = document.getElementById("taxes-fees");
    const totalPriceEl = document.getElementById("total-price");
    // const totalAmountEl = document.getElementById("total-amount");

    let subtotal = 0;

    if (bookings.length === 0) {
        selectedRoomsEl.innerHTML = "<p>No rooms selected.</p>";
    } else {
        bookings.forEach(booking => {
            const { roomName, quantity, price } = booking;
            const totalRoomPrice = price * quantity;
            subtotal += totalRoomPrice;

            const roomDetails = document.createElement("p");
            roomDetails.innerText = `${quantity} x ${roomName} - $${totalRoomPrice}`;
            selectedRoomsEl.appendChild(roomDetails);
        });
    }

    const tax = subtotal * 0.15;
    const totalPrice = subtotal + tax;

    subtotalPriceEl.innerText = `$${subtotal.toFixed(2)}`;
    taxesFeesEl.innerText = `$${tax.toFixed(2)}`;
    totalPriceEl.innerText = totalPrice.toFixed(2);

    // totalAmountEl.innerText = totalAmount;
});


//Search accommodation handlers
function formatDate(inputDate) {
    const date = new Date(inputDate); // Parse the input date string
    const year = date.getFullYear(); // Get the year
    const month = String(date.getMonth() + 1).padStart(2, '0'); // Get the month (0-indexed) and pad with leading zero
    const day = String(date.getDate()).padStart(2, '0'); // Get the day and pad with leading zero

    return `${year}-${month}-${day}`; // Return formatted date
}
document.getElementById('searchAccommodationForm').addEventListener('submit', function(event) {
    event.preventDefault();  // Prevent the form from submitting normally

    // Collect form data
    const checkInDate = document.querySelector('input[name="checkInDate"]').value;
    const checkOutDate = document.querySelector('input[name="checkOutDate"]').value;
    const numberOfGuests = document.querySelector('input[name="numberOfGuests"]').value;

    // Create a JSON object to send in the request
    const requestData = {
        checkInDate: formatDate(checkInDate),
        checkOutDate: formatDate(checkOutDate),
        numberOfGuests: numberOfGuests
    };

    // Send the data as a JSON POST request using fetch
    fetch('/accommodations/searchAccommodation', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(requestData)
    })
        .then(response => {
            if (response.ok) {
                // If the server returns an HTML page as the response, reload the page
                return response.text();  // Get the HTML response as a text
            }
            throw new Error('Request failed');
        })
        .then(html => {
            // Since the response is a full HTML page, you can either:
            // Option 1: Redirect to the new page (if the response provides a location for redirect)
            // window.location.href = response.url;  // Uncomment if you want to follow the redirect manually

            // Option 2: Inject the HTML response into an existing element
            document.documentElement.innerHTML = html;  // Replace the whole page with the new HTML
        })
        .catch(error => {
            console.error('Error:', error);  // Handle any errors
        });
});
