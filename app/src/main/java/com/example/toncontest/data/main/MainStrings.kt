package com.example.toncontest.data.main

object MainStrings {
    const val receiveButton = "Receive"
    const val sendButton = "Send"
    const val walletCreatedMain = "Wallet Created"
    const val walletCreatedSubtitle = "Your wallet address"
    const val topBarNetwork = "Waiting for network..."
    const val topBarConnection = "Connecting..."
    const val topBarUpdate = "Updating..."


    //settings screen
    const val walletSettings = "Wallet Settings"
    const val notifications = "Notifications"
    const val generalSettings = "General"
    const val addressSettings = "Active address"
    const val currencySettings = "Primary currency"
    const val tokensSettings = "List of tokens"
    const val securitySettings = "Security"
    const val recoverySettings = "Show recovery phrase"
    const val passcodeSettings = "Change passcode"
    const val biometricSettings = "Biometric Auth"
    const val deleteSettings = "Delete wallet"

    //receive card
    const val receiveHeaderText = "Receive TON"
    const val receiveMainText = "Share this address with other TON wallet owners to receive TON from them."
    const val shareButtonText = "Share Wallet Address"

    //transaction card
    const val transactionCardHeader = "Transaction"
    const val transactionDetailsHeader = "Details"
    const val transactionCardSenderTitle = "Sender"
    const val transactionCardRecipientTitle = "Recipient"
    const val transactionIdDetailsTitle = "Transaction"
    const val transactionDnsTitle = "Recipient"
    const val transactionExplorerTitle = "View in explorer"
    const val transactionSendButtonTitle = "Send TON to this address"

    //send screen
    const val sendScreenTitle = "Send TON"
    const val sendStartSubtitle = "Wallet Address or Domain"
    const val sendStartPlaceholder = "Enter Wallet Address or Domain"
    const val sendHintText = "Paste the 24-letter wallet address of the recipient here or TON DNS."
    const val pasteButtonText = "Paste"
    const val scanButtonText = "Scan"
    const val continueButtonText = "Continue"
    const val recentListHeader = "Recent"
    const val invalidAddressTitle = "Invalid Address"
    const val invalidAddressSubtitle = "Address entered does not belong to TON"
    const val sendBiggerAmount = "Insufficient funds"
    const val sendAllText = "Send all"
    const val sendConfirmSubtitle = "Comment (Optional)"
    const val sendConfirmHint = "The comment is visible to everyone. You must include the note when sending to an exchange."
    const val sendConfirmPlaceholder = "Description of the payment"
        //for text field hint
    fun someCharacterLeft(length: Int): String {
         return "${Variables.sendConfirmInputLen - length} characters left."
    }
    fun noCharacterLeft(length: Int): String {
        return  "Message size has been exceeded by ${length - Variables.sendConfirmInputLen} characters."
    }
    const val sendDetailsAmount = "Amount"
    const val sendDetailsFee = "Fee"
    const val confirmButtonText = "Confirm and send"
    const val sendInProcessHeader = "Sending TON"
    const val sendInProcessMain = "Please wait a few seconds for your\n" + "transaction to be processed…"
    const val sendInProcessButtonText = "View My Wallet"
    const val sendDoneHeader = "Done"
    const val sendDoneMain = " Toncoin have been sent to"

}