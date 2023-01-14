import 'dart:async';
import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:pianificatore/models/machina.dart';
import 'package:http/http.dart' as http;
import 'log_macchina_page.dart';

class LogPage extends StatefulWidget {
  const LogPage({super.key});

  @override
  State<LogPage> createState() => _LogPageState();
}

class _LogPageState extends State<LogPage> {
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
        title: const Text("Log Macchine"),
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
                        builder: (context) => LogMacchinaPage(
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
                      child: Column(crossAxisAlignment: CrossAxisAlignment.start, children: [
                        Row(
                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                          children: [
                            // ID MACCHINA
                            Text("Id Macchina:  ${snapshot.data![index].idMacchina}"),

                            // STATO MACCHINA
                            Text(snapshot.data![index].statoMacchina.name)
                          ],
                        ),

                        // ID LOTTO LAVORAZIONE
                        Text("Id Lotto In Lavorazione:  ${snapshot.data![index].codiceLottoInLavorazione}"),

                        // ULTIMO LOGTIME
                        Text("Ultimo Logtime: ${snapshot.data![index].lastTimestamp}"),
                      ]),
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
