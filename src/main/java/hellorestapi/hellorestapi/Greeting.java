package hellorestapi.hellorestapi;

/**
 * Robbie Sollie - Greeting.java - EGR327 - CBU - 2019-10-14
 */

public class Greeting {
    private final long id;
    private String content;

    public Greeting() {
        this.id = 0;
        this.content = "";
    }

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
