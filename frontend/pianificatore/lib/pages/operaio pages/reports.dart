import 'dart:async';

import 'package:flutter/material.dart';

import '../../models/report.dart';

class ReportsPage extends StatefulWidget {
  const ReportsPage({super.key});

  @override
  State<ReportsPage> createState() => _ReportsPageState();
}

class _ReportsPageState extends State<ReportsPage> {
  Future<List<Report>> getReports() async {
    // TODO: rimuovere
    await Future.delayed(const Duration(seconds: 2));
    return [
      Report(
          idLottoInLavorazione: "878979",
          idMacchina: "AABS223",
          statoMacchina: StatoMacchina.Fermo,
          ultimoLogtime: "19:91:111")
    ];
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        centerTitle: true,
        title: const Text("Reports"),
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
              itemBuilder: ((context, index) => Container(
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
                      Text("Id Lotto In Lavorazione:  ${snapshot.data![index].idLottoInLavorazione}"),

                      // ULTIMO LOGTIME
                      Text("Ultimo Logtime: ${snapshot.data![index].ultimoLogtime}"),
                    ]),
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
