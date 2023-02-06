import 'dart:async';

import 'package:firebase_core/firebase_core.dart';
import 'package:firebase_messaging/firebase_messaging.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:overlay_support/overlay_support.dart';
import 'package:pianificatore/models/notifica.dart';
import 'package:pianificatore/providers/auth_providers.dart';
import '../models/push_notification.dart';

class NotificationNotifier extends AsyncNotifier<List<Notifica>> {
  /// Setup firebase e listener per la ricezione notifiche push
  Future<void> initRicezioneNotifiche() async {
    // Print Device Token
    String? token = await FirebaseMessaging.instance.getToken();
    // ignore: avoid_print
    print("TOKEN: $token");

    // 2. Instantiate Firebase Messaging
    FirebaseMessaging messaging = FirebaseMessaging.instance;

    // 3. On iOS, this helps to take the user permissions
    NotificationSettings settings = await messaging.requestPermission(
      alert: true,
      badge: true,
      provisional: false,
      sound: true,
    );

    if (settings.authorizationStatus == AuthorizationStatus.authorized) {
      // For handling the received notifications
      FirebaseMessaging.onMessage.listen((RemoteMessage message) {
        // Parse the message received
        PushNotification notification = PushNotification(
          title: message.data["title"],
          body: message.data["body"],
        );

        print("arrivata notifica ${message.data["title"]}");

        // For displaying the notification as an overlay
        if (notification.title! == "Fine Pianificazione" &&
            ref.read(loginStateProvider).name == "manager") {
          showSimpleNotification(
            Text(notification.title ?? "default"),
            subtitle: Text(notification.body ?? "default"),
            background: Colors.cyan.shade700,
            duration: const Duration(seconds: 2),
          );
          print("inserisco notifica ${notification.title}");
          state.value!.insert(
              0,
              Notifica(
                  descrizione: notification.body ?? "default",
                  titolo: notification.title ?? "default"));
        } else if (notification.title! != "Fine Pianificazione" &&
            ref.read(loginStateProvider).name == "operaio") {
          showSimpleNotification(
            Text(notification.title ?? "default"),
            subtitle: Text(notification.body ?? "default"),
            background: Colors.cyan.shade700,
            duration: const Duration(seconds: 2),
          );
          print("inserisco notifica operaio ${notification.title}");
          state.value!.insert(
              0,
              Notifica(
                  descrizione: notification.body ?? "default",
                  titolo: notification.title ?? "default"));
        }
      });
    } else {
      // ignore: avoid_print
      print('User declined or has not accepted permission');
    }
  }

  @override
  FutureOr<List<Notifica>> build() async {
    await initRicezioneNotifiche();
    return [];
  }
}
