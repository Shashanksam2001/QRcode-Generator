const loginBtn = document.getElementById("login-btn");
const registerBtn = document.getElementById("register-btn");
const loginForm = document.getElementById("login-form");
const registerForm = document.getElementById("register-form");

// toggle forms on button click
loginBtn.addEventListener("click", () => {
    loginForm.classList.add("active");
    registerForm.classList.remove("active");
});

registerBtn.addEventListener("click", () => {
    loginForm.classList.remove("active");
    registerForm.classList.add("active");
});

// read query params
const params = new URLSearchParams(window.location.search);
const errorMsg = params.get("error");
const formType = params.get("form");

// auto-open correct form
if (formType === "login") {
    loginForm.classList.add("active");
    registerForm.classList.remove("active");

    if (errorMsg) {
        const msgEl = document.createElement("p");
        msgEl.innerText = errorMsg;
        msgEl.style.color = "red";
        msgEl.style.marginBottom = "10px";
        loginForm.prepend(msgEl); // show message inside login box
    }
} else if (formType === "register") {
    registerForm.classList.add("active");
    loginForm.classList.remove("active");

    if (errorMsg) {
        const msgEl = document.createElement("p");
        msgEl.innerText = errorMsg;
        msgEl.style.color = "red";
        msgEl.style.marginBottom = "10px";
        registerForm.prepend(msgEl); // show message inside register box
    }
}
