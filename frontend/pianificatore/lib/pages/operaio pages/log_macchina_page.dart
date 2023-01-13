import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import '../../models/log.dart';

class LogMacchinaPage extends StatefulWidget {
  final String macchina;
  const LogMacchinaPage({super.key, required this.macchina});

  @override
  State<LogMacchinaPage> createState() => _LogMacchinaPageState();
}

class _LogMacchinaPageState extends State<LogMacchinaPage> {
  Future<List<Log>> getLogs() async {
    List<Log> listaLog = [];
    var response = await http
        .get(Uri.parse("http://localhost:8081/log/${widget.macchina}"));

    if (response.statusCode == 200) {
      Log l = Log.fromJson(jsonDecode(response.body));
      listaLog.add(l);
    }
    return listaLog;
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
        future: getLogs(),
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
                        // ID LOG
                        Text("Id Log:  ${snapshot.data![index].idLog}"),

                        // ID LOGGER
                        Text("Logger: ${snapshot.data![index].idLogger}"),

                        // TITLE
                        Text("Titolo:  ${snapshot.data![index].title}"),

                        // BODY
                        Text("Body: ${snapshot.data![index].body}"),

                        // TIMESPAMP
                        Text("Timestamp: ${snapshot.data![index].timeStamp}"),
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
