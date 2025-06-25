package com.mycompany.progpart2;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Message {
    private int messageID;
    private String recipient;
    private String text;
    private String hash;

    public Message(int messageID, String recipient, String text) {
        this.messageID = messageID;
        this.recipient = recipient;
        this.text = text;
        this.hash = generateHash();
    }

    public boolean checkMessageID() {
        return messageID > 0;
    }

    public boolean checkRecipientCell() {
        return recipient.matches("\\d{3,15}"); // basic numeric check
    }

    private String generateHash() {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            String input = messageID + recipient + text;
            byte[] bytes = md.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            return "";
        }
    }

    public int getLength() {
        return text.length();
    }

    public String getHash() {
        return hash;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getText() {
        return text;
    }

    public String toDisplayString() {
        return "ID: " + messageID + "\nRecipient: " + recipient + "\nText: " + text + "\nHash: " + hash;
    }
}
