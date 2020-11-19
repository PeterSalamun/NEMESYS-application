package model.callsAndExceptions.otherCalls;

public abstract class BasicCall {

    protected String message;
    protected String title;

    //Constructor
    public BasicCall(String message, String title) {
        this.message = message;
        this.title = title;

    }

    //getters
    public abstract int getCall();

    public String getMessage() {
        return message;
    }

    public String getTitle() {
        return title;
    }
}
