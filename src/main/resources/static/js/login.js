const baseUrl = 'http://localhost:8089';

const loginForm = document.getElementById('login-form');
loginForm.addEventListener('submit', onLoginHandler);

function onLoginHandler(e) {
    e.preventDefault();
    const form = e.target;
    const data = new FormData(form);
    authenticateUser(data);
}

async function authenticateUser(loginFormData) {
    const loginData = {
        usernameOrEmail: loginFormData.get('usernameOrEmail'),
        password: loginFormData.get('password')
    };
    const settings = {
        method: 'POST',
        cache: 'no-cache',
        mode: 'cors',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(loginData)
    };
    const response = await fetch(baseUrl + '/login', settings);
    const responseData = await response.json();
    console.log(responseData);
}
