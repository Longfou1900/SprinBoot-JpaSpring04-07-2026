package co.istad.longfou.ecommerce.features.file;

import co.istad.longfou.ecommerce.features.file.dto.FileUploadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FileUploadSerrviceImpl implements FileUploadService {
    @Value("${file.storage-location}")
    private String sorageLocation;
//    @Value("${file.basae-uri}")
//    private String baseUri;

    private final FileUploadRepository fileUploadRepository;
    private final FileUploadMapper fileUploadMapper;

    @Override
    public FileUploadResponse findByName(String name) {
        return fileUploadRepository.findByName(name)
                .map(fileUploadMapper::mapFileUploadResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "File has not been found"));
    }

    @Override
    public Page<FileUploadResponse> findALl(int pageNumber, int pageSize) {
        Sort sortById = Sort.by(Sort.Direction.DESC, "id");
        PageRequest pageRequest = PageRequest.of(pageNumber,pageSize, sortById);
        Page<FileUpload> fileUploadResponses = fileUploadRepository.findAll(pageRequest);
        return fileUploadResponses.map(fileUploadMapper::mapFileUploadResponse);
    }

    @Override
    public FileUploadResponse upload(MultipartFile file) {
       return saveFile(file);
    }

    @Override
    public List<FileUploadResponse> uploadMultiple(List<MultipartFile> files) {
        //YOUR MORE LOGIC HERE...
        return files.stream()
//                .map(this::saveFile)
                .map(this::upload)
//                .toList();
                .collect(Collectors.toList());
    }

//    @Override
//    public void deleteByName(String name) {
//        Path path = Paths.get(sorageLocation, name);
//
//        try{
//            Files.deleteIfExists(path);
//        }catch (IOException e){
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Failed to delete file: "+name, e);
//        }
//    }
    private FileUploadResponse saveFile(MultipartFile file) {

        //Prepare file information
        //File name
        String name = UUID.randomUUID().toString();
        //myprofile.png
        String ext = file.getOriginalFilename()
                .substring(file.getOriginalFilename().lastIndexOf("."));

//        name += "." + ext; //new-unique-filename.ext

        //==================================
        // create absolute path to store file
        //==================================
//        Path path = Paths.get(sorageLocation+name);

        Path path = Paths.get(sorageLocation+name+"."+ext);

        try {
            Files.copy(file.getInputStream(), path);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "File has been failed to upload");
        }

        //save information file into db
        FileUpload fileUpload = new FileUpload();
        fileUpload.setName(name);
        fileUpload.setExtension(ext);
        fileUpload.setCaption("ISTAD - Adance IT Institute in Cambodia");
        fileUpload.setSize(file.getSize());
        fileUpload.setMediaType(file.getContentType());
        fileUploadRepository.save(fileUpload);

        return fileUploadMapper.mapFileUploadResponse(fileUpload);
//        return FileUploadResponse.builder()
//                .name(name)
//                .extension(fileUpload.getExtension())
//                .size(file.getSize())
//                .mediaType(file.getContentType())
//                .uri(baseUri+name)
//                .build();
    }

    @Override
    public void deleteByName(String name){
        FileUpload fileUpload = fileUploadRepository.findByName(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ""));
        fileUploadRepository.delete(fileUpload);
        //Create absolute path to store file
        Path path = Paths.get(sorageLocation +fileUpload.getName() + "."+ fileUpload.getExtension());
        try {
            boolean isExisted = Files.deleteIfExists(path);
            if (!isExisted)
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "File has been not Found");
        }catch (IOException e){
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "File has not been found");
        }
    }
}

