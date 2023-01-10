import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

class RimuoviMacchinaPage extends ConsumerStatefulWidget {
  const RimuoviMacchinaPage({super.key});

  @override
  ConsumerState<RimuoviMacchinaPage> createState() => _RimuoviMacchinaPageState();
}

class _RimuoviMacchinaPageState extends ConsumerState<RimuoviMacchinaPage> {
  late TextEditingController ctr;

  @override
  void initState() {
    ctr = TextEditingController();
    super.initState();
  }

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
      body: Center(
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            // TEXTFIELD ID MACCHINA
            Container(
              margin: const EdgeInsets.only(top: 20, bottom: 30),
              width: MediaQuery.of(context).size.width / 1.2,
              child: TextField(
                controller: ctr,
                style: const TextStyle(fontSize: 15),
                decoration: const InputDecoration(
                  label: Text("Id Macchina da Rimuovere"),
                  enabledBorder: OutlineInputBorder(
                    borderSide: BorderSide(color: Colors.grey, width: 1.0),
                  ),
                ),
              ),
            ),

            // PULSANTE CONFERMA
            SizedBox(
              width: 100,
              child: ElevatedButton(
                onPressed: () {
                  //TODO: aggiungere messaggio toast quando la rimozione è avvenuta
                },
                child: const Text("Elimina"),
              ),
            )
          ],
        ),
      ),
    );
  }
}
