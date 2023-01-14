import 'package:pianificatore/utils/enums.dart';

class Lotto {
  final String idLotto;
  final String idProdotto;
  final int nPezzi;
  final Priorita priorita;
  final List<dynamic> listaLavorazioni;

  Lotto({
    required this.idProdotto,
    required this.idLotto,
    required this.nPezzi,
    required this.priorita,
    required this.listaLavorazioni,
  });

  factory Lotto.fromJson(Map<String, dynamic> json) {
    return Lotto(
      idProdotto: json["idProdotto"],
      idLotto: json["idLotto"],
      nPezzi: json["nPezzi"],
      priorita: Priorita.values.byName(json["priorita"]),
      listaLavorazioni: json["listaLavorazioni"],
    );
  }
}
