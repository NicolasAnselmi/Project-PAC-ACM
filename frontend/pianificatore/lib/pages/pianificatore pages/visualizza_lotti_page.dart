import 'package:flutter/material.dart';
import 'package:pianificatore/models/lotto.dart';

class VisualizzaLottiPage extends StatefulWidget {
  const VisualizzaLottiPage({super.key});

  @override
  State<VisualizzaLottiPage> createState() => _VisualizzaLottiPageState();
}

class _VisualizzaLottiPageState extends State<VisualizzaLottiPage> {
  Future<List<Lotto>> getReports() async {
    // TODO: rimuovere
    await Future.delayed(const Duration(seconds: 2));
    return [
      Lotto(),
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
                    child: Container(),
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
