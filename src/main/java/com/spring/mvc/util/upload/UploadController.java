package com.spring.mvc.util.upload;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@Slf4j
public class UploadController {
    // 사용자가 보낸 파일을 저장하는 데 DB를 사용하지 않는다.
    // 비용이 많이 발생하기 때문. 저렴한 하드에 파일을 저장하고 DB에는 경로를 심어놓는다.

    private String rootPath = "C://MyWorkspace//upload"; // 저장할 경로 지정

    @GetMapping("/upload-form")
    public String uploadForm() {
        return "upload/upload-form";
    }

    @PostMapping("/upload-file")
    public String uploadForm(@RequestParam("thumbnail") MultipartFile file) {
        //jsp 파일의 input의 name 속성이 이 파라미터로 들어온다. @RequestParam("name 속성 명") 받을 변수
        // 파일은 MultipartFile 타입(스프링에서 파일 업로드용으로 제공하는 객체)으로 받으면 된다.

        log.info("file-name: {}", file.getOriginalFilename()); // 파일명 출력
        log.info("file-size: {}", file.getSize() / 1024.0); // 용량 출력(MB 단위로 넘어오므로 KB 단위로 알고 싶으면 1024로 나누어 준다.)
        log.info("file-type: {}", file.getContentType()); // 파일 타입 출력(jsp input의 accept 속성으로 파일 타입 지정 가능)


        /*
         사용자가 첨부한 파일은 DB에 저장하는 것을 선호하지 않습니다.
         DB 용량을 파일 첨부에 사용하는 것은 비용적으로도 좋지 않기 때문입니다.
         그렇기 때문에 상용되는 서비스들이 파일을 처리하는 방법은 따로 파일 전용 서버를 구축하여
         저장하고, DB에는 파일의 저장 경로를 지정하는 것이 일반적입니다.

         파일 업로드 시 파일명이 동일한 파일이 이미 존재할 수도 있고,
         사용자가 업로드 하는 파일명이 영어 이외의 언어로 되어있을 수 있습니다.
         타 언어를 지원하지 않는 환경에서는 정상 동작이 되지 않을 수 있습니다. (리눅스)
         고유한 랜덤 문자를 통해 DB와 서버에 저장할 파일명을 새롭게 만들어 줍니다.
         */

        // 첨부파일을 서버 스토리지에 저장
        // 1. 루트 디렉토리를 생성
        File root = new File(rootPath);
        if (!root.exists()) root.mkdirs();

        // 2. 랜덤한 문자 조합을 이용해서 새 파일명을 생성
        UUID uuid = UUID.randomUUID(); //하이픈이 있는 32자리 uuid가 생성됨.
        log.info("uuid: {}", uuid.toString());

        String fileName = uuid.toString();
        fileName = fileName.replace("-", ""); // replace로 하이픈 없애기
        log.info("랜덤 파일명: {}", fileName);

        // 3. 원본 파일명에서 확장자 떼기
        // 뒤에서부터 .을 찾아서 추출
        String fileExtension = file.getOriginalFilename()
                .substring(file.getOriginalFilename().lastIndexOf("."));
        log.info("fileExtension: {}", fileExtension);

        // 4. 첨부파일을 파일 객체로 생성
        File uploadFile = new File(rootPath, fileName + fileExtension); // 경로, 파일이름을 가진 객체 생성

        // 5. 지정한 경로로 파일 전송
        // (MultipartFile 스프링 제공 객체의 메서드 사용, 예외 처리 필요)
        try {
            file.transferTo(uploadFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

}
