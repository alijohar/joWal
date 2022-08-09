package com.aj.jowal.ui.model

enum class BankName(var fist6Numbets:String) {
    MELLI("603799"), SEPEH("589210"), TOSEASADERAT("627648"), SANAATVAMADAN("627961"), KESHAVARZI("603770"), MASKEN("628023"), POSTBANKIRAN("627760"), TOSEATAVON("502908"), EGHTESADNOVEEN("627412"), PARSIAN("622106"), PASARGAD("502229"), KARAFAREEN("627488"), SAMAN("621986"), SINA("639346"), SARMAIE("639607"), TAAT("636214"), SHAHR("502806"), DEY("502938"), SADERAT("603769"), MELLAT("610433"), TEJARET("627353"), REFAH("589463"), ANSAR("627381"), MEHREGHTISAD("639370");

    companion object {
        fun from(findValue: String): BankName = values().first { it.fist6Numbets == findValue }
    }
}

