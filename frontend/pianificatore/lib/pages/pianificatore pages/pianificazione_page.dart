import 'package:flutter/material.dart';

class PianificazionePage extends StatefulWidget {
  const PianificazionePage({super.key});

  @override
  State<PianificazionePage> createState() => _PianificazionePageState();
}

class _PianificazionePageState extends State<PianificazionePage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        centerTitle: true,
        title: const Text("Pianificazione"),
        leading: IconButton(
          icon: const Icon(Icons.arrow_back_ios),
          onPressed: () => Navigator.pop(context),
        ),
      ),
      body: const Center(
        child: Text("PIANIFICAZIONE"),
      ),
    );
  }
}
