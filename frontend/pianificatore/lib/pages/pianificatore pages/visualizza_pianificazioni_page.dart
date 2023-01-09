import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:pianificatore/models/pianificazione.dart';
import 'package:pianificatore/pages/pianificatore%20pages/pianificazione_page.dart';

class VisualizzaPianificazioniPage extends StatefulWidget {
  const VisualizzaPianificazioniPage({super.key});

  @override
  State<VisualizzaPianificazioniPage> createState() => _VisualizzaPianificazioniPageState();
}

class _VisualizzaPianificazioniPageState extends State<VisualizzaPianificazioniPage> {
  Future<List<Pianificazione>> getReports() async {
    // TODO: rimuovere
    await Future.delayed(const Duration(seconds: 2));
    return [
      Pianificazione(),
      Pianificazione(),
      Pianificazione(),
      Pianificazione(),
    ];
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        centerTitle: true,
        title: const Text("Pianificazioni"),
        leading: IconButton(
          icon: const Icon(Icons.arrow_back_ios),
          onPressed: () => Navigator.pop(context),
        ),
      ),
      body: FutureBuilder(
        future: getReports(),
        builder: ((context, snapshot) {
          if (snapshot.hasData) {
            return ListView.builder(
              itemCount: snapshot.data!.length,
              itemBuilder: ((context, index) => GestureDetector(
                    onTap: () => Navigator.push(
                      context,
                      CupertinoPageRoute(builder: (context) => const PianificazionePage()),
                    ),
                    child: Container(
                      margin: const EdgeInsets.all(15),
                      padding: const EdgeInsets.all(8),
                      decoration: BoxDecoration(
                        borderRadius: BorderRadius.circular(10),
                        color: Colors.blue[200],
                      ),
                      child: const Center(
                        child: Text("PIANO"),
                      ),
                    ),
                  )),
            );
          } else {
            return const Center(
              child: CircularProgressIndicator(),
            );
          }
        }),
      ),
    );
  }
}
