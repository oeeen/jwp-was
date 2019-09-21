package http;

import java.util.Arrays;

public enum MediaType {
    HTML("html", "templates", "text/html"),
    CSS("css", "static", "text/css"),
    JS("js", "static", "application/js"),
    ICO("ico", "static", "image/x-icon");

    private String extension;
    private String path;
    private String contentType;

    MediaType(String extension, String path, String contentType) {
        this.extension = extension;
        this.path = path;
        this.contentType = contentType;
    }

    public static String getFullPath(String uri) {
        return Arrays.stream(values())
                .filter(value -> uri.contains(value.extension))
                .map(value -> "./" + value.path + uri)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public static String getContentType(String uri) {
        return Arrays.stream(values())
                .filter(value -> uri.contains(value.extension))
                .map(value -> value.contentType)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}