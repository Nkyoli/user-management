const agreement_checkbox = document.getElementById('agreement');
const sign_up_btn = document.getElementById('sign_up');

agreement_checkbox.addEventListener('click', () => {
    if(agreement_checkbox.checked) {
        sign_up_btn.disabled = false;
    } else {
        sign_up_btn.disabled = true;
    }
});