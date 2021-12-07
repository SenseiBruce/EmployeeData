package com.example.springjpa.EmployeeData;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class EmployeeRestApiTests {
	
	
	
	  @Test 
	  public void responseTypeIsOk() throws IOException,
	  InterruptedException{
	  
	  
	  URI uri =URI.create("http://localhost:8086//employee");
	
	  
	  HttpUriRequest request = new HttpGet(uri);
	  
	  //HttpClient client = HttpClient.newBuilder().build(); 
	  
	  CloseableHttpResponse response =  HttpClientBuilder.create().build().execute(request);
	 
	  assertEquals(response.getStatusLine().getStatusCode(),200);
	  }
	  
	  @Test
	  public void 
	  defaultResponseContentTypeIsJson()
	    throws ClientProtocolException, IOException {	   
	     // Given
	     String jsonMimeType = "application/json";
	     HttpUriRequest request = new HttpGet( "http://localhost:8086//Employee" );

	     // When
	     CloseableHttpResponse response = HttpClientBuilder.create().build().execute( request );

	     // Then
	     String mimeType = ContentType.getOrDefault(response.getEntity()).getMimeType();
	     
	     assertEquals( jsonMimeType, mimeType );
	  }
	
	  
	  @Test
	  public void testResponseBody() throws ClientProtocolException, IOException {
		  
		  HttpUriRequest request = new HttpGet("http://localhost:8086//Employee");
		  
		  CloseableHttpResponse response = HttpClientBuilder.create().build().execute(request);
		  
		  HttpEntity body = response.getEntity();
		  
		  assertThat(body!=null);
		  
	  }
	  }

