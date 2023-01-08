import 'package:flutter/material.dart';
import 'package:pianificatore/pages/manager%20pages/aggiungi_macchina_page.dart';
import 'package:pianificatore/pages/manager%20pages/rimuovi_macchina_page.dart';
import 'package:pianificatore/widgets/pulsante_sezioni_home.dart';

class GestisciMacchinePage extends StatefulWidget {
  const GestisciMacchinePage({super.key});

  @override
  State<GestisciMacchinePage> createState() => _GestisciMacchinePageState();
}

class _GestisciMacchinePageState extends State<GestisciMacchinePage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        centerTitle: true,
        title: const Text("Gestisci Macchine"),
        leading: IconButton(
          icon: const Icon(Icons.arrow_back_ios),
          onPressed: () => Navigator.pop(context),
        ),
      ),
      body: Column(
        children: const [
          // AGGIUNGI MACCHINA
          SizedBox(
            width: double.infinity,
            child: PulsanteSezioniHome(
              titolo: "Aggiungi Macchina",
              pagina: AggiungiMacchinaPage(),
            ),
          ),

          // RIMUOVI MACCHINA
          SizedBox(
            width: double.infinity,
            child: PulsanteSezioniHome(
              titolo: "Rimuovi Macchina",
              pagina: RimuoviMacchinaPage(),
            ),
          ),
        ],
      ),
    );
  }
}
