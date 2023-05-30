package com.example.javademo1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Sentence {
    public String ArrayConvert(String[] input_texts ){
        ArrayList<String []> texts = new ArrayList<String []>();
        for (int i=0; i< input_texts.length; i++){
            texts.add(input_texts[i].toLowerCase().split(" "));
        }
        return  comparison(texts);
    }
    public String comparison(ArrayList<String []> texts) {//eşleşme bulma
        long time= System.currentTimeMillis();
        String temp1;
        String temp2;
        float len1=1;
        float len2=1;
        String similarity;
        for(int j=1;j<texts.size();j++){
            for(int k =0;k<texts.get(0).length;k++) {
                for (int l = 0; l < texts.get(j).length; l++) {

                    try{// ilk üç karakter benzer mi
                        temp1=" "+ texts.get(0)[k];
                        temp2=" "+ texts.get(j)[l];
                        if (temp1.contains(temp2) || temp2.contains(temp1)){// brebir içinde geçiyo
                            //System.out.println("içeriyor "+texts.get(j)[l]);
                            len1=texts.get(0)[k].length();
                            len2=texts.get(j)[l].length();
                            if( len1 <len2 && len1/len2 >=0.5){// ikinci uzun olan kelime ana diziye yazılır
                                texts.get(0)[k]=texts.get(j)[l];
                                texts.set(j,removeWord(texts.get(j),l));
                            }
                            else if(len1 >= len2 && len2/len1 >= 0.5)// ilk cümledeki kelime uzun oran %50den fazlaysa birleştirilmez
                                texts.set(j,removeWord(texts.get(j),l));
                            break;
                        }
                        else if(texts.get(0)[k].toLowerCase().substring(0,3).equals(texts.get(j)[l].toLowerCase().substring(0,3))){
                            similarity =letterCombination(texts.get(0)[k], texts.get(j)[l],texts.get(j)[l].substring(0,3));
                            if(!Objects.equals(similarity, "")) {
                                texts.get(0)[k] = similarity;
                                texts.set(j, removeWord(texts.get(j), l));
                            }else {
                                System.out.println("birleştirme yapılmadı benzerlik oranı düşük"+texts.get(0)[k]+texts.get(j)[l]);
                            }

                        }
                    }catch (Exception StringIndexOutOfBoundsException){// üçten az karakterliyse ?????
                        if(texts.get(0)[k].contains(" "+texts.get(j)[l]) || texts.get(j)[l].contains(" "+texts.get(0)[k])){// kısa olan iki karakterli ile diğerinin ilk iki karakter aynıysa
                            if(texts.get(0)[k].length()<texts.get(j)[l].length()){
                                texts.get(0)[k]=texts.get(j)[l];// uzun olan kelimeyi döndür
                            }
                            texts.set(j,removeWord(texts.get(j),l));
                        }//kelime equals ise remove contain koşulu eklenecek
                    }
                }
            }
            texts.set(0,addArray(texts.get(0),texts.get(j)));
            texts.remove(1);
            j=0;//while ile texts.len!=1 yapılabilir
        }

        return String.join(" ",texts.get(0));
    }
    public String letterCombination (String text1, String text2, String matches_word){
        String shortWord ;
        float count=3;
        if(text1.length()<=text2.length()){//kısa kelime bulunur
            shortWord=text1;
        }else {
            shortWord = text2;
            text2=text1;
        }
        for(int i =3; i<shortWord.length() ;i++) {
            if(shortWord.charAt(i) == text2.charAt(i)) {
                matches_word += shortWord.charAt(i);
                count+=1;
            }
            else {
                if((count / shortWord.length()) >= 0.5) {
                    System.out.println("benzerlik oranı %50 den fazla"+ shortWord);
                    matches_word = shortWord + text2.substring(i);//ekleri farklı ilk üç ve fazlası ortak
                    return matches_word;
                }
                else {
                    System.out.println("benzerlik oranı %50 den az" +shortWord);
                    return "";
                }
            }
        }
        matches_word+=text2.substring(shortWord.length());//biri diğerinin içinde tamamen var
        return  matches_word;//uzun olanı döndürür
    }

    public static String [] removeWord(String[] text, int index) {
        String[] newArray = new String[text.length - 1];
        for (int i = 0, k = 0; i < text.length; i++) {
            if (i != index) {
                newArray[k++] = text[i];
            }
        }
        return newArray;
    }
    public static String [] addArray(String[] text, String[] text2) {
        //System.out.println(Arrays.toString(text) +""+ Arrays.toString(text2));
        int maxSize = text.length +text2.length;
        String[] newArray = Arrays.copyOf(text, maxSize);
        for (int i = 0; i < text2.length; i++) {
            newArray[text.length+i] = text2[i];
        }
        return newArray;
    }

}