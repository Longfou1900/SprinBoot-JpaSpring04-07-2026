package co.istad.longfou.ecommerce.features.file;

import co.istad.longfou.ecommerce.features.file.dto.FileUploadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class FileUploadController {

    private final FileUploadService fileUploadService;

    @GetMapping("/{name}")
    public FileUploadResponse findByName(@PathVariable String name) {
        return fileUploadService.findByName(name);
    }

    public Page<FileUploadResponse> findAll(
            @RequestParam(required = false, defaultValue = "0") int pageNumber,
            @RequestParam(required = false, defaultValue = "25") int pageSize
    ){
        return fileUploadService.findALl(pageNumber, pageSize);
    }

    //single file upload
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public FileUploadResponse upload(@RequestPart MultipartFile file){
        return fileUploadService.upload(file);
    }

    //multiple files upload
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(
            value ="/multiple",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<FileUploadResponse> uploadMultiple(
            @RequestPart("files") List<MultipartFile> files)
//    @RequestParam("files") List<MultipartFile> files)
    {
        System.out.println("Number of files = " + files.size());

        files.forEach(file ->
                System.out.println(file.getOriginalFilename())
        );

        return fileUploadService.uploadMultiple(files);
    }

    //delete by name
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{name}")
    public void deletedByName(@PathVariable String name){
        fileUploadService.deleteByName(name);
    }


}
