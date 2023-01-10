import 'package:flutter/material.dart';
import 'package:overlay_support/overlay_support.dart';
import 'package:pianificatore/pages/login_page.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:pianificatore/pages/root_page.dart';
import 'package:pianificatore/providers/auth_providers.dart';
import 'package:pianificatore/utils/enums.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
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
    return OverlaySupport(
      child: MaterialApp(
        debugShowCheckedModeBanner: false,
        title: 'PROGETTO PAC',
        theme: ThemeData(
          primarySwatch: Colors.blue,
        ),
        //home: NotifichePage(),
        home: ref.watch(loginStateProvider) == UserStatus.none ? const LoginPage() : const RootPage(),
      ),
    );
  }
}
