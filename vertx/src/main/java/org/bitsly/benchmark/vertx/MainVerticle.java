package org.bitsly.benchmark.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.ext.web.Router;

public class MainVerticle extends AbstractVerticle {

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        Router router = Router.router(vertx);
        vertx.createHttpServer().requestHandler(router).listen(8888);

        router.get("/sync").blockingHandler(ctx -> {
            ctx.response().end("sync");
        }, false);
        router.get("/async").handler(ctx -> {
            ctx.response().end("async");
        });
        router.errorHandler(500, ctx -> {
            ctx.response().end("500!!");
        });
    }
}
