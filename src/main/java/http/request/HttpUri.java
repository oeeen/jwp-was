package http.request;

import java.util.Objects;

public class HttpUri {
    private String path;
    private QueryParameter query;

    private HttpUri(String path, QueryParameter query) {
        this.path = path;
        this.query = query;
    }

    public static HttpUri of(String requestUri) {
        if (requestUri.contains("?")) {
            String[] tokens = requestUri.split("\\?");
            String path = tokens[0];
            QueryParameter query = QueryParameter.of(tokens[1]);

            return new HttpUri(path, query);
        }
        return new HttpUri(requestUri, QueryParameter.empty());
    }

    public String getPath() {
        return path;
    }

    public QueryParameter getQuery() {
        return query;
    }

    public String getQueryParamValue(String key) {
        return query.getValue(key);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HttpUri httpUri = (HttpUri) o;
        return Objects.equals(path, httpUri.path) &&
                Objects.equals(query, httpUri.query);
    }

    @Override
    public int hashCode() {
        return Objects.hash(path, query);
    }
}
