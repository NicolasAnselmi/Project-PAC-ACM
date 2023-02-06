import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:pianificatore/notifiers/notification_notifier.dart';
import '../models/notifica.dart';

final notificaProvider =
    AsyncNotifierProvider<NotificationNotifier, List<Notifica>>(
        NotificationNotifier.new);
