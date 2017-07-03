package com.count.controller;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;

import javax.servlet.ServletContext;
import javax.servlet.http.Part;
import java.io.*;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by dell--pc on 2017/7/3.
 */
@Controller
public class IndexController {
    @RequestMapping(path = "/",method = RequestMethod.GET)
    public String choose(){
        return "index";
    }
    @RequestMapping(path = "/count/",method = RequestMethod.POST)
    public String count(Model model,@RequestParam("text") String text){
        ArrayList<Character> a=new ArrayList<>();
        ArrayList<Integer> b=new ArrayList<>();
        Map<Character,Integer> times=new HashedMap();
        for(int i =0;i<text.length();i++){
            char c=text.charAt(i);
            if(times.containsKey(c)){
                times.put(c,times.get(c)+1);
            }else{
                times.put(c,1);
            }
        }
        for(Map.Entry<Character,Integer> map:times.entrySet()){
            a.add(map.getKey());
            b.add(map.getValue());
        }
        for(int i=0;i<a.size();i++){
            for(int j=i+1;j<a.size();j++){
                if (b.get(i)<b.get(j)){
                    int temp=b.get(i);
                    b.set(i,b.get(j));
                    b.set(j,temp);
                    char c=a.get(i);
                    a.set(i,a.get(j));
                    a.set(j,c);
                }
            }
        }



        int eng=0,num=0,chinese=0,punc=0;
        for(int i=0;i<text.length();i++){
            if(text.charAt(i)<58&&text.charAt(i)>47){
                num++;
            }else if((text.charAt(i)<91&&text.charAt(i)>64)||(text.charAt(i)<123&&text.charAt(i)>96)){
                eng++;
            }
            else if(text.charAt(i) >= 0x4E00 &&  text.charAt(i) <= 0x9FA5){
                chinese++;
            }else{
                punc++;
            }
        }
        model.addAttribute("eng",eng);
        model.addAttribute("num",num);
        model.addAttribute("chinese",chinese);
        model.addAttribute("punc",punc);
        model.addAttribute("text",text);
        model.addAttribute("max1c",a.get(0));
        model.addAttribute("max2c",a.get(1));
        model.addAttribute("max3c",a.get(2));
        model.addAttribute("max1n",b.get(0));
        model.addAttribute("max2n",b.get(1));
        model.addAttribute("max3n",b.get(2));

        return "index";
    }

    @RequestMapping(path = "/upload/",method = RequestMethod.POST)
    public String upload(Model model, @RequestPart("file") Part part) throws IOException {
        InputStream is=part.getInputStream();
        byte[] buf=new byte[1024];
        int flag=0;
        StringBuilder sb=new StringBuilder();
        while((flag=is.read(buf,0,1024))!=-1){
            String s=new String(buf,0,flag);
            sb=sb.append(s);
        }
        String text=sb.toString();

        ArrayList<Character> a=new ArrayList<>();
        ArrayList<Integer> b=new ArrayList<>();
        Map<Character,Integer> times=new HashedMap();
        for(int i =0;i<text.length();i++){
            char c=text.charAt(i);
            if(times.containsKey(c)){
                times.put(c,times.get(c)+1);
            }else{
                times.put(c,1);
            }
        }
        for(Map.Entry<Character,Integer> map:times.entrySet()){
            a.add(map.getKey());
            b.add(map.getValue());
        }
        for(int i=0;i<a.size();i++){
            for(int j=i+1;j<a.size();j++){
                if (b.get(i)<b.get(j)){
                    int temp=b.get(i);
                    b.set(i,b.get(j));
                    b.set(j,temp);
                    char c=a.get(i);
                    a.set(i,a.get(j));
                    a.set(j,c);
                }
            }
        }
        int eng=0,num=0,chinese=0,punc=0;
        for(int i=0;i<text.length();i++){
            if(text.charAt(i)<58&&text.charAt(i)>47){
                num++;
            }else if((text.charAt(i)<91&&text.charAt(i)>64)||(text.charAt(i)<123&&text.charAt(i)>96)){
                eng++;
            }
            else if(text.charAt(i) >= 0x4E00 &&  text.charAt(i) <= 0x9FA5){
                chinese++;
            }else{
                punc++;
            }
        }
        model.addAttribute("eng",eng);
        model.addAttribute("num",num);
        model.addAttribute("chinese",chinese);
        model.addAttribute("punc",punc);
        model.addAttribute("text",text);
        model.addAttribute("max1c",a.get(0));
        model.addAttribute("max2c",a.get(1));
        model.addAttribute("max3c",a.get(2));
        model.addAttribute("max1n",b.get(0));
        model.addAttribute("max2n",b.get(1));
        model.addAttribute("max3n",b.get(2));
        return "index";
    }
}
