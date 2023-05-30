package com.example.javademo1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
//getter setterlari yazmamak için kullanılıyor
@NoArgsConstructor
@AllArgsConstructor
// constructure kısımlarını otomatık oluşturmak için kullanılıyor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document("records")
public class Records {
    @EqualsAndHashCode.Include

    private String[] sentence1;
    private String addedSentence;
   private Long time;
}
