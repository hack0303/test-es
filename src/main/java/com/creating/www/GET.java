package com.creating.www;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Date;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * https://www.elastic.co/guide/en/elasticsearch/client/java-api/current/java-docs-index.html
 * */
public class GET {

    public static void main(String[] args) throws IOException {
        Settings settings = Settings.builder()
                .put("cluster.name", "my-application")
                .put("client.transport.sniff", true)
                .build();
        //Settings.EMPTY;
        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new TransportAddress(InetAddress.getByAddress(new byte[]{(byte) 192, (byte) 168, (byte) 204, (byte) 137}), 9300));

        GetResponse response = client.prepareGet("test", "test", "1").get();
        System.out.println(response.getField("user"));
        client.close();
    }
}
