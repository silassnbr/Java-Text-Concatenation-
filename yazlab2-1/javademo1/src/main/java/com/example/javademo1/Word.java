package com.example.javademo1;

import java.util.ArrayList;

public class Word {
    ArrayList<String> first_match_array = new ArrayList<>();

    public String ArrayConvert(String [] input_texts ){// arraylisti uygun liste çevirir
        ArrayList<char[]> texts = new ArrayList<>();
        for (String str : input_texts) {
            char[] charArray = str.toLowerCase().toCharArray();
            texts.add(charArray);
        }
        first_match_array=match(texts.get(0), texts.get(1));
        if (first_match_array.isEmpty())
            return "Benzerlik bulunamadı ";
        else
            return  comparison(texts);
    }
    public  String comparison(ArrayList<char[]> texts) {//eşleşme bulma

        String matches_word;
        ArrayList<String> last_match_array = new ArrayList<>();
        for(int i=2;i<texts.size();i++){
            for(int j=0; j<first_match_array.size(); j++){
                last_match_array.addAll(match(first_match_array.get(j).toCharArray(), texts.get(i)));
            }
            first_match_array=last_match_array;
            last_match_array=new ArrayList<>();
        }
        System.out.println(first_match_array);
        matches_word=letterCombination(texts,first_match_array);
        if(matches_word.isEmpty())
            return "Benzerlik bulunamadı";
        else
            return matches_word;
    }
    public  String letterCombination(ArrayList<char[]> texts, ArrayList<String> match) {
        String text_i;
        String head="";
        String middle="";
        String end="";
        String matchWord="";
        int first_match = 0;
        int last_match = 0;
        int cursor=0;

        for(int i=0;i<match.size();i++){

            middle=match.get(i);
            for(int j=0;j<texts.size();j++){//??????????? ortak kelime sırası
                text_i=String.valueOf(texts.get(j));
                try {
                    first_match=text_i.indexOf(match.get(i));
                    last_match=first_match+match.get(i).length();
                    if(i==0)
                        head+=text_i.substring(cursor,first_match);
                    if(i==match.size()-1)
                        end+=text_i.substring(last_match);
                    else
                        end+=text_i.substring(last_match,text_i.indexOf(match.get(i+1)));
                }catch (Exception StringIndexOutOfBoundsException){
                    break;
                }
            }
            matchWord+=head+middle+end;
            head="";
            end="";

        }
        /*System.out.println("head+middle+end   --- " +matchWord);*/

        return matchWord;
    }
    public  ArrayList<String> match(char[] text1, char[] text2) {//ilk iki kelime arasındaki benzerlik en kısa iki kelime alınabilir???

        ArrayList<ArrayList<Integer>> cursor = new ArrayList<>();
        ArrayList<ArrayList<Integer>> cursor2 = new ArrayList<>();
        ArrayList<String> array = new ArrayList<>();
        ArrayList<Integer> array2 = new ArrayList<>();
        String matches_word = "";

        int count = 0;
        int temp;
        for (int k = 0; k < text1.length; k++) {
            temp = k;
            for (int l = 0; l < text2.length; l++) {

                if ((text1[k] == text2[l]) && !(text1[k] == ' ')) {
                    matches_word += text1[k];
                    if(k!= text1.length-1)
                        k++;
                    else {
                        break; //son harfleri benziyorsa ardışığına bakmasın, yok
                    }
                    count++;
                    //ilk kelimenin bir sonraki harfine geçer
                } else if (text1[k] != text2[l]) {//ikinci harfler benzer değil
                    if(count == 1){
                        matches_word = "";
                        count = 0;
                        k -= 1;
                    }else if (count>1){//ardışık eşleşme bittiyse
                        break;
                    }
                }
            }
            if (matches_word.length()>1) {
                if(!array.toString().contains(matches_word))// kelime içindeki benzerlik olan ardışık kelimeleri tut
                    array.add(matches_word);
                /*System.out.println(matches_word);
                System.out.println(array);*/
                matches_word = "";
                k = temp;
                count=0;
            }
        }
        // array boşsa benzerlik bulunmaz
        return array;
    }

}