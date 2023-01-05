import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:pianificatore/pages/pianificatore%20pages/avvia_pianificazione_page.dart';
import 'package:pianificatore/pages/manager%20pages/gestisci_macchine_page.dart';
import 'package:pianificatore/pages/manager%20pages/gestisci_utenti_page.dart';
import 'package:pianificatore/pages/pianificatore%20pages/inserisci_lotti_page.dart';
import 'package:pianificatore/pages/operaio%20pages/reports.dart';
import 'package:pianificatore/pages/pianificatore%20pages/visualizza_lotti_page.dart';
import 'package:pianificatore/pages/pianificatore%20pages/visualizza_pianificazioni_page.dart';
import 'package:pianificatore/providers/auth_providers.dart';
import 'package:pianificatore/utils/enums.dart';
import 'package:pianificatore/widgets/pulsante_sezioni_home.dart';

/// PAGINA HOME APPLICAZIONE. CONTENUTO MOSTRATO IN BASE ALLA TIPOLOGIA DI UTENTE
class RootPage extends ConsumerStatefulWidget {
  const RootPage({super.key});

  @override
  ConsumerState<RootPage> createState() => _RootPageState();
}

class _RootPageState extends ConsumerState<RootPage> {
  final List<Widget> listaWidget = [];
  final Map<String, Map<String, Widget>> listaPermessi = {
    "manager": {
      "Inserisci Lotti": const InserisciLottiPage(),
      "Visualizza Lotti": const VisualizzaLottiPage(),
      "Avvia Pianificazione": const AvviaPianificazionePage(),
      "Visualizza Pianificazioni": const VisualizzaPianificazioniPage(),
      "Gestisci Utenti": const GestisciUtentiPage(),
      "Gestisci Macchine": const GestisciMacchinePage(),
    },
    "pianificatore": {
      "Inserisci Lotti": const InserisciLottiPage(),
      "Visualizza Lotti": const VisualizzaLottiPage(),
      "Avvia Pianificazione": const AvviaPianificazionePage(),
      "Visualizza Pianificazioni": const VisualizzaPianificazioniPage(),
    },
    "operaio": {
      "Reports": const ReportsPage(),
    }
  };

  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        leadingWidth: 150,
        title: const Text("Home"),
        centerTitle: true,
        // PULSANTE LOGOUT
        leading: Container(
          margin: const EdgeInsets.all(8),
          child: ElevatedButton(
            style: ElevatedButton.styleFrom(
              backgroundColor: Colors.blue[200],
            ),
            onPressed: () {
              ref.read(loginStateProvider.notifier).logout();
            },
            child: const Text("Logout"),
          ),
        ),

        // PULSANTE MAILBOX
        actions: [
          ref.watch(loginStateProvider) != UserStatus.pianificatore
              ? Container(
                  margin: const EdgeInsets.only(right: 16),
                  child: IconButton(
                    padding: EdgeInsets.zero,
                    onPressed: () {},
                    icon: const Icon(
                      Icons.email,
                      size: kToolbarHeight - 8,
                    ),
                  ),
                )
              : const SizedBox()
        ],
      ),
      body: ListView.builder(
        itemBuilder: (context, index) => PulsanteSezioniHome(
          titolo: listaPermessi[ref.watch(loginStateProvider).name]!.keys.elementAt(index),
          pagina: listaPermessi[ref.watch(loginStateProvider).name]!.values.elementAt(index),
        ),
        itemCount: listaPermessi[ref.watch(loginStateProvider).name]!.length,
      ),
    );
  }
}
