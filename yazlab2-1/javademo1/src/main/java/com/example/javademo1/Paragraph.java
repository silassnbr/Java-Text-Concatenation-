package com.example.javademo1;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
public class Paragraph {
    final long startTime =System.currentTimeMillis();
    long endTime;
    Instant start = Instant.now();
    Instant end;
    Duration timeElapsed;
    public StringBuilder ArrayConvert(String [] input_texts ) {
        ArrayList<StringBuilder> sbTextArray = new ArrayList<StringBuilder>();
        for (int i = 0; i < input_texts.length; i++) {
            StringBuilder sb = new StringBuilder(" " + input_texts[i].toLowerCase().replaceAll("\\n", " ") + " ");
            sbTextArray.add(sb);
        }
        for (int i = 1; i < input_texts.length; i++) {
            sbTextArray.set(0, comparison(sbTextArray, i));
        }
        return sbTextArray.get(0);
    }

    public StringBuilder comparison(ArrayList<StringBuilder> sbTextArray, int n) {
        //String text = " " + sbTextArray.get(0).toString().trim() + " ";
        String[] text2 = (sbTextArray.get(n).toString().trim()).split(" ");
        ArrayList<ArrayList<Integer>> cursor = new ArrayList<>();
        int first_match = -1;
        int last_match = -1;
        int last_cursor = sbTextArray.get(0).length();
        int count = 0;
        int index = -1;
        for (int i = 0; i < text2.length; i++) {
            /*System.out.println(text2[i].toString());*/
            index = sbTextArray.get(0).indexOf(" " + text2[i] + " ", last_match);

            if (index != -1 && index <= last_cursor) {//kelime birebir aynı olmalı ek benzerliği olmayacak

                System.out.println(" eşleşen: " + text2[i].toString() + " " + sbTextArray.get(0).indexOf(" " + text2[i] + " ", last_match));
                if (count == 0) {
                    first_match = sbTextArray.get(0).indexOf(" " + text2[i] + " ", last_match);
                    last_match = first_match;
                }
                last_match += text2[i].length() + 1;
                try {
                    last_cursor = last_match + text2[i + 1].length() + 1;
                } catch (Exception ArrayIndexOutOfBoundsException) {//sonuncu kelime ise
                    last_cursor = last_match;

                }

                count += 1;

            } else if (count != 0) {//daha önce benzerlik bulundu
                System.out.println("eşleşme durdu " + text2[i].toString());
                ArrayList<Integer> array = new ArrayList<>();
                array.add(first_match);
                array.add(last_match);
                array.add(count);
                cursor.add(array);

                System.out.println(cursor);
                last_cursor = sbTextArray.get(0).length();
                count = 0;
                i = -1;//0yapınca döngü başında 1 oluyo
            }
            if (count != 0 && cursor.isEmpty() && i == (text2.length - 1)) {//süreyi çok uzattı
                System.out.println("tamamen ardışık eşlendi");
                ArrayList<Integer> array = new ArrayList<>();
                array.add(first_match);
                array.add(last_match + 1);//buraya neden +1 ekleyince son kelimeyi doğru alıyo ????? diğer türlü hatalı
                array.add(count);
                cursor.add(array);
            }

        }

        if (!cursor.isEmpty()) {//en çok eşleşen kısmı ilk stringten silip ikinciyi o kısma ekle
            ArrayList<Integer> loc = longMatch(cursor);
            /*System.out.println(loc.toString());*/
            System.out.println("ardışık eşleşme " + sbTextArray.get(0).substring(loc.get(0), loc.get(1)));
            System.out.println("kelime sayısı: " + loc.get(2));

            /*text=text.replace(text.substring(loc.get(0),loc.get(1)),sbTextArray.get(n-1));*/
            sbTextArray.set(0, sbTextArray.get(0).replace(loc.get(0), loc.get(1), sbTextArray.get(n).toString()));
        } else {
            sbTextArray.set(0, sbTextArray.get(0).append(sbTextArray.get(n)));//eşleşme yoksa arka arkaya ekle
        }

        endTime =System.currentTimeMillis() - startTime;
        System.out.println("fonk pargrf time"+ endTime);
        end = Instant.now();
        timeElapsed = Duration.between(start, end);
        return sbTextArray.get(0);
    }

    public ArrayList<Integer> longMatch(ArrayList<ArrayList<Integer>> cursor) {//en uzun ardışık eşleşme
        ArrayList<Integer> temp = cursor.get(0);
        int wordLen;
        int tempLen;
        for (int i = 0; i < cursor.size(); i++) {
            if (temp.get(2) < cursor.get(i).get(2))
                temp = cursor.get(i);

            else if (temp.get(2) == cursor.get(i).get(2)) {
                wordLen = cursor.get(i).get(1) - cursor.get(i).get(0);
                tempLen = temp.get(1) - temp.get(0);
                if (wordLen > tempLen) ;
                temp = cursor.get(i);
            }
            System.out.println(cursor);
        }
        return temp;
    }
}