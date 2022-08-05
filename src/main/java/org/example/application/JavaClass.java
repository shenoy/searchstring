package org.example.application;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

public class JavaClass {

    static String url = "https://gist.githubusercontent.com/akozumpl/61e98ab8aae99762a517656a61436e65/raw/23881f9513e31b7ca295b4da9f77028db1a91f91/corpus.txt";

    public static void makeApiCall(String args0, String args1) throws  IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(args0))
                .header("Content-Type", "text/plain; charset=UTF-8")
                .GET( )
                .build();

        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String result = response.body().toString();
        List<String> wordlist = new ArrayList<>(Arrays.asList(result.split(" ")));
        String searchWord = args1;
        Map<String,Integer> map = new TreeMap<>();
        for(String word: wordlist){
            if(word.startsWith(searchWord)){
                if (map.containsKey(word)) {
                    map.put(word, map.get(word) + 1);
                }else{
                    map.put(word,1);
                }
            }
        }
        System.out.println(result);
        System.out.println(map.keySet());
        System.out.println(map.values());

        map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(x->x.getKey())
                .forEach(x-> System.out.print(x + " "));

    }

    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {

        for(String s:  args){
            System.out.print(s+ " ");
        }

        makeApiCall(args[0],args[1]);
    }
}
