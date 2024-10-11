# Forumeong
A Forum built using Spring-Boot and Firebase.

## How it works

There are three endpoints: /users, /threads and /threads/posts

All of these endpoints have their own crud operations and the data is stored in the Firestore database.

All authentication is handled using Firebase client-side, you'll notice there is no authentication code here. On creating a user, the client-side code posts it into the database for future use.


Read on to see how to get the project working, or to just know how it works.

## The Firestore structure:
There are two main documents that are expected in your firestore: `threads` and `users`.

`threads` should have subdocuments called `posts` 

Overall, this is what they should look like:

```js
post: {
    "id": String,
    "authorName": String,
    "pfpUrl": String,
    "created": Server Timestamp,  // (In ISO 8601) 
    "content": String
}

thread: {
    "id": String,
    "title": String,
    "content": String,
    "authorId": String,
    "authorName": String,
    "pfpUrl": String,
    "created": Server Timestamp,  // (In ISO 8601) 
    "updated": Server Timestamp,  // (In ISO 8601) 
    "category": String,
    "postCount": int,
    "lastPost": Server Timestamp,  // (In ISO 8601) 
}

user: {
    "id": String,
    "username": String,
    "email": String,
    "pfpUrl": String,
    "created": Server Timestamp,  // (In ISO 8601) 
    "bio": String,
    "postsCount": int,
    "threadsCount": int
}

```


## Connecting the Frontend

You can find the frontend repo for the project [here](https://github.com/Sunset-06/Forum-Frontend)

Transfer the contents of the frontend build directory to ```src/main/resources/static```

This makes testing a pain, so I recommend setting up your own testing environment, but I personally just chose to build it everytime I made some progress.

## The service account key:
You need to add the account key that firebase provides [By default called serviceAccountKey.json] to your project. 
Start working immediately by just adding it to the root directory

The Firebase docs describe how to get the key

    To generate a private key file for your service account:

      1. In the Firebase console, open Settings > Service Accounts.

      2. Click Generate New Private Key, then confirm by clicking Generate Key.

      3. Securely store the JSON file containing the key.


Finally, Start the dev server by using:
```bash
mvn spring-boot:run
```
 
