import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:pianificatore/pages/pianificatore%20pages/visualizza_pianificazioni_page.dart';
import 'package:pianificatore/providers/auth_providers.dart';
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
              padding: const EdgeInsets.only(
                  right: 15, top: 10, bottom: 10, left: 15),
              height: ref.watch(notificaProvider).value![index].titolo ==
                      "Fine Pianificazione"
                  ? 150
                  : 100,
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Row(
                    children: [
                      // TITOLO
                      Expanded(
                        child: Text(
                          ref.watch(notificaProvider).value![index].titolo,
                          style: const TextStyle(fontSize: 20),
                        ),
                      ),

                      // PULSANTE VISUALIZZA
                      ref.watch(notificaProvider).value![index].titolo ==
                              "Fine Pianificazione"
                          ? SizedBox(
                              height: 30,
                              width: 100,
                              child: ElevatedButton(
                                style: ElevatedButton.styleFrom(
                                  backgroundColor: Colors.blue[300],
                                ),
                                onPressed: () => Navigator.push(
                                  context,
                                  CupertinoPageRoute(
                                    builder: ((context) =>
                                        const VisualizzaPianificazioniPage()),
                                  ),
                                ),
                                child: const Text("Visualizza"),
                              ),
                            )
                          : const SizedBox(),
                    ],
                  ),

                  // DESCRIZIONE NOTIFICA
                  Expanded(
                    flex: 2,
                    child: Text(
                        ref.watch(notificaProvider).value![index].descrizione),
                  ),

                  // PULSANTI CONFERMA / RIFIUTA (solo per notifiche fine pianificazione)
                  ref.watch(notificaProvider).value![index].titolo ==
                          "Fine Pianificazione"
                      ? Expanded(
                          child: Row(
                            mainAxisAlignment: MainAxisAlignment.center,
                            children: [
                              // RIFIUTA
                              SizedBox(
                                width: 120,
                                child: ElevatedButton(
                                  onPressed: () {
                                    setState(() {
                                      ref
                                          .read(notificaProvider)
                                          .value!
                                          .removeWhere((element) =>
                                              element.titolo ==
                                              ref
                                                  .watch(notificaProvider)
                                                  .value![index]
                                                  .titolo);
                                    });
                                  },
                                  child: const Text("Rifiuta"),
                                ),
                              ),
                              const SizedBox(
                                width: 50,
                              ),

                              // CONFERMA
                              SizedBox(
                                width: 120,
                                child: ElevatedButton(
                                  onPressed: () {
                                    setState(() {
                                      ref
                                          .read(notificaProvider)
                                          .value!
                                          .removeWhere((element) =>
                                              element.titolo ==
                                              ref
                                                  .watch(notificaProvider)
                                                  .value![index]
                                                  .titolo);
                                    });
                                  },
                                  child: const Text("Conferma"),
                                ),
                              )
                            ],
                          ),
                        )
                      : const SizedBox()
                ],
              ),
            )),
        itemCount: ref.watch(notificaProvider).value!.length,
      ),
    );
  }
}
