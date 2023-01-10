package com.sdet.sdetPractise.javaStreams;


import com.google.common.collect.Streams;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class JavaStreams {


    @Test
    public  void ListSample(){

        ArrayList<String> names= new ArrayList<>();
        names.add("Ashish");
        names.add("Aishwarya");
        names.add("Akash");
        names.add("Shukaul");
        names.add("Rima");

        int count=0;

        for(int i =0;i<names.size();i++){

            if(names.get(i).contains("A"));
                     count++;
        }
        System.out.println(count);
    }

    @Test
    public  void StreamSample(){


        //aggregate operation that we perform on the collection( filter in this case ) do not affect the original data
        //names in this case

        //intermediate operation will be performed only if we have a terminal operation



        ArrayList<String> names= new ArrayList<>();
        names.add("Ashish");
        names.add("Aishwarya");
        names.add("Akash");
        names.add("Shukaul");
        names.add("Rima");

       long count=  names.stream().filter(s->s.startsWith("A")).count();
      // System.out.println(count);



        // We need not always have the collections class object converted to streams. We can define Stream.of to create streams

       long  count2=  Stream.of("Ashish","Aishwarya","Akash","Shaukaul","Rima").filter(s->
               {
                   s.startsWith("A");
                    return true;

                   //terminal operation will execute only when the intermediate operation (filter) in this case
                   //return true
               }
                ).count();

        System.out.println(count2);



    }

    @Test
    public void streamMap(){

        //To print only the names ending with h and that too in upper case


        Stream.of("Ashish","Aishwarya","Akash","Shaukaul","Rima").filter(s->s.endsWith("h"))
                .map(s->s.toUpperCase()).forEach(s->System.out.println(s));



    }

    @Test
    public void streamLimit(){

        ArrayList<String> names= new ArrayList<>();
        names.add("Ashish");
        names.add("Aishwarya");
        names.add("Akash");
        names.add("Shukaul");
        names.add("Rima");

        //To print only the first name from the stream where the length is greater than 5

        names.stream().filter(s->s.length()>5).limit(1).forEach(s->System.out.println(s));


    }

    @Test
    public void streamLength(){

        ArrayList<String> names= new ArrayList<>();
        names.add("Ashish");
        names.add("Aishwarya");
        names.add("Akash");
        names.add("Shukaul");
        names.add("Rima");

        //To print all the names where the length is greater than 5

        names.stream().filter(s->s.length()>5).forEach(s->System.out.println(s));


    }

    @Test
    public void streamSort(){


        //To print all the names which starts with A in the sorted order

        List<String> names = Arrays.asList("Ashish","Aishwarya","Akash","Shaukaul","Rima");

        names.stream().filter(s->s.startsWith("A")).sorted().map(s->s.toUpperCase()).forEach(s->System.out.println(s));


    }

    @Test
    public void streamMerge(){


        ArrayList<String> names= new ArrayList<>();
        names.add("Ashish");
        names.add("Aishwarya");
        names.add("Akash");

        List<String> names2 = Arrays.asList("Shaukaul","Rima");

        Stream newStream = Stream.concat(names.stream(),names2.stream());
        newStream.sorted().forEach(s->System.out.println(s));

    }

    @Test
    public void streamAnyMatch(){
        List<String> names = Arrays.asList("Shaukaul","Rima");

        boolean found = names.stream().anyMatch(s->s.equalsIgnoreCase("Rima"));
        Assert.assertTrue(found);

    }

    @Test
    public void streamCollectors(){


       List<String> newList = Stream.of("Rima","Ashish","Priti","Nayantata","Paggu").filter(s->s.startsWith("P"))
                .collect(Collectors.toList());

       System.out.println(newList.get(0));

    }

    @Test
    public void streamUniqueAndSort(){


       List<Integer> list = Arrays.asList(1, 3, 4,4, 2, 6,6, 44, 22);

      // list.stream().distinct().sorted().forEach(s->System.out.println(s));
       List<Integer> listed = list.stream().distinct().sorted().collect(Collectors.toList());
       System.out.println(listed.get(2));


    }





}
