import 'dart:convert';
import 'package:http/http.dart' as http;
import 'package:flutter/material.dart';
import 'package:pianificatore/pages/pianificatore%20pages/pianificazione_macchina_page.dart';
import '../../models/machina.dart';

class VisualizzaPianificazioniPage extends StatefulWidget {
  const VisualizzaPianificazioniPage({super.key});

  @override
  State<VisualizzaPianificazioniPage> createState() => _VisualizzaPianificazioniPageState();
}

class _VisualizzaPianificazioniPageState extends State<VisualizzaPianificazioniPage> {
  Future<List<Macchina>> getMacchine() async {
    List<Macchina> listaMacchine = [];

    var response = await http.get(Uri.parse("http://localhost:8081/macchine"));

    if (response.statusCode == 200) {
      jsonDecode(response.body).forEach((json) {
        Macchina m = Macchina.fromJson(json);
        listaMacchine.add(m);
      });
    }
    return listaMacchine;
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        centerTitle: true,
        title: const Text("Visualizza Pianificazioni"),
        leading: IconButton(
          icon: const Icon(Icons.arrow_back_ios),
          onPressed: () => Navigator.pop(context),
        ),
      ),
      body: FutureBuilder(
        future: getMacchine(),
        builder: ((context, snapshot) {
          if (snapshot.hasData) {
            return ListView.builder(
              itemCount: snapshot.data!.length,
              itemBuilder: ((context, index) => GestureDetector(
                    onTap: () => Navigator.push(
                      context,
                      MaterialPageRoute(
                        builder: (context) => PianificazioneMacchinaPage(
                          macchina: snapshot.data![index].idMacchina,
                        ),
                      ),
                    ),
                    child: Container(
                      margin: const EdgeInsets.all(15),
                      padding: const EdgeInsets.all(8),
                      decoration: BoxDecoration(
                        borderRadius: BorderRadius.circular(10),
                        color: Colors.blue[200],
                      ),
                      child: Text(
                        "Id Macchina:  ${snapshot.data![index].idMacchina}",
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
