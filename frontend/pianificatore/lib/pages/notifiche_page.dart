import 'package:flutter/material.dart';

class NotifichePage extends StatefulWidget {
  const NotifichePage({super.key});

  @override
  State<NotifichePage> createState() => _NotifichePageState();
}

class _NotifichePageState extends State<NotifichePage> {
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
      body: ListView.builder(
        itemBuilder: ((context, index) => SizedBox()),
        itemCount: 3,
      ),
    );
  }
}
