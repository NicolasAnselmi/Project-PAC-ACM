class Report {
  final String idMacchina;
  final String idLottoInLavorazione;
  final String ultimoLogtime;
  final StatoMacchina statoMacchina;

  Report({
    required this.idLottoInLavorazione,
    required this.idMacchina,
    required this.ultimoLogtime,
    required this.statoMacchina,
  });

  Report.fromJson(Map<String, dynamic> json)
      : idLottoInLavorazione = json["idLottoLavorazione"],
        idMacchina = json["idMacchina"],
        ultimoLogtime = json["ultimoLogTime"],
        statoMacchina = json["statoMacchina"];
}

enum StatoMacchina {
  Lavorazione,
  Fermo,
  AttesaMateriale,
  Guasta,
}
