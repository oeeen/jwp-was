package http;

import java.util.Arrays;

public enum MediaType {
    HTML("html", "templates", "text/html"),
    CSS("css", "static", "text/css"),
    JS("js", "static", "application/js"),
    ICO("ico", "static", "image/x-icon"),
    WOFF("woff", "static", "application/x-font-woff"),
    TTF("ttf", "static", "application/x-font-ttf");

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
                .filter(value -> uri.endsWith(value.extension))
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

    public static boolean isContain(String uri) {
        int lastIndex = uri.lastIndexOf("/");
        String extension = uri.substring(lastIndex + 1);

        return Arrays.stream(values())
                .map(value -> extension.contains(value.extension))
                .filter(value -> value)
                .findFirst()
                .orElse(false);
    }
}
