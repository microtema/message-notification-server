package de.seven.fate.message.enums

import spock.lang.Specification

class MessageTypeSpec extends Specification {

    def "ModelsType's length"() {
        expect:
        MessageType.values().length == 2
    }

    def "ModelsType's name"() {
        expect:
        type.name() == name

        where:
        type               | name
        MessageType.UNREAD | "UNREAD"
        MessageType.READ   | "READ"
    }


    def "MessageType's ordinal"() {
        expect:
        role.ordinal() == ordinal

        where:
        role               | ordinal
        MessageType.UNREAD | 0
        MessageType.READ   | 1
    }
}
