package model.callsAndExceptions.otherCalls;

public abstract class BasicCall {

    String message;
    String title;


    public BasicCall(String message, String title) {
        this.message = message;
        this.title = title;

    }

    public abstract int getCall();
}
