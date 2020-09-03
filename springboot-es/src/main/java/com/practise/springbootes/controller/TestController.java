package com.practise.springbootes.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.practise.springbootes.repository.UserInfoRepository;
import com.practise.springbootes.utils.IdWorker;
import com.practise.springbootes.vo.ResponseBean;
import com.practise.springbootes.vo.UserInfo;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.PrefixQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedLongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortMode;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class TestController {

    @Autowired
    RestHighLevelClient client;

    @Resource
    UserInfoRepository userInfoRepository;

    protected IdWorker idWorker = new IdWorker(0l,0l);

    @RequestMapping(value = "/query/data", method = RequestMethod.GET)
    public ResponseBean testESFind() {
        SearchRequest searchRequest = new SearchRequest("bank");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("balance").from("10000").to("30000");//范围查询
        PrefixQueryBuilder prefixQueryBuilder = QueryBuilders.prefixQuery("firstname", "Amber");//前缀查询
        FieldSortBuilder fieldSortBuilder = SortBuilders.fieldSort("age");//按照年龄排序
        fieldSortBuilder.sortMode(SortMode.MIN);//从小到大排序

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(rangeQueryBuilder).should(prefixQueryBuilder);//and or  查询

        sourceBuilder.query(boolQueryBuilder).sort(fieldSortBuilder);//多条件查询
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        searchRequest.source(sourceBuilder);
        try {
            SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
            SearchHits hits = response.getHits();
            JSONArray jsonArray = new JSONArray();
            for (SearchHit hit : hits) {
                String sourceAsString = hit.getSourceAsString();
                JSONObject jsonObject = JSON.parseObject(sourceAsString);
                jsonArray.put(jsonObject);
            }
            return new ResponseBean(200, "查询成功", jsonArray);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseBean(10001, "查询失败", null);
        }
    }

    @RequestMapping(value = "/query/agg", method = RequestMethod.GET)
    public ResponseBean testESFindAgg() {
        SearchRequest searchRequest = new SearchRequest("bank");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        TermsAggregationBuilder termsAggregationBuilder = AggregationBuilders.terms("by_age").field("age");
        sourceBuilder.aggregation(termsAggregationBuilder);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        searchRequest.source(sourceBuilder);
        try {
            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            Aggregations aggregations = searchResponse.getAggregations();
            Map<String, Aggregation> stringAggregationMap = aggregations.asMap();
            ParsedLongTerms parsedLongTerms = (ParsedLongTerms) stringAggregationMap.get("by_age");
            List<? extends Terms.Bucket> buckets = parsedLongTerms.getBuckets();
            Map<Integer, Long> map = new HashMap<>();
            for (Terms.Bucket bucket : buckets) {
                long docCount = bucket.getDocCount();//个数
                Number keyAsNumber = bucket.getKeyAsNumber();//年龄
                System.err.println(keyAsNumber + "岁的有" + docCount + "个");
                map.put(keyAsNumber.intValue(), docCount);
            }
            return new ResponseBean(200, "查询成功", map);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping(value = "/init")
    public ResponseBean init() {
        for (int i = 0; i < 2000; i++) {
            userInfoRepository.save(UserInfo.builder()
                    .id(idWorker.nextId())
                    .name(i%3==0?"张三"+i:"露丝"+i)
                    .deptNo(idWorker.nextId())
                    .fromArea("成都"+i)
                    .salary(i%2==0?5000:6001)
                    .build());
        }
        return  ResponseBean.builder().build();
    }

    @GetMapping(value = "/queryByName/{name}")
    public ResponseBean queryByName(@NonNull @PathVariable String name) {
        long s = System.currentTimeMillis();

        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(QueryBuilders.matchPhraseQuery("name", name));
        Query build = new NativeSearchQueryBuilder().withQuery(functionScoreQueryBuilder).build();
        Page<UserInfo> search = userInfoRepository.search(build);


        List<UserInfo> userInfos = userInfoRepository.queryByNameIsLike(name);
        log.info("本次查询耗时:{}毫秒,查询数据条数:{}",System.currentTimeMillis() - s,userInfos.size());
        return  ResponseBean.builder().data(userInfos).build();
    }
}
