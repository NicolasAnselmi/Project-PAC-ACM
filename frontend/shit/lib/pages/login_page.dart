import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:pianificatore/providers/auth_providers.dart';

/// PAGINA INIZIALE DI LOGIN
class LoginPage extends ConsumerStatefulWidget {
  const LoginPage({super.key});

  @override
  ConsumerState<LoginPage> createState() => _LoginPageState();
}

class _LoginPageState extends ConsumerState<LoginPage> {
  late TextEditingController emailCtr;
  late TextEditingController pswCtr;

  @override
  void initState() {
    emailCtr = TextEditingController();
    pswCtr = TextEditingController();
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("Login"),
        centerTitle: true,
      ),
      body: Center(
        child: Column(
          children: [
            const Spacer(),

            // TextField Email
            SizedBox(
              width: MediaQuery.of(context).size.width / 2,
              child: TextField(
                controller: emailCtr,
                style: const TextStyle(fontSize: 30),
                decoration: const InputDecoration(
                  label: Text("Email"),
                  enabledBorder: OutlineInputBorder(
                    borderSide: BorderSide(color: Colors.grey, width: 1.0),
                  ),
                ),
              ),
            ),

            // TextField Password
            Container(
              margin: const EdgeInsets.only(top: 40, bottom: 40),
              width: MediaQuery.of(context).size.width / 2,
              child: TextField(
                controller: pswCtr,
                style: const TextStyle(fontSize: 30),
                obscureText: true,
                decoration: const InputDecoration(
                  label: Text("Password"),
                  enabledBorder: OutlineInputBorder(
                    borderSide: BorderSide(color: Colors.grey, width: 1.0),
                  ),
                ),
              ),
            ),

            // Pulsante Invio
            SizedBox(
              height: 50,
              width: 200,
              child: ElevatedButton(
                onPressed: () {
                  ref.read(loginStateProvider.notifier).login(emailCtr.text, pswCtr.text);
                },
                child: const Text("Invio"),
              ),
            ),
            const Spacer()
          ],
        ),
      ),
    );
  }
}
