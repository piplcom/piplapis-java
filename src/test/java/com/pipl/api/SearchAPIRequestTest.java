package com.pipl.api;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.pipl.api.search.SearchAPIError;
import com.pipl.api.search.SearchAPIRequest;
import com.pipl.api.search.SearchAPIResponse;
import com.pipl.api.search.SearchConfiguration;

public class SearchAPIRequestTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		SearchConfiguration defaultConfiguration = SearchAPIRequest.getDefaultConfiguration();
		defaultConfiguration.setShowSources(SearchConfiguration.ALL_SOURCES);
	}

	@Test
	public void testSend() throws SearchAPIError, IOException, URISyntaxException {
		SearchAPIRequest req = new SearchAPIRequest.Builder().email("clark.kent@example.com").build();
		SearchAPIResponse resp = req.send();
		assertNotNull(resp);
		assertNotNull(resp.person);
		assertNotNull(resp.sources);
		req = new SearchAPIRequest.Builder().firstName("clark").lastName("kent").build();
		resp = req.send();
		assertNotNull(resp);
		assertNull(resp.person);
		assertNotNull(resp.possiblePersons);
		assertNotNull(resp.sources);
	}

}
