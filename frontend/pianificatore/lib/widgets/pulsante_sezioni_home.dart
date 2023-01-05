import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class PulsanteSezioniHome extends StatelessWidget {
  final String titolo;
  final Widget pagina;

  const PulsanteSezioniHome({super.key, required this.titolo, required this.pagina});

  @override
  Widget build(BuildContext context) {
    return Container(
      height: 100,
      margin: const EdgeInsets.only(left: 20, right: 20, top: 15, bottom: 15),
      child: ElevatedButton(
        onPressed: () => Navigator.push(
          context,
          CupertinoPageRoute(
            builder: ((context) => pagina),
          ),
        ),
        child: Text(
          titolo,
          style: const TextStyle(fontSize: 30),
        ),
      ),
    );
  }
}
