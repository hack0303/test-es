package com.creating.www;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

public class Basic01 {

    public static void main(String[] args) throws IOException {
        Settings settings = Settings.builder()
                .put("cluster.name", "my-application")
                .put("client.transport.sniff", true)
                .build();
        //Settings.EMPTY;
        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new TransportAddress(InetAddress.getByAddress(new byte[]{(byte) 192, (byte) 168, (byte) 204, (byte) 137}), 9300));

        IndexResponse response = client.prepareIndex("test", "test").setSource(jsonBuilder().startObject()
                .field("user", "kimchy")
                .field("postDate", new Date())
                .field("message", "trying out Elasticsearch")
                .endObject()).get();
// Index name
        String _index = response.getIndex();
// Type name
        String _type = response.getType();
// Document ID (generated or not)
        String _id = response.getId();
// Version (if it's the first time you index this document, you will get: 1)
        long _version = response.getVersion();
// status has stored current instance statement.
        RestStatus status = response.status();
        System.out.printf("%s,%s,%s,%s,%d%n",_index,_type,_id,_version,status.getStatus());
        //TransportClient client = new PreBuiltTransportClient(settings);
        client.close();
    }
}
