package ru.netology.pusher

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import java.io.FileInputStream


fun main() {
    val options = FirebaseOptions.builder()
        .setCredentials(
            GoogleCredentials
                .fromStream(FileInputStream("fcm.json"))
        )
        .build()

    FirebaseApp.initializeApp(options)

    var message = Message.builder()
        .putData("action", "LIKE")
        .putData(
            "content", """{
          "userId": 1,
          "userName": "Vasiliy",
          "postId": 2,
          "postAuthor": "Netology"
        }""".trimIndent()
        )
        .setToken(Constance.TOKEN)
        .build()

    FirebaseMessaging.getInstance().send(message)

    message = Message.builder()
        .putData("action", "NEW_POST")
        .putData(
            "content", """{
          "userId": 2,
          "userName": "Anna",
          "postId": 5,
          "text": "На сегодняшнем совещании было решено:..."
        }""".trimIndent()
        )
        .setToken(Constance.TOKEN)
        .build()

    FirebaseMessaging.getInstance().send(message)

    message = Message.builder()
        .putData("action", "old")
        .putData(
            "content", """{
          "userId": 2,
          "userName": "Anna",
          "postId": 5,
          "text": "На сегодняшнем совещании было решено:..."
        }""".trimIndent()
        )
        .setToken(Constance.TOKEN)
        .build()

    FirebaseMessaging.getInstance().send(message)
}
