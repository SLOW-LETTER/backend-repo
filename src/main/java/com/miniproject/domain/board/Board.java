package com.miniproject.domain.board;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="board")
@Getter
@Setter
@ToString
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer boardno;
	
	private String title;
	private String content;
	private String writer;

//	@OneToMany(mappedBy = "board")
//	List<Comment> comment = new ArrayList<>();
}
