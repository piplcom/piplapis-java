package com.pipl.api;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
	public void testSend() throws SearchAPIError, IOException {
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
		resp = resp.possiblePersons.get(0).fromSearchPointer().send();
		assertNotNull(resp);
		assertNotNull(resp.person);
		assertNotNull(resp.sources);
	}
	
	@Test
	public void testSendAsync() {
		ExecutorService threadPool = Executors.newSingleThreadExecutor();
		SearchAPIRequest req = new SearchAPIRequest.Builder().email("clark.kent@example.com").firstName("Clark").build();
		Future<SearchAPIResponse> future = threadPool.submit(req.sendAsync(false));
		SearchAPIResponse resp = null;
		try {
			resp = future.get();
		} catch (InterruptedException e) {
			System.out.println("The http call thread was interrupted");
		} catch (ExecutionException e) {
			// The cause contains the actual Exception thrown by send().
			e.getCause().printStackTrace();
		}
		assertNotNull(resp);
		assertNotNull(resp.person);
		assertNotNull(resp.sources);
		future = threadPool.submit(req.sendAsync());
		resp = null;
		try {
			resp = future.get();
		} catch (InterruptedException e) {
			System.out.println("The http call thread was interrupted");
		} catch (ExecutionException e) {
			// The cause contains the actual Exception thrown by send().
			e.getCause().printStackTrace();
		}
		//This search should fail because it fails strict validation
		assertNull(resp); 
	}

}
