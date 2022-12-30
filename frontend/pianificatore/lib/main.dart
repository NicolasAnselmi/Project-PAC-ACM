import 'package:flutter/material.dart';
import 'package:pianificatore/pages/login_page.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:pianificatore/pages/root_page.dart';
import 'package:pianificatore/utils/enums.dart';

void main() {
  runApp(
    const ProviderScope(
      child: MyApp(),
    ),
  );
}

class MyApp extends ConsumerWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'PROGETTO PAC',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: ref.watch(loginStateProvider) == UserStatus.none ? const LoginPage() : const RootPage(),
    );
  }
}
