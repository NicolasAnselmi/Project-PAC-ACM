import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import '../../models/lavorazione.dart';

class PianificazioneMacchinaPage extends StatefulWidget {
  final String macchina;
  const PianificazioneMacchinaPage({super.key, required this.macchina});

  @override
  State<PianificazioneMacchinaPage> createState() => _PianificazioneMacchinaPageState();
}

class _PianificazioneMacchinaPageState extends State<PianificazioneMacchinaPage> {
  Future<List<Lavorazione>> getLavorazioni() async {
    List<Lavorazione> listaLavorazioni = [];
    var response = await http.get(Uri.parse("http://localhost:8081/pianificazione/idMacchina/${widget.macchina}"));

    if (response.statusCode == 200) {
      jsonDecode(response.body).forEach((json) {
        Lavorazione m = Lavorazione.fromJson(json);
        listaLavorazioni.add(m);
      });
    }

    return listaLavorazioni;
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        centerTitle: true,
        title: Text("Macchina: ${widget.macchina}"),
        leading: IconButton(
          icon: const Icon(Icons.arrow_back_ios),
          onPressed: () => Navigator.pop(context),
        ),
      ),
      body: FutureBuilder(
        future: getLavorazioni(),
        builder: ((context, snapshot) {
          if (snapshot.hasData) {
            return ListView.builder(
              itemCount: snapshot.data!.length,
              itemBuilder: ((context, index) => Container(
                    margin: const EdgeInsets.all(15),
                    padding: const EdgeInsets.all(8),
                    decoration: BoxDecoration(
                      borderRadius: BorderRadius.circular(10),
                      color: Colors.blue[200],
                    ),
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        // IDLavorazione
                        Text("Id Lavorazione:  ${snapshot.data![index].idLavorazione}"),

                        // SLOT
                        Text("SLOT: ${snapshot.data![index].slot}"),

                        // LOTTO
                        Text("Lotto:  ${snapshot.data![index].lotto}"),

                        // TipoMacchina
                        Text("Tipo Macchina: ${snapshot.data![index].tipoMacchina.name}"),
                      ],
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
