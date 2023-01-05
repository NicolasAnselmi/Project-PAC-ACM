import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:pianificatore/notifiers/user_auth_notifier.dart';
import 'package:pianificatore/utils/enums.dart';

final loginStateProvider = NotifierProvider<UserAuthNotifier, UserStatus>(UserAuthNotifier.new);
