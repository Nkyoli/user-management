const remember_checkbox = document.getElementById('remember_me');
const submit_btn = document.querySelector('.btn');

window.addEventListener('load', () => {
    if(localStorage.getItem('remember_me')) {
        remember_checkbox.checked = true;
        document.getElementById('username').value = localStorage.getItem('username');
        document.getElementById('password').value = localStorage.getItem('password');
    } else {
        remember_checkbox.checked = false;
    }
});

submit_btn.addEventListener('click', () => {
    if(remember_checkbox.checked) {
        localStorage.setItem('username', document.getElementById('username').value);
        localStorage.setItem('password', document.getElementById('password').value);
        localStorage.setItem('remember_me', 'remember');
    } else {
        localStorage.setItem('username', '');
        localStorage.setItem('password', '');
        localStorage.setItem('remember_me', false);
    }
});