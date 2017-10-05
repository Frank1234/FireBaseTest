# FireBaseTest

Try-out project using MVPVM, Dagger2-Android, Data Binding and RXJava, with a large test coverage of JUnit and UI (Espresso) tests. The app uses Firebase authentication, Firebase database and Firebase storage.

The architecture used is MVPVM (MVP + ViewModels). Views and ViewModels are dumb in this setup.

Tests are run by [buddybuild CI](https://www.buddybuild.com/)

## Open items

- The current use of IdlingResources for Espresso requires some test code in my production builds. Check if we can switch to github.com/square/RxIdler

## Author

* **Frank van der Laan** - [Frank1234](https://github.com/Frank1234)