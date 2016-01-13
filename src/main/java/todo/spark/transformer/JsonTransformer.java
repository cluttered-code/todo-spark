package todo.spark.transformer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.orientechnologies.orient.core.id.ORID;
import spark.ResponseTransformer;

/**
 * @author david.clutter@greyrocksoft.com
 */
@Singleton
public class JsonTransformer implements ResponseTransformer {

    private static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(ORID.class, new ORIDAdapter())
            .create();

    @Inject
    private JsonTransformer() {}

    @Override
    public String render(final Object obj) throws Exception {
        return toJson(obj);
    }

    public static String toJson(final Object obj) {
        return GSON.toJson(obj);
    }

    public static <T> T fromJson(final String string, final Class<T> clazz) {
        return GSON.fromJson(string, clazz);
    }
}
