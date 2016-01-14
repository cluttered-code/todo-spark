package todo.spark;

import com.google.inject.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Spark;
import todo.db.TodoDatabase;
import todo.db.orient.OrientDbModule;
import todo.spark.controller.Routable;

import java.util.Set;

/**
 * @author david.clutter@greyrocksoft.com
 */
public class SparkServer {

    private static final Logger LOG = LoggerFactory.getLogger(SparkServer.class);
    private static final Key<Set<Routable>> ROUTABLE_SET_KEY = Key.get(new TypeLiteral<Set<Routable>>() {});

    public static void main(final String[] args) {
        Spark.awaitInitialization();
        final Injector injector = initializeGuiceInjector();
        injector.getInstance(TodoDatabase.class).initialize();
        initializeRoutes(injector);
        setPort(args);
        Spark.init();
    }

    private static Injector initializeGuiceInjector() {
        Injector injector = null;
        try {
            injector = Guice.createInjector(new SparkModule(), new OrientDbModule());
        } catch (final CreationException ce) {
            LOG.error("Failed to create guice injector", ce);
            System.exit(1);
        }
        return injector;
    }

    private static void initializeRoutes(final Injector injector) {
        final Set<Routable> routableSet = injector.getInstance(ROUTABLE_SET_KEY);
        routableSet.forEach(Routable::registerRoutes);
    }

    private static void setPort(String[] args) {
        if(args.length == 0) {
            final int port = Integer.parseInt(args[0]);
            Spark.port(port);
        }
    }
}