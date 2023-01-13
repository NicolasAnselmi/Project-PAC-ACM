class Macchina {
  final String idMacchina;
  final int codiceLottoInLavorazione;
  final String lastTimestamp;
  final StatoMacchina statoMacchina;
  final TipoMacchina tipoMacchina;

  Macchina({
    required this.idMacchina,
    required this.codiceLottoInLavorazione,
    required this.lastTimestamp,
    required this.tipoMacchina,
    required this.statoMacchina,
  });

  factory Macchina.fromJson(Map<String, dynamic> json) {
    return Macchina(
      idMacchina: json["idMacchina"],
      codiceLottoInLavorazione: json["codiceLottoInLavorazione"],
      lastTimestamp: json["timeStampUltimoMessaggio"],
      statoMacchina: StatoMacchina.values.byName(json["statoMacchina"]),
      tipoMacchina: TipoMacchina.values.byName(json["tipoMacchina"]),
    );
  }
}

enum StatoMacchina {
  Lavorazione,
  Fermo,
  AttesaMateriale,
  Guasta,
}

enum TipoMacchina {
  fresa,
  tornio,
}
