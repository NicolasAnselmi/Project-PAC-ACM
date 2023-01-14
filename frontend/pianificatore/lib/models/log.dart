class Log {
  final int idLog;
  final String idLogger;
  final String title;
  final String body;
  final String timeStamp;

  Log({
    required this.idLogger,
    required this.idLog,
    required this.title,
    required this.body,
    required this.timeStamp,
  });

  factory Log.fromJson(Map<String, dynamic> json) {
    return Log(
      idLogger: json["idLogger"],
      idLog: json["idLog"],
      title: json["title"],
      body: json["body"],
      timeStamp: json["timeStamp"],
    );
  }
}
