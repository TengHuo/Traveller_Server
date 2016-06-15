package app.jsonClass;

/**
 * Created by zhujay on 16/6/15.
 */
public class hello {
    private String message;

    public hello(String message) {
        this.message = "Hello! "+message +'!';
    }

    public hello() {
        this.message = "Hello! What's your name?";
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
