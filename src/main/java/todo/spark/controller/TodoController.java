package todo.spark.controller;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.orientechnologies.orient.core.id.ORID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import spark.ResponseTransformer;
import todo.db.dao.TodoDao;
import todo.db.model.Todo;
import todo.db.orient.model.OrientTodo;
import todo.spark.transformer.JsonTransformer;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static spark.Spark.*;

/**
 * @author david.clutter@greyrocksoft.com
 */
@Singleton
public class TodoController implements Controller {

    private static final Logger LOG = LoggerFactory.getLogger(TodoController.class);
    private static final String BASE_PATH = "/todo";

    final TodoDao todoDao;
    final ResponseTransformer transformer;

    @Inject
    private TodoController(final TodoDao todoDao, final ResponseTransformer transformer) {
        this.todoDao = todoDao;
        this.transformer = transformer;
    }

    @Override
    public void registerRoutes() {
        get(BASE_PATH, this::list, transformer);
        post(BASE_PATH, this::create, transformer);
        get(BASE_PATH + "/:id", this::find, transformer);
        put(BASE_PATH + "/:id", this::update, transformer);
        delete(BASE_PATH + "/:id", this::remove, transformer);
    }

    private List<Todo> list(final Request request, final Response response) {
        final int skip = parseQueryParam(request, "skip", 0);
        final int limit = parseQueryParam(request, "limit", -1);
        LOG.trace("Listing Todo: skip={} limit={}", skip, limit);
        return todoDao.list(skip, limit);
    }

    private Todo create(final Request request, final Response response) {
        final Todo newTodo = JsonTransformer.fromJson(request.body(), OrientTodo.class);
        LOG.trace("Creating Todo: {}", request.body());
        return todoDao.create(newTodo);
    }

    private Todo find(final Request request, final Response response) {
        final ORID id = JsonTransformer.fromJson(request.params("id"), ORID.class);
        LOG.trace("Finding Todo: id={}", id);
        return todoDao.find(id);
    }

    private Todo update(final Request request, final Response response) {
        final ORID id = JsonTransformer.fromJson(request.params("id"), ORID.class);
        final Todo updateTodo = JsonTransformer.fromJson(request.body(), OrientTodo.class);
        LOG.trace("Updating Todo: {}", request.body());
        return todoDao.update(id, updateTodo);
    }

    private Void remove(final Request request, final Response response) {
        final ORID id = JsonTransformer.fromJson(request.params("id"), ORID.class);
        LOG.trace("Removing Todo: id={}", id);
        todoDao.delete(id);
        response.status(HttpServletResponse.SC_NO_CONTENT);
        return null;
    }
}