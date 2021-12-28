package com.revature.ttapi.game.services;

import com.revature.ttapi.game.models.Board;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class BoardConverter implements AttributeConverter<Board, String> {

    @Override
    public String convertToDatabaseColumn(Board board) {
        String result = board.toString();
        return result;
    }

    @Override
    public Board convertToEntityAttribute(String s) {
        return null;
    }
}
