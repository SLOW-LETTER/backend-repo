package com.shanep.service;

import com.shanep.enumpkg.ServiceResult;
import com.shanep.model.Board;
import com.shanep.model.ErrorResponse;
import com.shanep.model.Result;
import com.shanep.repositories.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardServiceImpl implements BoardService{


	@Autowired
	BoardRepository repository;

	@Override
	public Result updateBoard(Board board) {
		Optional<Board> search = repository.findById(board.getBoardno());
		Result result = new Result();
		if(search.isPresent()) {
			board = repository.save(board);
			result.setPayload(board);
		}else {
			result.setMessage(new ErrorResponse(ServiceResult.NOTEXIST.toString()));
		}
		return result;
	}


	@Override
	public Result deleteBoard(int boardno) {
		Result result = new Result();
		boolean isPresent = repository.findById(boardno).isPresent();
		if(!isPresent) {
			result.setMessage(new ErrorResponse(ServiceResult.NOTEXIST.toString()));
		}else {
			repository.deleteById(boardno);
		}
		return result;
	}

	@Override
	public Result createBoard(Board board) {
		board = repository.save(board);
		Result result = new Result();
		result.setPayload(board);
		return result;
	}

	@Override
	public Result retrieveBoardList() {
		List<Board> list = repository.findAllByOrderByBoardnoDesc();
		Result result = new Result();
		result.setPayload(list);
		return result;
	}

	@Override
	public Result retrieveBoard(int boardno) {
		Optional<Board> optionalBoard = repository.findById(boardno);
		Result result = new Result();
		if(optionalBoard.isPresent()) {
			result.setPayload(optionalBoard.get());
		}else {
			result.setMessage(new ErrorResponse(ServiceResult.NOTEXIST.toString()));
		}
		return result;
	}
}
