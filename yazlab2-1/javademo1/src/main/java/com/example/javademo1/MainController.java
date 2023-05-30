package com.example.javademo1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static com.example.javademo1.Number.kutuNumber;

@Controller
public class MainController {
    @Autowired
    Repository repo;
    ArrayList<StringBuilder> sbTextArray = new ArrayList<StringBuilder>();//default 10
    ArrayList<String> textArray = new ArrayList<>();//default 10
    List<String> metinler = new ArrayList<>();


    @GetMapping(path="/firstPage")
    public String showHomePage(Model model)
    {
        Note note = new Note();
        model.addAttribute("note", note);
        model.addAttribute("textArea", "");
        String noteString=note.toString();
        ArrayList<String> stringList = new ArrayList<>();
        stringList.add(noteString);

        System.out.println(kutuNumber);
        metinler.add(String.valueOf(note.noteDetail));

        List<String> items = new ArrayList<>();
        items.add("Item 1");
        items.add("Item 2");
        items.add("Item 3");
        List<String> texts = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            texts.add("Text " + i);
        }


        model.addAttribute("texts", texts);
        model.addAttribute("items", items);

        return "index.html";
    }
    @PostMapping(value="/firstPage")
    public String doStuffMethod(@ModelAttribute("note") Note note, Model model)  {

        System.out.println(note);
        return "index";
    }


    @PostMapping(value="/submitForm")
    public String yonlendir(@RequestParam("textareaValues") String [] textareaValues, Model model) {
        Word word = new Word();
        Paragraph paragraph = new Paragraph();
        Sentence sentence = new Sentence();
        model.addAttribute("textareaValues", textareaValues);
        Instant start = Instant.now();
        System.out.println(start);
        int temp=0;
        String addSentence = "Birleştimr Yapılamadı";

        for (String textareaValue : textareaValues) {
            if (!(textareaValue.contains(" "))) {
                if (temp <= 0) temp = 1;
                System.out.println("kelime");
            } else if (textareaValue.contains(" ") && !textareaValue.contains("\n")) {
                if (temp < 2) temp = 2;
                System.out.println("cümle");

            } else if (textareaValue.contains("\n")) {
                if (temp < 3) temp = 3;
                System.out.println("paragraf");

            }
        }

        switch (temp) {
            case 1:
                System.out.println("kelime Algo Çalıştı");
                addSentence = String.valueOf(word.ArrayConvert(textareaValues));
                break;

            case 2:
                System.out.println("cümle Algo Çalıştı");
                addSentence = String.valueOf(sentence.ArrayConvert(textareaValues));
                break;

            case 3:
                System.out.println("paragraf Algo Çalıştı");
                addSentence = String.valueOf(paragraph.ArrayConvert(textareaValues));
                break;

            default :
                System.out.println("cümle Algo Çalıştı");
                addSentence = sentence.ArrayConvert(textareaValues);
                break;

        }
        for (int i = 0; i < 100000; i++) {
            System.out.print("");
        }
        Instant end = Instant.now();
        System.out.println(end);
        Duration timeElapsed = Duration.between(start, end);
        System.out.println("süre "+timeElapsed.toMillis());
        model.addAttribute("sure", timeElapsed.toMillis());
        model.addAttribute("valuesArray", addSentence);
        repo.save(new Records(textareaValues,addSentence,timeElapsed.toMillis()));// cümlenin birleşik hali ve süre eklenecek

        return "index2.html";
    }

}
