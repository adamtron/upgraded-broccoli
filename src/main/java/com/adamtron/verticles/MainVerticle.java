package com.adamtron.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;

public class MainVerticle extends AbstractVerticle {

	@Override
	public void start() {
		
		vertx.deployVerticle(BasicServerVerticle.class.getCanonicalName(), new DeploymentOptions().setConfig(config().getJsonObject("basicServerVerticle")));
		vertx.deployVerticle(BasicClientVerticle.class.getCanonicalName(), new DeploymentOptions().setConfig(config().getJsonObject("basicClientVerticle")));		
				
	}
	
	@Override
	public void stop() {
		System.out.println("Main verticle stopping!");
	}
}