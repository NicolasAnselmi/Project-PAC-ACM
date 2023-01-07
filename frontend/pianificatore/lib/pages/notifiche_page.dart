import 'package:firebase_core/firebase_core.dart';
import 'package:firebase_messaging/firebase_messaging.dart';
import 'package:flutter/material.dart';

class NotifichePage extends StatefulWidget {
  const NotifichePage({super.key});

  @override
  State<NotifichePage> createState() => _NotifichePageState();
}

class _NotifichePageState extends State<NotifichePage> {
  Future<void> foo() async {
    await Firebase.initializeApp();
    print("CiO");
    String? token = await FirebaseMessaging.instance.getToken();
    print("TOK");
    print(token);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          centerTitle: true,
          title: const Text("Notifiche"),
          leading: IconButton(
            icon: const Icon(Icons.arrow_back_ios),
            onPressed: () => Navigator.pop(context),
          ),
        ),
        body: FutureBuilder(
          future: foo(),
          builder: ((context, snapshot) => Container(
                child: Center(child: Text("Cio")),
              )),
        ));
  }
}
