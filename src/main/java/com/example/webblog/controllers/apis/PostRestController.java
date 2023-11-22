package com.example.webblog.controllers.apis;

import com.example.webblog.models.Account;
import com.example.webblog.models.Category;
import com.example.webblog.models.Post;
import com.example.webblog.models.Type;
import com.example.webblog.requestmodel.PostRequest;
import com.example.webblog.servies.account.IAccountService;
import com.example.webblog.servies.category.ICategoryService;
import com.example.webblog.servies.post.IPostService;
import com.example.webblog.servies.type.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("api/posts")
public class PostRestController {
    @Autowired
    private IPostService postService;

    @Autowired
    private ITypeService typeService;

    @Autowired
    private IAccountService accountService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private ServletContext servletContext;

    @Autowired
    private HttpServletRequest request;

    @GetMapping
    public ResponseEntity<List<PostRequest>> getPosts() {
        return ResponseEntity.ok((List<PostRequest>) postService.findAll());
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostRequest> getPostById(@PathVariable("postId") Long postId) {
        Optional<PostRequest> post = postService.findById(postId);
        if (post.isPresent()) {
            return ResponseEntity.ok(post.get());
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/types/{typeId}")
    public ResponseEntity<List<PostRequest>> getPostByTypeId(@PathVariable("typeId") Long typeId) {
        Optional<Type> type = typeService.findById(typeId);
        if (!type.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<PostRequest> posts = postService.getAllPostByTypeId(typeId);
        if (posts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.ok(posts);
        }
    }

    @GetMapping("/search/{title}")
    public ResponseEntity<List<PostRequest>> getAllPostByTitle(@PathVariable("title") String title) {
        List<PostRequest> posts = postService.getAllPostByTitle(title);
        if (posts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.ok(posts);
        }
    }

    @GetMapping("/activePost/{id}")
    public ResponseEntity<Boolean> activePost(@PathVariable("id") Long id) {
        Boolean isSuccess = postService.activePost(id);
        if (!isSuccess) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.ok(true);
        }
    }

    @GetMapping("/accounts/{accountId}")
    public ResponseEntity<List<PostRequest>> getPostByAccountId(@PathVariable("accountId") Long accountId) {
        Optional<Account> account = accountService.findById(accountId);
        if (!account.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<PostRequest> posts = postService.getAllPostByAccountId(accountId);
        if (posts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.ok(posts);
        }
    }

    @GetMapping("/categories/{categoryId}")
    public ResponseEntity<List<PostRequest>> getPostByCategoryId(@PathVariable("categoryId") Long categoryId) {
        Optional<Category> category = categoryService.findById(categoryId);
        if (!category.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<PostRequest> posts = postService.getAllPostByCategoryId(categoryId);
        if (posts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.ok(posts);
        }
    }

    @PostMapping
    public ResponseEntity<PostRequest> createPost(@ModelAttribute PostRequest postRequest,
                                                  @RequestParam("pictureFile") MultipartFile picture) {
        uploadImage(picture);
        postRequest.setPicture("/img-upload/" + StringUtils.cleanPath(Objects.requireNonNull(picture.getOriginalFilename())));
        PostRequest createdPost = postService.save(postRequest);
        return ResponseEntity.ok(createdPost);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostRequest> updatePost(@PathVariable("postId") Long postId, @ModelAttribute PostRequest postRequest,
                                                  @RequestParam("pictureFile") MultipartFile picture) {
        Optional<PostRequest> existingPost = postService.findById(postId);
        if (existingPost.isPresent()) {
            postRequest.setPostId(postId);
            uploadImage(picture);
            postRequest.setPicture("/img-upload/" + StringUtils.cleanPath(Objects.requireNonNull(picture.getOriginalFilename())));

            PostRequest updatedPost = postService.save(postRequest);
            return ResponseEntity.ok(updatedPost);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update-no-picture/{postId}")
    public ResponseEntity<PostRequest> updatePostWithoutPicture(@PathVariable("postId") Long postId,
            @ModelAttribute PostRequest postRequest) {

        Optional<PostRequest> existingPost = postService.findById(postId);
        if (existingPost.isPresent()) {
            postRequest.setPostId(postId);
            postRequest.setPicture(existingPost.get().getPicture());
            PostRequest updatedPost = postService.save(postRequest);
            return ResponseEntity.ok(updatedPost);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Boolean> deletePost(@PathVariable("postId") Long postId) {
        Optional<PostRequest> postRequest = postService.findById(postId);
        if (!postRequest.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        boolean isDeleted = postService.remove(postId);
        if (isDeleted) {
            return ResponseEntity.ok(true);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public String uploadImage(MultipartFile picture){
        String img = "";
        if (!picture.isEmpty()) {
            try {
                String fileName = StringUtils.cleanPath(Objects.requireNonNull(picture.getOriginalFilename()));

                // Define the directory to store the uploaded file
                String uploadDir = "WEB-INF/static/img-upload";

                // Get the root path of the web application
                String rootPath = servletContext.getRealPath("/");

                // Create the directory if it does not exist
                Path dirPath = Paths.get(rootPath, uploadDir);
                if (!Files.exists(dirPath)) {
                    Files.createDirectories(dirPath);
                }

                // Construct the path where the file will be stored
                Path filePath = Paths.get(rootPath, uploadDir, fileName);

                // Save the file to the specified directory
                Files.copy(picture.getInputStream(), filePath);

                return "/img-upload/"+fileName;

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return img;
    }
}