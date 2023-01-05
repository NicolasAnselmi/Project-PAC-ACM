import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:pianificatore/utils/enums.dart';

class UserAuthNotifier extends Notifier<UserStatus> {
  @override
  build() {
    return UserStatus.none;
  }

  void login(String email, String password) {
    // TODO: RIMUOVERE
    state = UserStatus.manager;
    return;
    email = email.trim();
    if (users.containsKey(email)) {
      if (users[email]["password"] == password) {
        switch (users[email]["userstatus"]) {
          case 'manager':
            state = UserStatus.manager;
            break;
          case 'operaio':
            state = UserStatus.operaio;
            break;
          case 'pianificatore':
            state = UserStatus.pianificatore;
            break;
        }
      } else {
        print("PASSWORD ERRATA");
      }
    } else {
      print("ACCOUNT NON VALIDO");
      return;
    }
  }

  void logout() {
    state = UserStatus.none;
    print("LOGOUT");
  }
}

// ////////////////////////////////////////////////////
/// MOCK USER DATABASE
Map<String, dynamic> users = {
  'piero': {
    'password': 'abc123',
    'userstatus': 'manager',
  },
  'luca': {
    'password': 'abc123',
    'userstatus': 'operaio',
  },
  'mario': {
    'password': 'abc123',
    'userstatus': 'pianificatore',
  },
};
