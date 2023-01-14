import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:pianificatore/models/lotto.dart';
import 'package:http/http.dart' as http;

class VisualizzaLottiPage extends StatefulWidget {
  const VisualizzaLottiPage({super.key});

  @override
  State<VisualizzaLottiPage> createState() => _VisualizzaLottiPageState();
}

class _VisualizzaLottiPageState extends State<VisualizzaLottiPage> {
  Future<List<Lotto>> getLotti() async {
    List<Lotto> listaLotti = [];
    var response = await http.get(Uri.parse("http://localhost:8081/pianificazione/residui"));

    if (response.statusCode == 200) {
      jsonDecode(response.body).forEach((json) {
        Lotto m = Lotto.fromJson(json);
        listaLotti.add(m);
      });
    }

    return listaLotti;
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        centerTitle: true,
        title: const Text("Visualizza Lotti"),
        leading: IconButton(
          icon: const Icon(Icons.arrow_back_ios),
          onPressed: () => Navigator.pop(context),
        ),
      ),
      body: FutureBuilder(
        future: getLotti(),
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
                        // ID LOTTO
                        Text("ID Lotto:  ${snapshot.data![index].idLotto}"),

                        // ID PRODOTTO
                        Text(snapshot.data![index].idProdotto),

                        // N PEZZI
                        Text("N Pezzi:  ${snapshot.data![index].nPezzi}"),

                        // PRIORITA
                        Text("Priorità: ${snapshot.data![index].priorita.name}"),
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
