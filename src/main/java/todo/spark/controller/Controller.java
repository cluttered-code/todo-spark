package todo.spark.controller;

import spark.QueryParamsMap;
import spark.Request;

/**
 * @author david.clutter@ca.com
 */
public interface Controller extends Routable {

    default int parseQueryParam(final Request request, final String key, final int defaultValue) {
        final QueryParamsMap map = request.queryMap(key);
        return map.hasValue() ? map.integerValue() : defaultValue;
    }
}