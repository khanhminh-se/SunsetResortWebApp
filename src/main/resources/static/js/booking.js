// amount of accommodation
document.addEventListener("DOMContentLoaded", () => {
    if (window.location.pathname === "/showproduct" ||  window.location.pathname ==="/accommodations") {
        // localStorage.removeItem("bookings");
        // localStorage.removeItem("totalAmount");
        localStorage.clear();
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

    reserveButton.addEventListener("click", (e) => {
        fetch("/reservations/make-reservations",{ method:"POST",
            headers:{
            "Content-Type": "application/json",
            },
            body:JSON.stringify({
                bookings: bookings // Send the bookings array inside an object
            })
            }
            )
            .then(response => response.json())
            .then(data =>{
                if(data.redirectURL){
                    window.location.href=data.redirectURL;
                }

                localStorage.setItem("reservations",JSON.stringify(data.bookings));
                console.log("OK")
            })

            .catch(error => console.error('Error:', error));
        // localStorage.setItem("bookings", JSON.stringify(bookings));
        // localStorage.setItem("totalAmount", totalAmount);
        // console.log("Current Bookings:", bookings);
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


document.addEventListener("DOMContentLoaded", () => {
   const staySummary = document.getElementById("summary-stay");
   const reservations = JSON.parse(localStorage.getItem("reservations")) || [];
   staySummary.innerHTML = '';
   reservations.forEach((booking) => {
       const str = `
        <p>Room Name: ${booking.roomName}</p>
            <p>Quantity: ${booking.quantity}</p>
            <p>Total Price: ${booking.totalPrice}</p>`;
       staySummary.innerHTML += str;
   })
});




