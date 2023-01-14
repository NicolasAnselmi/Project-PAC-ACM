import 'dart:convert';
import 'package:fluttertoast/fluttertoast.dart';
import 'package:http/http.dart' as http;
import 'package:flutter/material.dart';
import 'package:pianificatore/models/machina.dart';

class AvviaPianificazionePage extends StatefulWidget {
  const AvviaPianificazionePage({super.key});

  @override
  State<AvviaPianificazionePage> createState() => _AvviaPianificazionePageState();
}

class _AvviaPianificazionePageState extends State<AvviaPianificazionePage> {
  Map<String, bool> listaMacchineSelezionate = {};
  late TextEditingController slotCtr;
  late FToast ftoast;

  @override
  void initState() {
    slotCtr = TextEditingController();
    ftoast = FToast();
    ftoast.init(context);
    super.initState();
  }

  _showToast() {
    Widget toast = Container(
      padding: const EdgeInsets.symmetric(horizontal: 24.0, vertical: 12.0),
      decoration: BoxDecoration(
        borderRadius: BorderRadius.circular(25.0),
        color: Colors.greenAccent,
      ),
      child: Row(
        mainAxisSize: MainAxisSize.min,
        children: const [
          Icon(Icons.check),
          SizedBox(
            width: 12.0,
          ),
          Text("Pianificazione Avviata Correttamente"),
        ],
      ),
    );

    ftoast.showToast(
      child: toast,
      gravity: ToastGravity.BOTTOM,
      toastDuration: const Duration(seconds: 4),
    );
  }

  Future<List<Macchina>> getMacchine() async {
    List<Macchina> listaMacchine = [];
    var response = await http.get(Uri.parse("http://localhost:8081/macchine"));

    if (response.statusCode == 200) {
      for (Map<String, dynamic> el in jsonDecode(response.body)) {
        Macchina m = Macchina.fromJson(el);
        listaMacchine.add(m);
        listaMacchineSelezionate.putIfAbsent(m.idMacchina, () => false);
      }
    }
    return listaMacchine;
  }

  Future<void> avvioCalcolo() async {
    var stringaMacchine = "";
    listaMacchineSelezionate.forEach((key, val) {
      if (val) {
        stringaMacchine += "$key,";
      }
    });
    var params = {
      "slotSettimanali": slotCtr.text,
      "listaMacchine": stringaMacchine.substring(0, stringaMacchine.length - 1),
    };
    var response = await http.get(Uri.http("localhost:8081", "/pianificazione/calcolo", params));
    if (response.statusCode == 200) {
      _showToast();
      if (mounted) Navigator.pop(context);
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        centerTitle: true,
        title: const Text("Avvia Pianificazione"),
        leading: IconButton(
          icon: const Icon(Icons.arrow_back_ios),
          onPressed: () => Navigator.pop(context),
        ),
      ),
      body: FutureBuilder(
        future: getMacchine(),
        builder: ((context, snapshot) {
          if (snapshot.hasData) {
            return Column(
              children: [
                Expanded(
                  flex: 5,
                  child: ListView.builder(
                    itemCount: snapshot.data!.length,
                    itemBuilder: ((context, index) => Container(
                        height: 50,
                        margin: const EdgeInsets.all(10),
                        padding: const EdgeInsets.all(8),
                        decoration: BoxDecoration(
                          borderRadius: BorderRadius.circular(10),
                          color: Colors.blue[200],
                        ),
                        child: Row(
                          mainAxisAlignment: MainAxisAlignment.spaceAround,
                          children: [
                            // ID MACCHINA
                            Text(snapshot.data![index].idMacchina),

                            // CHECKBOX
                            Checkbox(
                                value: listaMacchineSelezionate[snapshot.data![index].idMacchina],
                                onChanged: (val) {
                                  setState(() {
                                    listaMacchineSelezionate[snapshot.data![index].idMacchina] = val ?? false;
                                  });
                                })
                          ],
                        ))),
                  ),
                ),

                // TEXTFIELD + LABEL SLOT SETTIMANALI
                Expanded(
                  child: Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      const Text("Slot Settimanali: "),
                      Container(
                        width: 60,
                        margin: const EdgeInsets.only(left: 10),
                        child: TextField(
                          controller: slotCtr,
                          style: const TextStyle(fontSize: 20),
                          decoration: const InputDecoration(
                            enabledBorder: OutlineInputBorder(
                              borderSide: BorderSide(color: Colors.grey, width: 1.0),
                            ),
                          ),
                        ),
                      ),
                    ],
                  ),
                ),

                // PULSANTE AVVIO PIANIFICAZIONE
                Expanded(
                  child: Container(
                    margin: const EdgeInsets.only(bottom: 30),
                    child: ElevatedButton(
                      child: const Text("Avvia Pianificazione"),
                      onPressed: () => avvioCalcolo(),
                    ),
                  ),
                )
              ],
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
