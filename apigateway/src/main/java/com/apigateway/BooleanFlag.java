package com.apigateway;

public class BooleanFlag {

    private boolean isFunded;
    private boolean isPaid;

    public boolean getFunded() {
        return isFunded;
    }

    public void setFunded(boolean funded) {
        isFunded = funded;
    }

    public boolean getPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }
}
