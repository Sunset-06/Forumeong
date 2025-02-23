# Forumeong
A Forum built using Spring-Boot and Firebase. Visit it here: https://forumeong.onrender.com/

## 🔖 About
This is a project that is modeled after older internet forums and message boards, built mostly as a way for me to explore Spring-boot and learn how user facing applications are developed.

### Tech Stack
- Spring-boot
- Firebase Firestore
- Firebase Auth
- React
- Mantine-UI

## 🌑 How it works

There are three endpoints: /users, /threads and /threads/posts

All of these endpoints have their own CRUD operations and the data is stored in Firestore.

All authentication is handled using Firebase on the client-side. On creating a user at the client side, the server posts it into the database for future use, As such all code relating to the authentication is on the [frontend repository](https://github.com/Sunset-06/Forum-Frontend).


Read on to see how to get the project working, or to just know how it works.

## ⤵️ Get It Running

These instructions are for contributing or getting it running for the first time. Before working on it, understand how the application expects the database:

- ### The Firestore structure
There are two main documents that are expected in your firestore: `threads` and `users`.

`threads` should have subdocuments called `posts` 

Overall, this is what they should look like:

```js
post: {
    "id": String,
    "authorName": String,
    "pfpUrl": String,
    "created": Server Timestamp,  
    "content": String
}

thread: {
    "id": String,
    "title": String,
    "content": String,
    "authorId": String,
    "authorName": String,
    "pfpUrl": String,
    "created": Server Timestamp,  
    "updated": Server Timestamp,  
    "category": String,
    "postCount": int,
    "lastPost": Server Timestamp,  
}

user: {
    "id": String,
    "username": String,
    "email": String,
    "pfpUrl": String,
    "created": Server Timestamp,  
    "bio": String,
    "postsCount": int,
    "threadsCount": int
}

```
Once you make your changes, connect the frontend to the application:

- ### Connecting the Frontend

You can find the frontend repo for the project [here](https://github.com/Sunset-06/Forum-Frontend)

Transfer the contents of the frontend build directory to ```src/main/resources/static```

This makes testing a pain, so I recommend setting up your own testing environment, but I personally just chose to build it everytime I made some progress.
(I *really* recommend finding a better way to do it)
(An alternative way is to just create an extra path to test out the frontend elements, which is what I eventually ended up doing, but this discussion is a little out of topic for this document.)


- ### Starting the server

Ensure you have Java 21 and Maven installed on your system. Maven shold take care of all the other dependencies for you.

If you have an older Java version, change the version in the `pom.xml` file (Lines 31 to 33) and run the command 

```bash
mvn clean install
```

Next, you need to have the key to your service account. This is a json file that should be present in your root directory.

The Firebase docs describe how to get the key:

    To generate a private key file for your service account:

      1. In the Firebase console, open Settings > Service Accounts.

      2. Click Generate New Private Key, then confirm by clicking Generate Key.

      3. Securely store the JSON file containing the key.

After this step you need to point the application to the path of the key. You have to specify this path in `config/firebaseConfig.java`

Finally, Start the dev server by using:
```bash
mvn spring-boot:run
```

- ### The pipeline
The pipeline does the following in order:
- Set up java in the container
- Build the .jar file
- Push the image to dockerhub
- Use Render Deployhook to send a redeploy message to Render, the service that I have this currently deployed on.

## 🛡 License

This project is licensed under the **MIT License** – see the [LICENSE](LICENSE) file for details.


  
## 👋Closing note
I once read a medium article where the author stated that they did not have a closing note to add, so instead there was just a video of a samoyed eating a watermelon. I do not have a closing note either so here's a link to the same video:

[ASMR Dog Eating Watermelon](https://www.youtube.com/watch?v=VRmksNNPua8)
