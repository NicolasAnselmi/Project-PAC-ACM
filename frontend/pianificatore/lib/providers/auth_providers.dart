import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../notifiers/user_auth_notifier.dart';
import '../utils/enums.dart';

final loginStateProvider = NotifierProvider<UserAuthNotifier, UserStatus>(UserAuthNotifier.new);
