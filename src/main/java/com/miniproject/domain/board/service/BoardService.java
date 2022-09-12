package com.miniproject.domain.board.service;

import com.miniproject.domain.board.Board;
import com.miniproject.global.result.Result;

public interface BoardService {
	public Result createBoard(Board board);
	public Result retrieveBoardList();
	public Result retrieveBoard(int boardno);
	public Result updateBoard(Board board);
	public Result deleteBoard(int boardno);
}
