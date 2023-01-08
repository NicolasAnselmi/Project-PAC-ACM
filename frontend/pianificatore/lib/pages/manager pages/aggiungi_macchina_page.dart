import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

class AggiungiMacchinaPage extends ConsumerStatefulWidget {
  const AggiungiMacchinaPage({super.key});

  @override
  ConsumerState<AggiungiMacchinaPage> createState() => _AggiungiMacchinaPageState();
}

class _AggiungiMacchinaPageState extends ConsumerState<AggiungiMacchinaPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        centerTitle: true,
        title: const Text("Aggiungi Macchina"),
        leading: IconButton(
          icon: const Icon(Icons.arrow_back_ios),
          onPressed: () => Navigator.pop(context),
        ),
      ),
      body: Container(),
    );
  }
}
