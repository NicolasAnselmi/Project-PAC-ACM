import 'package:flutter/material.dart';

class GestisciUtentiPage extends StatefulWidget {
  const GestisciUtentiPage({super.key});

  @override
  State<GestisciUtentiPage> createState() => _GestisciUtentiPageState();
}

class _GestisciUtentiPageState extends State<GestisciUtentiPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        centerTitle: true,
        title: const Text("Gestisci Utenti"),
        leading: IconButton(
          icon: const Icon(Icons.arrow_back_ios),
          onPressed: () => Navigator.pop(context),
        ),
      ),
      body: const SizedBox(),
    );
  }
}
