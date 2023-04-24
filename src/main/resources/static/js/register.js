const baseUrl = 'http://localhost:8089';

const registrationForm = document.getElementById('registration-form');
registrationForm.addEventListener('submit', onRegisterHandler);

function onRegisterHandler(e) {
    e.preventDefault();
    const form = e.target;
    const data = new FormData(form);
    createUser(data);
}

async function createUser(userFormData) {
    const user = {
        username: userFormData.get('username'),
        email: userFormData.get('email'),
        password: userFormData.get('password')
    };
    const settings = {
        method: 'POST',
        cache: 'no-cache',
        mode: 'cors',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user)
    };
    try {
        const response = await fetch(baseUrl + '/sign-up', settings);
        const responseData = await response.json();
        console.log(responseData);
    } catch (err) {
        console.log(err);
    }
}
