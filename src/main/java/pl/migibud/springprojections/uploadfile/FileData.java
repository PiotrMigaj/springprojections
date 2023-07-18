package pl.migibud.springprojections.uploadfile;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="file_data")
@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@ToString
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FileData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    private String filePath;

}
