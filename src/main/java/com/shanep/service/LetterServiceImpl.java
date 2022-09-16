package com.shanep.service;

import com.shanep.model.Letter;
import com.shanep.model.Result;
import com.shanep.repositories.LetterRepository;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

/**
 * @package : com.shanep.service
 * @name : LetterServiceImpl
 * @create-date: 2022.09.15
 * @author : 김현진
 * @version : 1.0.0
 *
 * @update-date :
 * @update-author : 000
 * @update-description :
 */


public class LetterServiceImpl implements LetterService{
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(LetterServiceImpl.class);

    @Autowired
    LetterRepository repository;

    public Result createLetter(Letter letter) {
        letter = repository.save(letter);
        Result result = new Result();
        result.setPayload(letter);
        result.setMessage("편지 생성 완료");
        return result;
    }

    public Result retrieveReceiver(int id) {
        return null;
    }

    public Result retrieveSender(int id) {
        Optional<Letter> optionLetter = repository.findById(id);
        Result result = new Result();
        if(optionLetter.isPresent()) {
            result.setPayload(optionLetter.get());
            result.setMessage("편지 조회 성공");
        } else {
            //result.setError(new ErrorResponse(ServiceResult.NOTEXIST.toString()));
        }
        return result;
    }

    public Result retrieveLetter(int id) {
        return null;
    }

    public Result deleteLetter(int id) {
        Result result = new Result();
        boolean isPresent = repository.findById(id).isPresent();
        if(!isPresent) {
            //result.setError(new ErrorResponse(ServiceResult.NOTEXIST.toString()));
        } else {
            repository.deleteById(id);
        }
        result.setMessage("편지 삭제 성공");
        return result;
    }
}
