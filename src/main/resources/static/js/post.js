'use strict'
document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('splash-screen').style.display = 'none';
    const user = JSON.parse(localStorage.getItem('user'));
    if (!user) {
        document.getElementById('splash-screen').style.display = 'block';
        document.getElementById('container').style.display = 'none';
    } else {
        createBasePosts().then(function() {
            document.getElementById('splash-screen').style.display = 'none';
            document.getElementById('container').style.display = 'block';
            insertUsernameNavItem();
        });
    }

    $('#login-form').submit(function(event) {
        event.preventDefault();
        const user = {
            username: $('#username-input').val(),
            password: $('#password-input').val()
        };
        localStorage.setItem('user', JSON.stringify(user));
        createBasePosts().then(function() {
            document.getElementById('splash-screen').style.display = 'none';
            document.getElementById('container').style.display = 'block';
        });
    });
});

const logoutButton = document.getElementById('logout-button');
logoutButton.addEventListener('click', onLogoutHandler);

function onLogoutHandler() {
    localStorage.removeItem('user');
}

function insertUsernameNavItem() {
    const nav = document.querySelector('.navbar-nav');
    const username = restoreUser().username;
    const li = document.createElement('li');
    li.textContent = 'Logged in as: ' + username;
    nav.appendChild(li);
}


function addHeart(id) {
    const postIconLike = document.getElementById(id);
    const currentColor = postIconLike.style.color
    if (currentColor === 'red' || currentColor === 'rgb(255, 0,0)') {
        postIconLike.style.color = 'grey';
    } else {
        postIconLike.style.color = 'red';
    }
}

addEventListener('click', addHeart);

document.querySelector(".post-image").addEventListener("dblclick", function (event) {
    const postIconLike = document.querySelector('.bi-heart-fill');
    const currentColor = postIconLike.style.color
    if (currentColor === 'red' || currentColor === 'rgb(255, 0,0)') {
        postIconLike.style.color = 'grey';
    } else {
        postIconLike.style.color = 'red';
    }

    const heart = document.querySelector('.heart');
    heart.style.top = (event.clientY - heart.offsetHeight / 2) + "px";
    heart.style.left = (event.clientX - heart.offsetWidth / 2) + "px";
    heart.style.opacity = "1";
    setTimeout(function () {
        heart.style.opacity = "0";
    }, 1000);
});

addEventListener('click', favoriteIcon);

function favoriteIcon(id) {
    let postIconFavorite = document.getElementById(id);
    const currentColor = postIconFavorite.style.color;
    if (currentColor === 'grey') {
        postIconFavorite.style.color = 'orange';
    } else {
        postIconFavorite.style.color = 'grey';
    }
}

function createPostElement(formData) {
    const reader = new FileReader();
    const allId = formData.get('postId');
    reader.onload = function (e) {
        let postIdInput = document.getElementById('post-id-input');
        postIdInput = postIdInput + 1;

        const postElement = document.createElement('div');
        postElement.classList.add('post');
        postElement.id = postIdInput.value

        const imgElement = document.createElement('img');
        imgElement.classList.add('post-image');
        imgElement.src = e.target.result;
        imgElement.alt = 'post image';
        postElement.appendChild(imgElement);

        const heartContainerElement = document.createElement('div');
        heartContainerElement.classList.add('heart-container');
        postElement.appendChild(heartContainerElement);

        const heartElement = document.createElement('div');
        heartElement.classList.add('heart');
        heartContainerElement.appendChild(heartElement);

        const postLikesElement = document.createElement('div');
        postLikesElement.classList.add('post-likes');
        postLikesElement.id = 'post-likes' + allId;
        postLikesElement.setAttribute('onclick', 'addHeart("post-likes' + allId + '")')
        const svgElement = document.createElementNS('http://www.w3.org/2000/svg', 'svg');
        svgElement.setAttribute('xmlns', 'http://www.w3.org/2000/svg');
        svgElement.setAttribute('width', '30');
        svgElement.setAttribute('height', '30');
        svgElement.setAttribute('fill', 'currentColor');
        svgElement.classList.add('bi', 'bi-heart-fill');
        svgElement.setAttribute('viewBox', '0 0 16 16');
        const pathElement = document.createElementNS('http://www.w3.org/2000/svg', 'path');
        pathElement.setAttribute('fill-rule', 'evenodd');
        pathElement.setAttribute('d', 'M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314z');
        svgElement.appendChild(pathElement);
        postLikesElement.appendChild(svgElement);
        postElement.appendChild(postLikesElement);

        const postCommentsElement = document.createElement('div');
        postCommentsElement.classList.add('post-description');
        const commentsHeadingElement = document.createElement('h2');
        commentsHeadingElement.textContent = 'Описание';
        const descriptionElement = document.createElement('p');
        descriptionElement.textContent = formData.get('description');
        postCommentsElement.appendChild(commentsHeadingElement);
        postElement.appendChild(postCommentsElement);
        postElement.appendChild(descriptionElement);

        const divElement = document.createElement("div");
        divElement.className = "add-to-favorites";
        divElement.id = 'add-to-favorites' + allId;
        divElement.setAttribute("onclick", "favoriteIcon('add-to-favorites" + allId + "')");
        const addToFavoritesSvgElement = document.createElementNS("http://www.w3.org/2000/svg", "svg");
        addToFavoritesSvgElement.classList.add('bi', 'bi-bookmark-fill');
        addToFavoritesSvgElement.id = postIdInput.value;
        addToFavoritesSvgElement.setAttribute("viewBox", "0 0 16 16");
        const favoritePathElement = document.createElementNS("http://www.w3.org/2000/svg", "path");
        favoritePathElement.setAttribute("d", "M2 2v13.5a.5.5 0 0 0 .74.439L8 13.069l5.26 2.87A.5.5 0 0 0 14 15.5V2a2 2 0 0 0-2-2H4a2 2 0 0 0-2 2z");
        addToFavoritesSvgElement.appendChild(favoritePathElement);
        divElement.appendChild(addToFavoritesSvgElement);
        postElement.appendChild(divElement);

        const divElementComment = document.createElement("div");
        divElementComment.className = "post-comment";
        const svgElementComment = document.createElementNS("http://www.w3.org/2000/svg", "svg");
        svgElementComment.setAttribute("width", "30");
        svgElementComment.setAttribute("height", "30");
        svgElementComment.setAttribute("fill", "currentColor");
        svgElementComment.classList.add('bi', 'bi-chat-dots');
        svgElementComment.setAttribute('onclick', 'toggleCommentForm("comment-form' + allId + '","' + 'comment-list' + allId + '")');
        svgElementComment.setAttribute("viewBox", "0 0 16 16");
        const pathElement1 = document.createElementNS("http://www.w3.org/2000/svg", "path");
        pathElement1.setAttribute("d", "M5 8a1 1 0 1 1-2 0 1 1 0 0 1 2 0zm4 0a1 1 0 1 1-2 0 1 1 0 0 1 2 0zm3 1a1 1 0 1 0 0-2 1 1 0 0 0 0 2z");
        const pathElement2 = document.createElementNS("http://www.w3.org/2000/svg", "path");
        pathElement2.setAttribute("d", "m2.165 15.803.02-.004c1.83-.363 2.948-.842 3.468-1.105A9.06 9.06 0 0 0 8 15c4.418 0 8-3.134 8-7s-3.582-7-8-7-8 3.134-8 7c0 1.76.743 3.37 1.97 4.6a10.437 10.437 0 0 1-.524 2.318l-.003.011a10.722 10.722 0 0 1-.244.637c-.079.186.074.394.273.362a21.673 21.673 0 0 0 .693-.125zm.8-3.108a1 1 0 0 0-.287-.801C1.618 10.83 1 9.468 1 8c0-3.192 3.004-6 7-6s7 2.808 7 6c0 3.193-3.004 6-7 6a8.06 8.06 0 0 1-2.088-.272 1 1 0 0 0-.711.074c-.387.196-1.24.57-2.634.893a10.97 10.97 0 0 0 .398-2z");
        svgElementComment.appendChild(pathElement1);
        svgElementComment.appendChild(pathElement2);
        divElementComment.appendChild(svgElementComment);
        postElement.appendChild(divElementComment)

        const commentFormDiv = document.createElement("div");
        commentFormDiv.setAttribute("id", "comment-form" + allId);
        commentFormDiv.setAttribute("style", "display: none");
        const commentForm = document.createElement("form");
        commentForm.setAttribute("onsubmit", "submitComment(event" + ", 'comment-text" + allId + "', 'comment-list" + allId + "')");

        const userIdInput = document.createElement("input");
        userIdInput.setAttribute("type", "hidden");
        userIdInput.setAttribute("id", "user-id" + allId);
        userIdInput.setAttribute("value", "1");

        const inputElement = document.createElement("input");
        inputElement.setAttribute("type", "hidden");
        inputElement.setAttribute("id", "post-id" + allId);
        inputElement.setAttribute("value", "" + allId);

        const commentTextLabel = document.createElement("label");
        commentTextLabel.setAttribute("for", "comment-text" + allId);
        commentTextLabel.textContent = "Comment Text:";

        const commentTextArea = document.createElement("textarea");
        commentTextArea.setAttribute("id", "comment-text" + allId);
        commentTextArea.setAttribute("rows", "4");
        commentTextArea.setAttribute("cols", "50");

        const submitButton = document.createElement("input");
        submitButton.setAttribute("type", "submit");
        submitButton.setAttribute("value", "Send");

        commentForm.appendChild(userIdInput);
        commentForm.appendChild(inputElement);
        commentForm.appendChild(commentTextLabel);
        commentForm.appendChild(commentTextArea);
        commentForm.appendChild(submitButton);

        commentFormDiv.appendChild(commentForm);

        const commentListDiv = document.createElement("div");
        commentListDiv.setAttribute("id", "comment-list" + allId);
        commentListDiv.setAttribute("style", "display: none");

        divElementComment.appendChild(commentFormDiv);
        divElementComment.appendChild(commentListDiv);


        insertPostElement(postElement);
    }
    reader.readAsDataURL(formData.get('imageLink'))
}

function createCommentElement(formData, list) {
    const p = document.createElement('p');
    p.textContent = formData.get('commentText');

    const containerElement = document.getElementById(list);
    containerElement.appendChild(p);
}

async function submitComment(event, id, list) {
    event.preventDefault();
    const userId = document.getElementById('user-id').value;
    const postId = document.getElementById('post-id').value;
    const commentText = document.getElementById(id).value;

    let formData = new FormData();
    formData.append('userId', userId);
    formData.append('postId', postId);
    formData.append('commentText', commentText);

    createCommentElement(formData, list);

    await fetch('http://localhost:8089/publications/comment', {
        method: 'POST',
        body: formData
    });
}


async function createBasePosts() {
    const response = await fetch('http://localhost:8089/watch');
    const postsList = await response.json();

    const responseComm = await fetch('http://localhost:8089/comments');
    const commentList = await responseComm.json();

    for (const post of postsList) {
        const imageUrl = '/images/' + post.imageLink;
        const imageBlob = await fetch(imageUrl).then(response => response.blob());
        const imageFile = new File([imageBlob], post.imageLink);
        const formData = new FormData();
        formData.append('userId', post.user_id);
        formData.append('postId', post.id);
        formData.append('commentText', post.description);
        formData.append('imageLink', imageFile);
        createPostElement(formData);
    }
    for (const comment of commentList) {
        const formData = new FormData();
        formData.append('userId', comment.user_id);
        formData.append('postId', comment.publication_id);
        formData.append('commentText', comment.commentText);

        createCommentElement(formData, "comment-list" + comment.publication_id)
    }
}



async function getId() {
    return await fetch('http://localhost:8089/get-id');
}

function toggleCommentForm(form, list) {
    const commentForm = document.getElementById(form);
    const commentList = document.getElementById(list);
    if (commentForm.style.display === 'none') {
        commentList.style.display = 'block';
        commentForm.style.display = 'block';
    } else {
        commentList.style.display = 'none';
        commentForm.style.display = 'none';
    }
}

function insertPostElement(postElement) {
    const containerElement = document.getElementById('posts-container');
    containerElement.appendChild(postElement);
}

document.getElementById('post-form').onsubmit = async (e) => {
    e.preventDefault();
    let imageInput = document.getElementById('image-input');
    let descriptionInput = document.getElementById('description-input');
    let userIdInput = document.getElementById('user-id-input');
    const id = getId() + 1;
    let formData = new FormData();
    formData.append('imageLink', imageInput.files[0]);
    formData.append('description', descriptionInput.value);
    formData.append('user_id', userIdInput.value);
    formData.append('postId', id);
    await fetch('http://localhost:8089/publications/add', {
        method: 'POST',
        body: formData
    });
    createPostElement(formData)
    alert('post created successfully');
}
