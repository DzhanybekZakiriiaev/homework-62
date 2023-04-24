<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Homepage</title>
    <link rel="stylesheet" href="/css/styles.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
          integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
            crossorigin="anonymous"></script>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="http://localhost:8089">My Website</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a class="nav-link" href="http://localhost:8089/register">Register</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="http://localhost:8089/my-login-page">Login</a>
            </li>
        </ul>
    </div>
</nav>


<div class="container">
    <div class="posts-container" id="posts-container">
        <div class="post">
            <img class="post-image"
                 src="https://i.pinimg.com/564x/7b/e8/0e/7be80e1dd312352fb3616ff285f18037.jpg"
                 alt="post image">
            <div class="heart-container">
                <div class="heart"></div>
            </div>
            <div class="post-likes"  id="post-likes" onclick="addHeart('post-likes')">
                <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor"
                     class="bi bi-heart-fill" viewBox="0 0 16 16">
                    <path fill-rule="evenodd"
                          d="M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314z"/>
                </svg>
            </div>
            <div class="post-description">
                <h2>Описание</h2>
                <p>descriodasdsad</p>
            </div>
            <div class="add-to-favorites" id="add-to-favorites1" onclick="favoriteIcon('add-to-favorites1')">
                <svg xmlns="http://www.w3.org/2000/svg"
                     class="bi bi-bookmark-fill" viewBox="0 0 16 16">
                    <path d="M2 2v13.5a.5.5 0 0 0 .74.439L8 13.069l5.26 2.87A.5.5 0 0 0 14 15.5V2a2 2 0 0 0-2-2H4a2 2 0 0 0-2 2z"/>
                </svg>
            </div>
            <div class="post-comment">
                <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor"
                     class="bi bi-chat-dots" viewBox="0 0 16 16"
                     onclick="toggleCommentForm('comment-form', 'comment-list')">
                    <path d="M5 8a1 1 0 1 1-2 0 1 1 0 0 1 2 0zm4 0a1 1 0 1 1-2 0 1 1 0 0 1 2 0zm3 1a1 1 0 1 0 0-2 1 1 0 0 0 0 2z"/>
                    <path d="m2.165 15.803.02-.004c1.83-.363 2.948-.842 3.468-1.105A9.06 9.06 0 0 0 8 15c4.418 0 8-3.134 8-7s-3.582-7-8-7-8 3.134-8 7c0 1.76.743 3.37 1.97 4.6a10.437 10.437 0 0 1-.524 2.318l-.003.011a10.722 10.722 0 0 1-.244.637c-.079.186.074.394.273.362a21.673 21.673 0 0 0 .693-.125zm.8-3.108a1 1 0 0 0-.287-.801C1.618 10.83 1 9.468 1 8c0-3.192 3.004-6 7-6s7 2.808 7 6c0 3.193-3.004 6-7 6a8.06 8.06 0 0 1-2.088-.272 1 1 0 0 0-.711.074c-.387.196-1.24.57-2.634.893a10.97 10.97 0 0 0 .398-2z"/>
                </svg>
                <div id="comment-form" style="display: none">
                    <form onsubmit="submitComment(event, 'comment-text', 'comment-list')">
                        <input type="hidden" id="user-id" value="1">
                        <input type="hidden" id="post-id" value="1">
                        <label for="comment-text"></label><textarea id="comment-text" rows="4" cols="50"></textarea>
                        <input type="submit" value="Send">
                    </form>
                </div>
                <div id="comment-list" style="display: none">
                </div>
            </div>
        </div>
    </div>
    <form id="post-form" enctype="multipart/form-data">
        <label for="image-input">Image</label>
        <input type="file" id="image-input" name="imageLink">
        <br>
        <label for="description-input">Description</label>
        <textarea id="description-input" name="description"></textarea>
        <br>
        <input type="hidden" id="user-id-input" name="user_id" value="1">
        <input type="hidden" id="post-id-input" name="post_id" value="1">
        <input type="submit" value="create">
    </form>
</div>
<script src="/js/post.js"></script>
</body>
</html>



