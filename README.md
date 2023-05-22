# Ton Wallet Contest - Android Application

This repository contains an Android application developed for the Ton Wallet Contest. The application provides a wallet interface for interacting with the TON blockchain. Please note that some features are currently not implemented or have limitations.

## Features

- Wallet interface for TON blockchain.
- Balance tracking and transaction history.
- Sending and receiving TON tokens.
- Security measures to protect wallet data.

## Limitations

- **Sending entire balance:** The feature to send the entire balance is not implemented at the moment. Implementing this feature could lead to unintended consequences, such as sending unknown transactions and burning the entire balance.

- **Wallet versions:** Only V4R2 wallet version is supported in this application. Other wallet versions are not compatible.

- **DNS and TON Connect:** The application does not currently support working with DNS and TON Connect. These features may be implemented in future updates.

- **Deeplink functionality:** The Deeplink functionality in the application has certain limitations. It can either be used for sending funds or sharing the QR code, but not both simultaneously.

## Getting Started

To get started with the Ton Wallet Contest Android application, follow these steps:

1. Clone this repository to your local machine.
2. Replace the `release.keystore` file with your own keystore file. The `release.keystore` file is located in the project's root directory. Ensure that you securely store your keystore file and do not share it publicly.
3. Open the project in Android Studio.
4. Build the application using Android Studio and run it on an Android device or emulator.

## Ready-made Build

A ready-made build of the Ton Wallet Contest Android application can be found in the "builds" folder. If you do not wish to build the application manually, you can use the APK file provided in this folder.

## Contributing

Contributions to the Ton Wallet Contest Android application are welcome. If you encounter any issues or would like to suggest improvements, please submit a pull request or open an issue in this repository.

## License

This project is licensed under the [MIT License](LICENSE).
