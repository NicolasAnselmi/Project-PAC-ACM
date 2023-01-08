import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

class RimuoviMacchinaPage extends ConsumerStatefulWidget {
  const RimuoviMacchinaPage({super.key});

  @override
  ConsumerState<RimuoviMacchinaPage> createState() => _RimuoviMacchinaPageState();
}

class _RimuoviMacchinaPageState extends ConsumerState<RimuoviMacchinaPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        centerTitle: true,
        title: const Text("Rimuovi Macchina"),
        leading: IconButton(
          icon: const Icon(Icons.arrow_back_ios),
          onPressed: () => Navigator.pop(context),
        ),
      ),
      body: Container(),
    );
  }
}
