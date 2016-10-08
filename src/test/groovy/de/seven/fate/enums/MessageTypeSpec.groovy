package de.seven.fate.enums

import de.seven.fate.message.enums.MessageType
import spock.lang.Specification

class MessageTypeSpec extends Specification {

    def "MessageType's length"() {
        expect:
        MessageType.values().length == 2
    }

    def "MessageType's name"() {
        expect:
        type.name() == name

        where:
        type               | name
        MessageType.UNREAD | "UNREAD"
        MessageType.READ   | "READ"
    }


    def "MessageType's ordinal"() {
        expect:
        type.ordinal() == ordinal

        where:
        type               | ordinal
        MessageType.UNREAD | 0
        MessageType.READ   | 1
    }
}
