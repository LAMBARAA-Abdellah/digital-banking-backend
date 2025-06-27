package org.example.digitalbanking.exceptions;

public class BalanceNotSuffisendException extends Throwable {
    public BalanceNotSuffisendException(String soldeInssufisant) {
        super(soldeInssufisant);
    }
}
