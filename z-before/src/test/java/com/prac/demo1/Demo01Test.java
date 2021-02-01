package com.prac.demo1;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.*;

/**
 * @Auther: liweizhi
 * @Date: 2018/12/13 15:47
 * @Description:
 */
public class Demo01Test {

    @Data
    private static class Node{
        private Integer id;
        private Integer pid;
        private String name;
        private LinkedList<Node> child;

        public Node(int id, int pid, String name) {
            this.id = id;
            this.pid = pid;
            this.name = name;
        }
    }

    public static void main(String[] args) {
        Node n1 = new Node(1,0,"1");
        Node n2 = new Node(2,1,"1-2");
        Node n3 = new Node(3,1,"1-3");
        Node n4 = new Node(4,2,"2-4");
        List<Node> list = new ArrayList<>();
        list.add(n1);
        list.add(n2);
        list.add(n3);
        list.add(n4);
        Node tree = getTree(list);
        System.out.println(tree);
        System.out.println(JSON.toJSONString(tree));
    }
    private static Node getTree(List<Node> list){
        HashMap<Integer,Node> map = new HashMap<>(16);
        Node p = null;
        for (Node node:list) {
            if (node.getPid() == null || node.getPid() == 0){
                p = node;
            }
            map.put(node.getId(),node);
        }
        for (Node node:list) {
            Node pNode = map.get(node.getPid());
            if (pNode != null){
                if (pNode.getChild() == null){
                    pNode.setChild(new LinkedList<>());
                }
                pNode.getChild().add(node);
            }
        }
        return p;
    }

    @Test
    public void linkList() {
        LinkedList<String> list = new LinkedList<>();
        list.add("a");
        list.add("s");
        list.add(null);
        list.add("d");
        System.out.println(list);
    }

    public void arrayList() {
        int[] a = new int[10];
        a[0] = 0;
        a[1] = 1;
        a[2] = 2;
        a[3] = 3;
        System.arraycopy(a, 2, a, 3, 2);
        a[2] = 99;
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }

    @Test
    public void arrList() {
        List<String> arrayList = new ArrayList<String>();
        arrayList.add("s");
        arrayList.add("e");
        arrayList.add("n");

        String[] strings = arrayList.toArray(new String[arrayList.size()]);
        arrayList.remove(0);
        System.out.println(Arrays.toString(strings));


    }

    @Test
    public void number() {
        int a = 1, b = 2;
        System.out.println("a=" + a + ";b=" + b);
        System.out.println(Float.isNaN(1.1F));
    }

    public void timeStamp() {
        System.out.println(new Date(1545980811857L));
        System.out.println(new Date(1545720371000L));
    }

    @Test
    public void tmp() {
        JSONObject obj = new JSONObject();
        obj.put("label", "CPU状态");
        obj.put("statusText", "CPU使用33%");
        obj.put("formatted_statusText", "<span class=\"normal\">CPU使用33%</span?");
        System.out.println(obj.toJSONString());
    }

    public void nbdGetDemo() {
        RestTemplate restTemplate = getRestTemplate();
        List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
        for (HttpMessageConverter<?> httpMessageConverter : messageConverters
        ) {
            if (httpMessageConverter instanceof StringHttpMessageConverter) {
                ((StringHttpMessageConverter) httpMessageConverter).setDefaultCharset(Charset.forName("utf-8"));
            }
        }
        String nbcUrl = "http://10.0.1.61:8098/api/v1/dataquerymodels/59348aef01174af589b83c7b10ca96f3/query?jsonformat=true";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("CWAccessToken", "27046a281cda41f7b9e93f0710c4306b");
        HttpEntity<String> entity = new HttpEntity<>("{params:{}}", headers);
        String response = restTemplate.exchange(nbcUrl, HttpMethod.POST, entity, String.class).getBody();
        System.out.println("response==============" + response);
        String results = JSON.parseObject(response).getJSONObject("data").getString("results");
        List<Person> peoples = JSON.parseArray(results, Person.class);
        System.out.println(peoples);
    }

    @Test
    public void nbdSetDemo() {
        Person data = new Person.Builder("tom").age(19).build();

        RestTemplate restTemplate = getRestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.set("CWAccessToken", "27046a281cda41f7b9e93f0710c4306b");
        HttpEntity<String> entity = new HttpEntity<>(JSON.toJSONString(data), headers);
        String resultStr = restTemplate.exchange("http://10.0.1.61:8098/api/v1/data/3d7ec741a9ba4e08865a8705e0f9a0ef", HttpMethod.POST, entity, String.class).getBody();
        System.out.println(resultStr);
        // 正常的话会返回 {"status":"success","code":100000,"data":null}
        JSONObject resultObject = JSON.parseObject(resultStr);
    }

    private RestTemplate getRestTemplate() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(15000);//单位为ms
        factory.setConnectTimeout(15000);//单位为ms
        return new RestTemplate(factory);
    }

}
