package com.ssafy.honeySchool.api.controller;

import java.io.File;
import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.honeySchool.api.dto.ClassBoardDto;
import com.ssafy.honeySchool.api.dto.CommentDto;
import com.ssafy.honeySchool.api.dto.NoticeDto;
import com.ssafy.honeySchool.api.service.BoardService;
import com.ssafy.honeySchool.api.service.NoticeService;
import com.ssafy.honeySchool.db.entity.ClassBoard;
import com.ssafy.honeySchool.db.entity.ClassBoardFile;
import com.ssafy.honeySchool.db.entity.Comment;
import com.ssafy.honeySchool.db.entity.DeleteYn;
import com.ssafy.honeySchool.db.entity.Notice;
import com.ssafy.honeySchool.db.entity.NoticeFile;
import com.ssafy.honeySchool.db.entity.User;
import com.ssafy.honeySchool.db.repository.ClassBoardFileRepository;
import com.ssafy.honeySchool.db.repository.ClassBoardRepository;
import com.ssafy.honeySchool.db.repository.CommentRepository;
import com.ssafy.honeySchool.db.repository.NoticeFileRepository;
import com.ssafy.honeySchool.db.repository.NoticeRepository;
import com.ssafy.honeySchool.db.repository.UserRepository;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/board")
public class NoticeController {
		
	@Autowired
	private NoticeRepository noticeRepository;

	@Autowired
	private NoticeFileRepository noticeFileRepository;
	
	// ?????? ???????????? ?????? ??? ??????
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private NoticeService noticeService;
	
	// ???????????? ?????? ??????
	@GetMapping("/notice")
	public ResponseEntity<?> selectNotice(Pageable pageable) throws SQLException{
//		List<NoticeDto> noticeDtos = noticeService.findAll(pageable).getContent();				
		Page<NoticeDto> noticeDtos = noticeService.findAll(pageable);				
		return new ResponseEntity<Page<NoticeDto>>(noticeDtos, HttpStatus.OK);
	}
	// ???????????? ??????
	@PostMapping("/notice")
	public ResponseEntity<?> createNotice(
			HttpServletRequest req,
			@RequestPart(value="files", required = false) List<MultipartFile> files
	) throws Exception{
		// ???????????? ??????
		String rootPath = request.getSession().getServletContext().getRealPath("/uploads");
		// ?????? ?????? ?????? ??????
		String resourcesPath = rootPath.substring(0, rootPath.length()-14) + "resources\\static\\uploads";	
		rootPath = resourcesPath;
		
		// User ?????? ???
        User user = userRepository.findByUserId(req.getParameter("userId")).get();
               
        // notice ??????
        Notice notice = noticeRepository.save(Notice.builder()
				.title(req.getParameter("title"))
				.content(req.getParameter("content"))
				.user(user)
				.viewcount(0)
				.build());
        // ?????? ??????
        Notice sameNotice = noticeService.addNotice(notice, files, rootPath);

		URI uriLocation = new URI("/board/" + notice.getId());
        return ResponseEntity.created(uriLocation).body("{}");	
	}
	// ???????????? ?????? (+ ?????? ?????? ?????????)
	@Transactional
	@GetMapping("/notice/{noticeId}")
	public ResponseEntity<Map<String, Object>> detailNotice(@PathVariable int noticeId, HttpServletRequest req) {
		Notice notice = noticeRepository.findById(noticeId);
		noticeRepository.updateView(noticeId);  // ????????? ??????
		// notice dto??? ?????????
		NoticeDto noticeDto = NoticeDto.from(notice);
		
		List<NoticeFile> files = noticeFileRepository.findAllByNoticeIdAndIsDeleted(noticeId, DeleteYn.N);
		// Map ???????????? ??????
		Map<String, Object> map = new HashMap<>();
		map.put("board", noticeDto);
		map.put("files", files);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	// ???????????? ?????? - ??????????????? ?????? ?????????
	@DeleteMapping("/notice/{noticeId}")
	public HttpStatus deleteBoard(@PathVariable int noticeId) {
		// ???????????? ?????? ?????????
		noticeFileRepository.deleteFile(noticeId);
			
		Notice notice = noticeRepository.findById(noticeId);
		noticeRepository.delete(notice);
		return HttpStatus.OK;
	}
	// ???????????? ??????
	@Transactional
	@PutMapping("/notice/{noticeId}")
	public ResponseEntity<?> updateNotice(
			@PathVariable int noticeId,
			HttpServletRequest req, 
			@RequestPart(value="files", required = false) List<MultipartFile> files) 
	throws Exception{
		Notice notice = noticeRepository.findById(noticeId);
		// ????????? ???????????? ???????????? ?????? ?????? (?????? ?????? ??????)
		noticeFileRepository.deleteFile(noticeId);
		// ?????? ???????????? ???????????? ??????
		String rootPath = request.getSession().getServletContext().getRealPath("/uploads");
		String resourcesPath = rootPath.substring(0, rootPath.length()-14) + "resources\\static\\uploads";	
		rootPath = resourcesPath;
		Notice sameNotice = noticeService.addNotice(notice, files, rootPath);
		// ?????? ?????? ??????
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		notice.update(title, content);
		// Dto??? ??????
		NoticeDto noticeDto = NoticeDto.from(notice);
		return new ResponseEntity<NoticeDto>(noticeDto, HttpStatus.OK);
	}
}
