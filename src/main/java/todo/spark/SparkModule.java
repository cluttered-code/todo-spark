package todo.spark;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import spark.ResponseTransformer;
import todo.spark.controller.Routable;
import todo.spark.controller.TodoController;
import todo.spark.filter.EnableCORS;
import todo.spark.filter.Filtering;
import todo.spark.transformer.JsonTransformer;

/**
 * @author david.clutter@greyrocksoft.com
 */
public class SparkModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(ResponseTransformer.class).to(JsonTransformer.class);

        final Multibinder<Routable> routableBinder = Multibinder.newSetBinder(binder(), Routable.class);
        routableBinder.addBinding().to(TodoController.class);

        final Multibinder<Filtering> filteringBinder = Multibinder.newSetBinder(binder(), Filtering.class);
        filteringBinder.addBinding().to(EnableCORS.class);
    }
}