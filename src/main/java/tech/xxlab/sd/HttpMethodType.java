package tech.xxlab.sd;

/**
 * @author houfachao
 */
public enum HttpMethodType {
    GET(false),
    POST(true),
    PUT(true),
    DELETE(false),
    HEAD(false),
    OPTIONS(false);

    private final boolean hasContent;

    HttpMethodType(boolean hasContent) {
        this.hasContent = hasContent;
    }

    public boolean hasContent() {
        return hasContent;
    }
}
