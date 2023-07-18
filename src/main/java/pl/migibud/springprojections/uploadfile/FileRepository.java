package pl.migibud.springprojections.uploadfile;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface FileRepository extends JpaRepository<FileData,Long> {

    Optional<FileData> findByName(String name);
}
