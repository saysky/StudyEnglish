package com.example.dto;

import com.example.pojo.Word;
import lombok.Data;

import java.util.List;

/**
 * @author 言曌
 * @date 2020/12/12 12:18 下午
 */
@Data
public class LetterDTO {
    private String letter;

    private List<Word> wordList;

    public LetterDTO(String letter) {
        this.letter = letter;
    }

    public LetterDTO() {
    }
}
