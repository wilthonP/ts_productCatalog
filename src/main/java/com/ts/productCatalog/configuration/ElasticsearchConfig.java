package com.ts.productCatalog.configuration;


import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticsearchConfig {
	
	@Value("${elasticsearch.host}")
	String host;

	@Value("${elasticsearch.port}")
	Integer port;

    
	@Bean(destroyMethod = "close")
    public RestHighLevelClient getConnection(){    	
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost(host,port,"http")
                )
        );

        return client;
    }

}
