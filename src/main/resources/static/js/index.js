$(document).ready(function () {
    //get datepicker from jquery
    $(function () {
        $(".datepicker").datepicker();
    });

    //show more menu infor in desktop
    $(function showMoreMenu() {
        let clickShow = $(".mobile-nav li.has-children > a");
        clickShow.click(ShowMore);

        function ShowMore(event) {
            $(this).siblings(".mobile-sub-nav").slideToggle(100);
        }
    });

    //video btn
    $(function playVideo() {
        $(".video-box").hide();
        $(".play-video-btn").click(function () {
            $(".video-box").show();
        });

        $("#close-video").click(function () {
            $(".video-box").hide();
        })
    });

    //load items
    function loadVillaItems() {
        let villas = $("#villa-items-row");
        let villasArr = [
            { overlay: "Denpasar", imgSrc: `images/Our villas - 1.jpg`, name: "Whispering Pines Villa", bed: "4 ", bath: "3 ", guest: "12 ", details: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut elit tellus, luctus nec ullamcorper mattis, pulvinar", price: "150.000" },
            { overlay: "Bandung", imgSrc: `images/Our villas - 2.jpg`, name: "Mountain Serenity Villa", bed: "6 ", bath: "4 ", guest: "14 ", details: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut elit tellus, luctus nec ullamcorper mattis, pulvinar", price: "165.000" },
            { overlay: "Tangerang", imgSrc: `images/Our villas - 3.jpg`, name: "Modern Bliss Villa", bed: "7 ", bath: "5 ", guest: "15 ", details: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut elit tellus, luctus nec ullamcorper mattis, pulvinar", price: "175.000" }
        ];
        let VillasEl = "";

        for (s of villasArr) {
            VillasEl += `
                <div class="col-lg-4 col-sm-12">
                    <div class="villa-items">
                        <div class="villa-img">
                            <p class="overlay">${s.overlay}</p>
                            <img src="${s.imgSrc}" alt="">
                        </div>
                
                        <div class="stars">
                            <i class="fa fa-star" aria-hidden="true"></i>
                            <i class="fa fa-star" aria-hidden="true"></i>
                            <i class="fa fa-star" aria-hidden="true"></i>
                            <i class="fa fa-star" aria-hidden="true"></i>
                            <i class="fa fa-star-half-o" aria-hidden="true"></i>
                        </div>

                        <h3>${s.name}</h3>
                        <!-- bieu tuong -->
                        <div class="facility-icons">
                            <i class="fa-solid fa-bed" aria-hidden="true"></i><span> ${s.bed} Beds</span>
                            <i class="fa-solid fa-bath" aria-hidden="true"></i><span> ${s.bath} Baths</span> 
                            <i class="fa-solid fa-users" aria-hidden="true"></i><span> ${s.guest} Guests</span>
                        </div>
                        <p>${s.details}</p>
                        <hr>
                        <div class="price-and-details">
                            <p><span style="font-size: 24px; font-weight: 500">$${s.price}</span>/Night</p>
                            <button class="more-details-btn orange-button">More Details</button>
                        </div>
                    </div>
                </div>`;
        }
        villas.append(VillasEl);
    }
    loadVillaItems();

    //load blogs
    function loadBlogs() {
        let blogArr = [
            { imgSrc: `images/Blog 1.jpg"`, title: "10 Ways Villas Can Make Your Holiday Enjoyable", date: "June 13, 2024" },
            { imgSrc: `images/Blog 2.jpg"`, title: "Top 8 destinations & villas you must visit in 2024", date: "June 13, 2024" },
            { imgSrc: `images/Blog 3.jpg"`, title: "Plan your days and weeks to allocate time for Family", date: "June 8, 2024" }
        ];
        let blogEl = "";
        for (const b of blogArr) {

            blogEl += `
            <div class="col-lg-4 col-sm-12">
                <div class="blogs">
                    <a href="#">
                        <div class="blog-img-box">
                            <img src="${b.imgSrc}" alt="blog-1">
                        </div>
                        <div class="overlay">
                            <h4>${b.title}</h4>
                            <p><i class="fa-regular fa-clock"></i> ${b.date}</p>
                        </div>
                    </a>
                </div>
            </div>
            `;
        }
        $("#blogs-row").append(blogEl);
    }
    loadBlogs();

    //control card
    $(function controlCard() {
        const caroselCard = document.querySelector(".carousel-card");
        const arrowBtns = document.querySelectorAll(".control i");
        const firstCardWidth = caroselCard.querySelector(".card").offsetWidth;
        const carouselChildren = [...caroselCard.children];

        let isDragging = false, startX, startScrollLeft;

        let cardPerView = Math.round(caroselCard.offsetWidth / firstCardWidth);

        carouselChildren.slice(-cardPerView).reverse().forEach(card => {
            caroselCard.insertAdjacentHTML("afterbegin", card.outerHTML);
        });

        carouselChildren.slice(0, cardPerView).forEach(card => {
            caroselCard.insertAdjacentHTML("beforeend", card.outerHTML);
        });

        arrowBtns.forEach(btn => {
                btn.addEventListener("click", () => {
                    caroselCard.scrollLeft += btn.id === "previous" ? -(firstCardWidth + 30) : (firstCardWidth + 30);
                });
            }
        );

        const dragStart = (e) => {
            isDragging = true;
            caroselCard.classList.add("dragging");
            startX = e.pageX;
            startScrollLeft = caroselCard.scrollLeft;
        }

        const dragging = (e) => {
            if (!isDragging) return;
            caroselCard.scrollLeft = startScrollLeft - (e.pageX - startX);
        }

        const dragStop = () => {
            isDragging = false;
            caroselCard.classList.remove("dragging");
        }

        caroselCard.addEventListener("mousedown", dragStart);
        caroselCard.addEventListener("mousemove", dragging);
        caroselCard.addEventListener("mouseup", dragStop);

    });

    //go-to-top
    $(".go-to-top").click(function () {
        $("html, body").animate({ scrollTop: 0 }, 1000);
    });

    $(window).scroll(function () {
        if ($(this).scrollTop() > 200) {
            $(".go-to-top").fadeIn();
        } else {
            $(".go-to-top").fadeOut();
        }
    })

    // show login form
    function showLoginForm() {
        $('#popup-container').removeClass('hidden');
        $('#login-form').removeClass('hidden');
        $('#signup-form').addClass('hidden');
        $('#toptitle').addClass('hidden');
        $('#reverse-form').addClass('hidden');
        $('#requireforbooking').addClass('hidden');
    }

    // show signup form
    function showSignupForm() {
        $('#popup-container').removeClass('hidden');
        $('#signup-form').removeClass('hidden');
        $('#login-form').addClass('hidden');
        $('#toptitle').addClass('hidden');
        $('#reverse-form').addClass('hidden');
        $('#requireforbooking').addClass('hidden');
    }


});


//edit profile and update
function enableEditing() {
    const fields = ["user-name", "user-email", "user-phone", "user-address"];
    fields.forEach(fieldId => {
        const displaySpan = document.getElementById(fieldId);
        const inputField = document.getElementById(`${fieldId}-input`);
        displaySpan.style.display = "none";
        inputField.style.display = "inline";
    });
    document.getElementById("edit-button").style.display = "none";
    document.getElementById("update-button").style.display = "inline";
}

function saveChanges() {
    const fields = ["user-name", "user-email", "user-phone", "user-dob", "user-nationality", "user-gender", "user-address"];
    fields.forEach(fieldId => {
        const displaySpan = document.getElementById(fieldId);
        const inputField = document.getElementById(`${fieldId}-input`);
        displaySpan.textContent = inputField.value;
        displaySpan.style.display = "inline";
        inputField.style.display = "none";
    });

    document.getElementById("edit-button").style.display = "inline";
    document.getElementById("update-button").style.display = "none";
}

document.getElementById("update-button").addEventListener("click", saveChanges);

// each function on sidebar in profile
function showProfile() {
    $('#profile').removeClass('hidden');
    $('#booking-detail').addClass('hidden');
    $('#purchase-history').addClass('hidden');
    $('#change-password').addClass('hidden');
    $('#logout-popup').addClass('hidden');
}

function showBookingDetail() {
    $('#booking-detail').removeClass('hidden');
    $('#profile').addClass('hidden');
    $('#purchase-history').addClass('hidden');
    $('#change-password').addClass('hidden');
    $('#logout-popup').addClass('hidden');
}

function showPurchaseHistory() {
    $('#profile').addClass('hidden');
    $('#booking-detail').addClass('hidden');
    $('#purchase-history').removeClass('hidden');
    $('#change-password').addClass('hidden');
    $('#logout-popup').addClass('hidden');
}

function showChangePassword() {
    $('#profile').addClass('hidden');
    $('#booking-detail').addClass('hidden');
    $('#purchase-history').addClass('hidden');
    $('#change-password').removeClass('hidden');
    $('#logout-popup').addClass('hidden');
}

// show function from homepage
$(document).ready(function () {
    const urlParams = new URLSearchParams(window.location.search);
    const section = urlParams.get('section');

    switch (section) {
        case 'profile':
            $('#profile').removeClass('hidden');
            break;
        case 'booking':
            $('#booking-detail').removeClass('hidden');
            break;
        case 'history':
            $('#purchase-history').removeClass('hidden');
            break;
        case 'password':
            $('#change-password').removeClass('hidden');
            break;
        default:
            $('#profile').removeClass('hidden');
            break;
    }
});


// log out
function showLogoutPopup() {
    $('#logout-popup').removeClass('hidden');
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
        alert("You have successfully logged out.");
        hideLogoutPopup();
        window.location.href = "/logout";
    });

    $('#cancel-logout').click(function () {
        hideLogoutPopup();
    });
});

// change password
function checkPassword() {
    const oldPassword = document.getElementById("old-password-input").value;
    const newPassword = document.getElementById("new-password-input").value;
    const repeatPassword = document.getElementById("repeat-password-input").value;

    // if (!oldPassword) {
    //     alert("Please enter your current password.");
    //     return;
    // }

    if (newPassword !== repeatPassword) {
        alert("New password and repeat password do not match.");
        return;
    }

    alert("Password changed successfully!");

    document.getElementById("old-password-input").value = "";
    document.getElementById("new-password-input").value = "";
    document.getElementById("repeat-password-input").value = "";
}

document.addEventListener("DOMContentLoaded", function () {
    document.querySelectorAll(".toggle-password").forEach(function (toggle) {
        toggle.addEventListener("click", function () {
            this.classList.toggle("fa-eye");
            this.classList.toggle("fa-eye-slash");

            const input = document.querySelector(this.getAttribute("toggle"));

            if (input.type === "password") {
                input.type = "text";
            } else {
                input.type = "password";
            }
        });
    });
});

