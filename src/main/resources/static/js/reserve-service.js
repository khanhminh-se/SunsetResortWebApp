document.addEventListener("DOMContentLoaded", () => {
    const remainedSeatEl = document.querySelector(".remained-seat span");
    const numberOfGuestsInput = document.getElementById("adults");
    const form = document.querySelector(".booking-form form");
    const noticePopup = document.getElementById("notice-popup");
    const cancelNoticeButton = document.getElementById("cancel-notice");

    function showNoticePopup() {
        noticePopup.classList.remove("hidden");
    }

    function hideNoticePopup() {
        noticePopup.classList.add("hidden");
    }

    form.addEventListener("submit", (e) => {
        const remainedSeats = parseInt(remainedSeatEl.innerText, 10);
        const numberOfGuests = parseInt(numberOfGuestsInput.value, 10);

        if (isNaN(numberOfGuests) || numberOfGuests <= 0) {
            e.preventDefault();
            showNoticePopup();
        } else if (numberOfGuests > remainedSeats) {
            e.preventDefault();
            showNoticePopup();
        }
    });

    cancelNoticeButton.addEventListener("click", () => {
        hideNoticePopup();
    });
});
