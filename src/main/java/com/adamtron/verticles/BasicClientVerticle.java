package com.adamtron.verticles;

import com.adamtron.shared.BasicResponse;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpClient;

public class BasicClientVerticle extends AbstractVerticle {

	private HttpClient httpClient = null;

	@Override
	public void start() {
		this.httpClient = vertx.createHttpClient();
		
		Integer serverPort = config().getInteger("port");
		String url = config().getString("remoteHost");
		Long pollingFrequency = config().getLong("pollingFrequencyMs");
		
		vertx.setPeriodic(pollingFrequency, id -> {
			BasicClientVerticle.this.httpClient.getNow(serverPort, url, "/", response -> {
				
				response.bodyHandler(totalBody -> {
					
					BasicResponse serverResponse = null;
					try {
						 serverResponse = BasicResponse.fromJson(totalBody.toString());
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					System.out.println(String.format("RequestId:[%s] Time:[%s]", serverResponse.getRequestId(), serverResponse.getTime()));
				});
			});
		});
	}
	
	@Override
	public void stop() {
		System.out.println("Client Verticle Stopping!");
	}
}