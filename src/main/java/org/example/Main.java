package org.example;

import org.apache.commons.lang3.StringUtils;

import java.text.Normalizer;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        String result = "hom qua anh mo ve em  , mơ rất nhiều đó mò";
        String keyword = "mo";

        System.out.println(highlight(result, keyword));

    }

    
    private static String highlight(String result , String keyword){
        Map<String , Boolean> keywords = new HashMap<>();
        for (String key : keyword.split(" ")){
            keywords.put(removeVietnameseAccent(key) , true);
        }

        String[] newResultArray = result.split(" ");
        for(int  i = 0 ; i < newResultArray.length  ; i++){
            if(keywords.get(removeVietnameseAccent(newResultArray[i])) != null){
                newResultArray[i] = addHtmlTagForHighlight(newResultArray[i]);
            }
        }

        return Stream.of(newResultArray)
                .collect(Collectors.joining(" "));
    }

    private static String removeVietnameseAccent (String result){
        return StringUtils.stripAccents(result)
                .replace("đ" , "d")
                .replace("Đ" , "d");
    }

    private static String addHtmlTagForHighlight(String result){
        return new StringBuilder("<p class=\"highlighted\">").append(result).append("</p>").toString();
    }
}