const BASE_URL = 'http://localhost:8089';

const loginForm = document.getElementById('login-form');
loginForm.addEventListener('submit', onLoginHandler);

function updateOptions(options) {
    const update = { ...options };
    update.mode = 'cors';
    update.headers = { ...options.headers };
    update.headers['Content-Type'] = 'application/json';
    const user = restoreUser();
    if (user) {
        update.headers['Authorization'] =
            'Basic ' + btoa(user.username + ':' + user.password);
    }
    return update;
}

async function fetchAuthorised(url, options) {
    const settings = options || {};
    const response = await fetch(url, updateOptions(settings));
    return response;
}

async function onLoginHandler(e) {
    e.preventDefault();
    const form = e.target;
    const userFormData = new FormData(form);
    await authenticateUser(userFormData);
    await fetchAuthorised(BASE_URL + '/posts');
}

async function authenticateUser(loginFormData) {
    const loginData = {
        usernameOrEmail: loginFormData.get('username'),
        password: loginFormData.get('password'),
    };
    const settings = {
        method: 'POST',
        cache: 'no-cache',
        mode: 'cors',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(loginData),
    };
    const response = await fetch(BASE_URL + '/authorize', settings);
    const responseData = await response.json();
    saveUser(responseData);
    console.log(responseData);
}

function saveUser(user) {
    const userAsJSON = JSON.stringify(user);
    localStorage.setItem('user', userAsJSON);
}

function restoreUser() {
    const userAsJSON = localStorage.getItem('user');
    const user = JSON.parse(userAsJSON);
    return user;
}
