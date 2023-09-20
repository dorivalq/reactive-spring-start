package com.reactive;

import com.reactive.domain.AnalysisResult;
import com.reactive.domain.FileAnalysisResponse;
import com.reactive.domain.LoginResponse;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reactive")
public class TestController {


    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public String login() {
        final LoginResponse response = new LoginResponse();
        return "{\"oms-csrf-token\": \"YWVjMmZhM2IyYTg3OGQxZjEzNjA4ZGY1OTU0MGRmZmY=\",\n" + "\"session_id\": \"9c6b1a98b4914baf8ff029aeeaa08cf0\"\n" + "}";

    }

    @PostMapping("/login2")
    public ResponseEntity<LoginResponse> login2() {
        final LoginResponse response = new LoginResponse();
        response.setOmsCsrfToken("YWVjMmZhM2IyYTg3OGQxZjEzNjA4ZGY1OTU0MGRmZmY=");
        response.setSessionId("9c6b1a98b4914baf8ff029aeeaa08cf0");
        return ResponseEntity.ok(response);

    }

    @GetMapping("/login")
    public String getLogin() {
        new DefenderClient().returnClient();

        return "{\n" +
                "    \"oms-csrf-token\": \"YWVjMmZhM2IyYTg3OGQxZjEzNjA4ZGY1OTU0MGRmZmY=\",\n" +
                "    \"session_id\": \"9c6b1a98b4914baf8ff029aeeaa08cf0\"\n" +
                "}";
    }

    @PostMapping(value = "/file/sync", consumes = MediaType.APPLICATION_OCTET_STREAM_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FileAnalysisResponse> analyseFile(@RequestBody Resource inputStream){
//    public ResponseEntity<FileAnalysisResponse> analyseFile(@RequestPart OpeningBalancesInput openingBalancesInput,
//                                                            @RequestPart List<MultipartFile> list) {
        final FileAnalysisResponse analysisResponse = FileAnalysisResponse.builder()
                .fileName("filename.txt")
                .result(AnalysisResult.CLEAN)
                .build();
        return ResponseEntity.ok(analysisResponse);
        ///reactive/file/sync
    }

}
