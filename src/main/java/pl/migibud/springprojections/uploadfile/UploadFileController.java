package pl.migibud.springprojections.uploadfile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
@RestController
@RequestMapping("images")
@RequiredArgsConstructor
class UploadFileController {

    private final UploadFileService uploadFileService;

    @PostMapping
    String uploadImage(@RequestParam("file") MultipartFile file) {
        try (InputStream inputStream = file.getInputStream()) {
            return uploadFileService.uploadImage(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    inputStream
            );
        } catch (IOException e) {
            throw new IllegalStateException("Something wrong while uploading file...", e);
        }
    }

    @GetMapping("{fileName}")
    ResponseEntity<byte[]> downloadImage(@PathVariable String fileName) {
        byte[] imageData = uploadFileService.downloadImage(fileName);
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }

    @GetMapping("resource/{fileName}")
    ResponseEntity<Resource> serveFile(@PathVariable String fileName) throws IOException {
        Resource file = uploadFileService.serveFile(fileName);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(Path.of(fileName)))
                .body(file);
    }

}
