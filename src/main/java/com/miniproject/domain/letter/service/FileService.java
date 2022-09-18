package com.miniproject.domain.letter.service;

import com.miniproject.domain.letter.dto.FileDto;
import com.miniproject.global.entity.Result;

/**
 * @package : com.miniproject.domain.letter.service;
 * @name : FileService
 * @create-date: 2022.09.157
 * @author : 김현진
 * @version : 1.0.0
 *
 * @update-date :
 * @update-author : 000
 * @update-description :
 */

public interface FileService {
    public Result createFile(FileDto fileDto);

    public Result retrieveFileList();

    public Result deleteFile(int id);
}
