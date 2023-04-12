package com.spring.redis.util;

import org.json.JSONObject;

public class JsonUtil {
	
  /**
   * @param key
   * @param value
   * @return tui grid data 형식 포맷
   */
  public static JSONObject getDataAsJSONObject(String key , String value) {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("key", key);
    jsonObject.put("value", value);
    return jsonObject;
  }
	
}
