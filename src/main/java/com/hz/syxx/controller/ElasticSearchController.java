package com.hz.syxx.controller;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * Created by Pefan_Li
 * Created Time 2019-2-17 12:46.
 */
@RestController
@RequestMapping("/es")
public class ElasticSearchController {
    @Autowired
    RestHighLevelClient client;

    @PutMapping("/index")
    public CreateIndexResponse addIndex(@RequestParam(name = "index") String index) throws IOException {
        CreateIndexRequest request = new CreateIndexRequest(index);
        request.settings(Settings.builder().put("index.number_of_shards", 3)
                .put("index.number_of_replicas", 0));
        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        {
            builder.startObject("_doc");
            {
                builder.startObject("properties");
                {
                    builder.startObject("title");
                    {
                        builder.field("type", "text");
                    }
                    builder.endObject();
                    builder.startObject("author");
                    {
                        builder.field("type", "text");
                    }
                    builder.endObject();
                    builder.startObject("word_count");
                    {
                        builder.field("type", "integer");
                    }
                    builder.endObject();
                    builder.startObject("publish_date");
                    {
                        builder.field("type", "date");
                        builder.field("format", "yyyy-MM-dd HH:mm:ss");
                    }
                    builder.endObject();
                }
                builder.endObject();
            }
            builder.endObject();
        }
        builder.endObject();
        request.mapping("_doc", builder);
        CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);
        return response;
    }

    @PutMapping("/doc")
    public IndexResponse addDoc(@RequestParam(name = "title") String title,
                         @RequestParam(name = "author") String author,
                         @RequestParam(name = "word_count") String wordCount,
                         @RequestParam(name = "publish_date") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") String publishDate) throws IOException {
        IndexRequest request = new IndexRequest("book","_doc")
                .source("title",title,
                        "author",author,
                        "word_count",wordCount,
                        "publish_date",publishDate);
        IndexResponse indexResponse = client.index(request,RequestOptions.DEFAULT);
        return indexResponse;
    }

    @GetMapping("/doc")
    public GetResponse getDoc(@RequestParam(name = "id") String id) throws IOException {
        GetRequest request = new GetRequest("book","_doc",id);
        GetResponse getResponse = client.get(request,RequestOptions.DEFAULT);
        return getResponse;
    }

    @PostMapping("/doc/_search")
    public SearchResponse search() throws IOException {
        SearchRequest searchRequest = new SearchRequest("book");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        return searchResponse;
    }
}
