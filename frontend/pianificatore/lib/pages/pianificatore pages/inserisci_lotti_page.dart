import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:pianificatore/models/machina.dart';
import 'package:pianificatore/utils/enums.dart';
import 'package:fluttertoast/fluttertoast.dart';

class InserisciLottiPage extends StatefulWidget {
  const InserisciLottiPage({super.key});
  @override
  State<InserisciLottiPage> createState() => _InserisciLottiPageState();
}

class _InserisciLottiPageState extends State<InserisciLottiPage> {
  late List<String> seqLavorazioni;
  late Priorita priorita;
  late TextEditingController idLottoCtr;
  late TextEditingController idProdottoCtr;
  late TextEditingController nPezziCtr;
  late FToast ftoast;

  @override
  void initState() {
    idLottoCtr = TextEditingController();
    idProdottoCtr = TextEditingController();
    nPezziCtr = TextEditingController();
    priorita = Priorita.alta;
    seqLavorazioni = [];
    ftoast = FToast();
    ftoast.init(context);
    super.initState();
  }

  Future<bool> inserisciLotto() async {
    return true;
    var params = {
      "idLotto": idLottoCtr.text,
      "idProdotto": idProdottoCtr.text,
      "nPezzi": nPezziCtr.text,
      "priorita": priorita.name,
    };
    var body = seqLavorazioni.asMap();
    var response = await http.post(
        Uri.http(
            "http://localhost:8081", "/pianificazione/lotto/aggiungi", params),
        body: body);

    if (response.statusCode == 200) {
      return true;
    }
    return false;
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
          Text("Lotto Inserito Correttamente"),
        ],
      ),
    );

    ftoast.showToast(
      child: toast,
      gravity: ToastGravity.BOTTOM,
      toastDuration: const Duration(seconds: 2),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        centerTitle: true,
        title: const Text("Inserisci Lotto"),
        leading: IconButton(
          icon: const Icon(Icons.arrow_back_ios),
          onPressed: () => Navigator.pop(context),
        ),
      ),
      body: SingleChildScrollView(
        child: Center(
          child: Column(
            children: [
              // ID LOTTO
              Container(
                margin: const EdgeInsets.only(top: 20, bottom: 40),
                width: MediaQuery.of(context).size.width / 1.2,
                child: TextField(
                  controller: idLottoCtr,
                  style: const TextStyle(fontSize: 15),
                  obscureText: true,
                  decoration: const InputDecoration(
                    label: Text("ID Lotto"),
                    enabledBorder: OutlineInputBorder(
                      borderSide: BorderSide(color: Colors.grey, width: 1.0),
                    ),
                  ),
                ),
              ),

              // ID Prodotto
              Container(
                margin: const EdgeInsets.only(top: 20, bottom: 40),
                width: MediaQuery.of(context).size.width / 1.2,
                child: TextField(
                  controller: idProdottoCtr,
                  style: const TextStyle(fontSize: 15),
                  obscureText: true,
                  decoration: const InputDecoration(
                    label: Text("ID Prodotto"),
                    enabledBorder: OutlineInputBorder(
                      borderSide: BorderSide(color: Colors.grey, width: 1.0),
                    ),
                  ),
                ),
              ),

              // N Pezzi
              Container(
                margin: const EdgeInsets.only(top: 20, bottom: 40),
                width: MediaQuery.of(context).size.width / 1.2,
                child: TextField(
                  controller: nPezziCtr,
                  style: const TextStyle(fontSize: 15),
                  obscureText: true,
                  decoration: const InputDecoration(
                    label: Text("ID Prodotto"),
                    enabledBorder: OutlineInputBorder(
                      borderSide: BorderSide(color: Colors.grey, width: 1.0),
                    ),
                  ),
                ),
              ),

              // PRIORITA'
              Container(
                margin: const EdgeInsets.only(top: 20),
                width: MediaQuery.of(context).size.width / 1.2,
                child: DropdownButton<String>(
                  alignment: Alignment.center,
                  value: priorita.name,
                  icon: const Icon(Icons.arrow_downward),
                  elevation: 16,
                  style: const TextStyle(color: Colors.deepPurple),
                  underline: Container(
                    height: 2,
                    color: Colors.deepPurpleAccent,
                  ),
                  onChanged: (String? value) {
                    setState(() {
                      priorita = Priorita.values.byName(value!);
                    });
                  },
                  items: [
                    DropdownMenuItem<String>(
                      value: Priorita.alta.name,
                      child: Text(Priorita.alta.name),
                    ),
                    DropdownMenuItem<String>(
                      value: Priorita.media.name,
                      child: Text(Priorita.media.name),
                    ),
                    DropdownMenuItem<String>(
                      value: Priorita.bassa.name,
                      child: Text(Priorita.bassa.name),
                    ),
                  ],
                ),
              ),

              // SEQUENZA LAVORAZIONI
              Center(
                child: Container(
                  margin: const EdgeInsets.all(30),
                  height: 50,
                  child: ListView.separated(
                    scrollDirection: Axis.horizontal,
                    itemCount: seqLavorazioni.length,
                    itemBuilder: (context, index) => Container(
                      decoration: BoxDecoration(
                        color: Colors.blue[200],
                        borderRadius: BorderRadius.circular(10),
                      ),
                      padding: const EdgeInsets.only(right: 5, left: 5),
                      child: Center(
                        child: Text(
                          seqLavorazioni[index],
                        ),
                      ),
                    ),
                    separatorBuilder: (context, index) =>
                        const Icon(Icons.arrow_forward_ios),
                  ),
                ),
              ),

              // AGGIUNGI LAVORAZIONI
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                children: [
                  ElevatedButton(
                    onPressed: () {
                      setState(() {
                        seqLavorazioni.add("tornio");
                      });
                    },
                    child: Row(
                      children: const [
                        Icon(Icons.add),
                        Text("Tornio"),
                      ],
                    ),
                  ),
                  ElevatedButton(
                    onPressed: () {
                      setState(() {
                        seqLavorazioni.add("fresa");
                      });
                    },
                    child: Row(
                      children: const [
                        Icon(Icons.add),
                        Text("Fresa"),
                      ],
                    ),
                  ),
                ],
              ),

              // PULSANTE INVIO
              Container(
                width: 150,
                height: 30,
                margin: const EdgeInsets.only(top: 30, bottom: 30),
                child: ElevatedButton(
                  child: const Text("Inserisci"),
                  onPressed: () async {
                    var res = await inserisciLotto();
                    if (res) {
                      _showToast();
                    }
                  },
                ),
              )
            ],
          ),
        ),
      ),
    );
  }
}
