package pl.migibud.springprojections.uploadfile;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Component
@RequiredArgsConstructor
class UploadFileService {

    private static final String FOLDER_PATH = "./asset/images/";

    private final FileRepository fileRepository;

    public String uploadImage(final String fileName, String contentType, final InputStream inputStream) {

        Path filePath = Paths.get(FOLDER_PATH).resolve(fileName);

        FileData fileData = FileData.builder()
                .name(fileName)
                .type(contentType)
                .filePath(filePath.toString())
                .build();

        fileRepository.save(fileData);

        try (OutputStream outputStream = Files.newOutputStream(filePath)) {
            inputStream.transferTo(outputStream);
        } catch (IOException e) {
            throw new IllegalStateException("Can not save the file...", e);
        }
        return "file uploaded successfully: " + filePath;
    }


    public byte[] downloadImage(final String fileName) {
        FileData fileData = fileRepository.findByName(fileName)
                .orElseThrow(() -> new EntityNotFoundException("File with name: '%s' not found.".formatted(fileName)));
        String filePath = fileData.getFilePath();
        try {
            return Files.readAllBytes(Path.of(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Resource serveFile(final String fileName){
        FileData fileData = fileRepository.findByName(fileName)
                .orElseThrow(() -> new EntityNotFoundException("File with name: '%s' not found.".formatted(fileName)));
        String filePath = fileData.getFilePath();
        FileSystemResourceLoader fileSystemResourceLoader = new FileSystemResourceLoader();
        return fileSystemResourceLoader.getResource(filePath);
    }
}
