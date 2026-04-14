package org.apache.commons.mail;

public class EmailConcrete extends Email {

    @Override
    public Email setMsg(String msg) throws EmailException {
        this.content = msg;
        return this;
    }
}