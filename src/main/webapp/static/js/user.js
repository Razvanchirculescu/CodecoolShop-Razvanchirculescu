const signUpButton = document.getElementById("signUpButton");
const signUpModal = document.getElementById('signUpModal');
const signUpModalCancelBtn = document.getElementById("signUpModalCancelBtn");
const xCloseSignupModal = document.getElementById('xCloseSignupModal');


const loginButton = document.getElementById("loginButton");
const loginModal = document.getElementById('loginModal');
const loginModalCancelBtn = document.getElementById("loginModalCancelBtn")
const xCloseLoginModal = document.getElementById('xCloseLoginModal');

const cartButton = document.getElementsByClassName("btn btn-success");
cartButton.addEventListener("click", function () {
    console.log("Button clicked!");
});


// signup modal

signUpButton.addEventListener("click", function (e) {
    {
        document.getElementById("signUpModal").style.display = 'block';
    }
})

window.onclick = function (event) {
    console.log(event.target)
    if (event.target === signUpModal) {
        signUpModal.style.display = "none";
    }

    if (event.target === loginModal) {
        loginModal.style.display = "none";
    }
}

signUpModalCancelBtn.addEventListener("click", function (e) {
    {
        signUpModal.style.display = 'none';
    }
})

xCloseSignupModal.addEventListener("click", function (e) {
    {
        signUpModal.style.display = 'none';
    }
})


//login modal

loginButton.addEventListener("click", function (e) {
    {
        document.getElementById("loginModal").style.display = 'block';
    }
})


loginModalCancelBtn.addEventListener("click", function (e) {
    {
        loginModal.style.display = "none";
    }
})


xCloseLoginModal.addEventListener("click", function (e) {
    {
        loginModal.style.display = 'none';
    }
})


let session = document.getElementById("session")


if (session.innerHTML === "[]") {

    document.getElementById('session').style.visibility = 'hidden';
    document.getElementById("logoutButton").style.visibility = 'hidden'
}

if (session.innerHTML !== "[]") {
    document.getElementById('loginButton').style.visibility = 'hidden';
    document.getElementById('signUpButton').style.visibility = 'hidden';
    document.getElementById("logoutButton").style.visibility = 'visible'


}





