package http.request;

import java.util.Map;
import java.util.Optional;

public class HttpRequestHeader {
    private static final String CONTENT_LENGTH = "Content-Length";
    private Map<String, String> fields;

    public HttpRequestHeader(Map<String, String> fields) {
        this.fields = fields;
    }

    public int getContentLength() {
        int length = 0;

        if (fields.containsKey(CONTENT_LENGTH)) {
            length = Integer.parseInt(fields.get(CONTENT_LENGTH));
        }

        return length;
    }

    public String getHeader(String key) {
        return Optional.ofNullable(this.fields.get(key)).orElseThrow(IllegalArgumentException::new);
    }

    public boolean isContainKey(String key) {
        return fields.containsKey(key);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        fields.forEach((key, value) -> stringBuilder.append(key).append(": ").append(value).append("\r\n"));
        stringBuilder.append("\r\n");

        return stringBuilder.toString();
    }
}
