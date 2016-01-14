package todo.spark.filter;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import spark.Request;
import spark.Response;
import spark.Spark;

/**
 * @author david.clutter@greyrocksoft.com
 */
@Singleton
public class EnableCORS implements Filtering {

    @Inject
    private EnableCORS() {}

    @Override
    public void enable() {
        Spark.options("/*", this::accessControlAllow);

        Spark.before(this::setAllowedOrigins);
    }

    private Void setAllowedOrigins(final Request request, final Response response) {
        response.header("Access-Control-Allow-Origin", "*");
        return null;
    }

    private String accessControlAllow(final Request request, final Response response) {
        String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
        if (accessControlRequestHeaders != null) {
            response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
        }
        String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
        if(accessControlRequestMethod != null){
            response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
        }
        return "OK";
    }
}