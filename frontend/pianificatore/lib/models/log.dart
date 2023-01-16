import 'package:pianificatore/utils/enums.dart';

class Log {
  final String idLog;
  final String idLogger;
  final String title;
  final String body;
  final String timeStamp;
  final StatoMacchina statoMacchina;
  final String codiceLotto;

  Log({
    required this.idLogger,
    required this.idLog,
    required this.title,
    required this.body,
    required this.timeStamp,
    required this.statoMacchina,
    required this.codiceLotto,
  });

  factory Log.fromJson(Map<String, dynamic> json) {
    return Log(
      idLogger: json["idLogger"],
      idLog: json["idLog"],
      title: json["title"],
      body: json["body"],
      timeStamp: json["timeStamp"],
      statoMacchina: StatoMacchina.values.byName(json["statoMacchina"]),
      codiceLotto: json["codiceLotto"],
    );
  }
}
