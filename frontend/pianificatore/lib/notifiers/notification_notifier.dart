import 'package:firebase_core/firebase_core.dart';
import 'package:firebase_messaging/firebase_messaging.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:overlay_support/overlay_support.dart';
import 'package:pianificatore/models/notifica.dart';

import '../pages/root_page.dart';

class NotificationNotifier extends Notifier<List<Notifica>> {
  @override
  List<Notifica> build() {
    return [];
  }

  /// Setup firebase e listener per la ricezione notifiche push
  Future<void> initRicezioneNotifiche() async {
    await Firebase.initializeApp();

    // 2. Instantiate Firebase Messaging
    FirebaseMessaging _messaging = FirebaseMessaging.instance;

    // 3. On iOS, this helps to take the user permissions
    NotificationSettings settings = await _messaging.requestPermission(
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
          title: message.notification?.title,
          body: message.notification?.body,
        );

        state.add(Notifica(descrizione: notification.body!, titolo: notification.title!));

        // For displaying the notification as an overlay
        showSimpleNotification(
          Text(notification.title!),
          leading: const Text("CIAO"),
          subtitle: Text(notification.body!),
          background: Colors.cyan.shade700,
          duration: const Duration(seconds: 2),
        );
      });
    } else {
      print('User declined or has not accepted permission');
    }
  }
}
