package com.wtime.data.common.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class JsonUtils {
	
	public static  String asString(JsonObject json , String key){
		return json.get(key) != null ? json.get(key).getAsString() : null ;
	}
	
	public static int asInt(JsonObject json , String key){
		return json.get(key) != null ? json.get(key).getAsInt() : null ;
	}
	
	public static long asLong(JsonObject json , String key){
		return json.get(key) != null ? json.get(key).getAsLong() : null ;
	}
	
	public static  boolean asBoolean(JsonObject json , String key){
		return json.get(key)!= null ? json.get(key).getAsBoolean() : null ;
	}
	
	public static Double asDouble(JsonObject json , String key){
		return json.get(key) != null ? json.get(key).getAsDouble() : null ;
	}
	
	public static JsonObject asJsonObject(JsonObject json , String key){
		return json.get(key) != null ? json.get(key).getAsJsonObject() : null ;
	}
	
	public static JsonArray asJsonArray(JsonObject json , String key){
		return json.get(key) != null ? json.getAsJsonArray(key) : null ;
	}
}
