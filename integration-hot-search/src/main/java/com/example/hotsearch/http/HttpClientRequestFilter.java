package com.example.hotsearch.http;

import lombok.extern.slf4j.Slf4j;
import org.springblade.core.tool.jackson.JsonUtil;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import java.io.IOException;

/**
 * @author qiwendong
 */
@Slf4j
public class HttpClientRequestFilter implements ClientRequestFilter {

	@Override
	public void filter(ClientRequestContext clientRequestContext) throws IOException {
		log.info(JsonUtil.toJson(clientRequestContext.getStringHeaders()));
	}
}
