import '../utils/enums.dart';

class Lavorazione {
  final String idLavorazione;
  final String idMacchina;
  final int slot;
  final String lotto;
  final TipoMacchina tipoMacchina;

  Lavorazione({
    required this.idMacchina,
    required this.idLavorazione,
    required this.slot,
    required this.lotto,
    required this.tipoMacchina,
  });

  factory Lavorazione.fromJson(Map<String, dynamic> json) {
    return Lavorazione(
      idMacchina: json["idMacchina"],
      idLavorazione: json["idLavorazione"],
      slot: json["slot"],
      lotto: json["lotto"],
      tipoMacchina: TipoMacchina.values.byName(json["tipoMacchina"]),
    );
  }
}
