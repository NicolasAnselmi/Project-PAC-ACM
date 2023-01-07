import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:pianificatore/notifiers/notification_notifier.dart';
import '../models/notifica.dart';

final notificaProvider = NotifierProvider<NotificationNotifier, List<Notifica>>(NotificationNotifier.new);
