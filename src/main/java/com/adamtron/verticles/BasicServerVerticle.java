package com.adamtron.verticles;

import java.time.Instant;
import java.util.concurrent.ThreadLocalRandom;

import com.adamtron.shared.BasicResponse;

import io.vertx.core.AbstractVerticle;

public class BasicServerVerticle extends AbstractVerticle {

	@Override
	public void start() {
		Integer port = config().getInteger("port");
		System.out.println("Server listening at port " + port);
		
		vertx.createHttpServer().requestHandler(request -> {
			BasicResponse response = new BasicResponse()
					.setRequestId(Math.abs(ThreadLocalRandom.current().nextInt()))
					.setTime(Instant.now().toString());

			request.response().end(response.toJson());

		}).listen(port);
	}

	@Override
	public void stop() {
		System.out.println("Server Verticle Stopping!");
	}
}