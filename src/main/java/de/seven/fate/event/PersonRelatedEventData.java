package de.seven.fate.event;

import java.io.Serializable;

public abstract class PersonRelatedEventData<T> implements Serializable {

    private final String ldapId;
    private final T data;

    public PersonRelatedEventData(String ldapId, T data) {
        this.ldapId = ldapId;
        this.data = data;
    }

    public String getLdapId() {
        return ldapId;
    }

    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        return "UserRelatedEventData{" +
                "ldapId='" + ldapId + '\'' +
                ", data=" + data +
                '}';
    }
}