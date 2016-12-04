package com.adamtron.shared;

import io.vertx.core.json.JsonObject;

public class BasicResponse {

	private String time;
	private Integer requestId;

	public String getTime() {
		return time;
	}

	public BasicResponse setTime(String time) {
		this.time = time;
		return this;
	}

	public Integer getRequestId() {
		return requestId;
	}

	public BasicResponse setRequestId(Integer requestId) {
		this.requestId = requestId;
		return this;
	}

	public String toJson() {
		return new JsonObject()
				.put("BasicResponse", new JsonObject().put("time", this.time).put("requestId", this.requestId))
				.toString();
	}
	
	public static BasicResponse fromJson(String json) {
		JsonObject data = new JsonObject(json).getJsonObject("BasicResponse");
		
		BasicResponse response = new BasicResponse();
		response.setTime(data.getString("time"));
		response.setRequestId(data.getInteger("requestId"));
		
		return response;
	}
}