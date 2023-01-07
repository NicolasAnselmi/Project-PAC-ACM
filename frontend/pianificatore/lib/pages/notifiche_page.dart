import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:pianificatore/providers/notica_provider.dart';

class NotifichePage extends ConsumerStatefulWidget {
  const NotifichePage({super.key});

  @override
  ConsumerState<NotifichePage> createState() => _NotifichePageState();
}

class _NotifichePageState extends ConsumerState<NotifichePage> {
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
        itemBuilder: ((context, index) => Container(
              decoration: BoxDecoration(
                borderRadius: BorderRadius.circular(20),
                color: Colors.blue[800],
              ),
              margin: const EdgeInsets.all(10),
              padding: const EdgeInsets.only(right: 15, top: 10, bottom: 10, left: 15),
              height: 100,
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  // TITOLO
                  Expanded(
                    child: Text(
                      ref.watch(notificaProvider)[index].titolo,
                      style: const TextStyle(fontSize: 20),
                    ),
                  ),

                  // DESCRIZIONE NOTIFICA
                  Expanded(flex: 2, child: Text(ref.watch(notificaProvider)[index].descrizione)),
                ],
              ),
            )),
        itemCount: ref.watch(notificaProvider).length,
      ),
    );
  }
}
