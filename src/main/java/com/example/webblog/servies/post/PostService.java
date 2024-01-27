package com.example.webblog.servies.post;

import com.example.webblog.mappers.PostMapper;
import com.example.webblog.models.Account;
import com.example.webblog.models.Post;
import com.example.webblog.models.Rate;
import com.example.webblog.repositories.*;
import com.example.webblog.requestmodel.PostRequest;
import com.example.webblog.servies.comment.ICommentService;
import com.example.webblog.servies.rate.IRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostService implements IPostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private IRateService rateService;

    @Autowired
    private ICommentService commentService;

    @Autowired
    HttpServletRequest request;

    @Override
    public Iterable<PostRequest> findAll() {
        List<PostRequest> posts = new ArrayList<>();
        for (Post post : postRepository.findAll()) {
            posts.add(PostMapper.postModelToPostRequest(post));
        }
        posts.sort((o1, o2) -> o2.getCreateAt().compareTo(o1.getCreateAt()));
        return posts;
    }

    @Override
    public Optional<PostRequest> findById(Long id) {
        Post post = postRepository.findById(id).orElse(null);
        if(post == null) return Optional.empty();
        return Optional.of(PostMapper.postModelToPostRequest(post));
    }

    @Override
    public PostRequest save(PostRequest post) {

        String role = (String) request.getSession().getAttribute("user-role");

        Post postModel = new Post();
        if(post.getPostId() != null) postModel.setPostId(post.getPostId());
        postModel.setTitle(post.getTitle());
        postModel.setContent(post.getContent());
        postModel.setCreateAt(new Date());
        postModel.setBriefContent(post.getBriefContent());
        postModel.setPicture(post.getPicture());
        postModel.setRate(0);
        postModel.setStatus(role.equals("ROLE_ADMIN") ? 1 : 0);

        Account account = (Account) request.getSession().getAttribute("account-login");
        postModel.setAccount(account);
        Optional<com.example.webblog.models.Type> type = typeRepository.findById(post.getTypeId());
        postModel.setType(type.orElse(null));
        Optional<com.example.webblog.models.Category> category = categoryRepository.findById(post.getCategoryId());
        postModel.setCategory(category.orElse(null));
        Post postCreate = postRepository.save(postModel);
        return PostMapper.postModelToPostRequest(postCreate);
    }

    @Transactional
    @Override
    public Boolean remove(Long id) {
        try {
            commentService.removeAllByPost(id);
            rateService.removeAllByPost(id);
            postRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<PostRequest> getAllPostByTypeId(Long typeId) {
        List<Post> posts = (List<Post>) postRepository.getAllByTypeTypeId(typeId);
        List<PostRequest> postRequests = new ArrayList<>();
        for (Post post : posts) {
            postRequests.add(PostMapper.postModelToPostRequest(post));
        }
        return postRequests;
    }

    @Override
    public List<PostRequest> getAllPostByCategoryId(Long categoryId) {
        List<Post> posts = (List<Post>) postRepository.getAllByCategoryCategoryId(categoryId);
        List<PostRequest> postRequests = new ArrayList<>();
        for (Post post : posts) {
            postRequests.add(PostMapper.postModelToPostRequest(post));
        }
        return postRequests;
    }

    @Override
    public List<PostRequest> getAllPostByAccountId(Long accountId) {
        List<Post> posts = (List<Post>) postRepository.getAllByAccountAccountId(accountId);
        List<PostRequest> postRequests = new ArrayList<>();
        for (Post post : posts) {
            postRequests.add(PostMapper.postModelToPostRequest(post));
        }
        return postRequests;
    }

    @Override
    public List<PostRequest> getAllPostByTitle(String title) {
        List<Post> posts = (List<Post>) postRepository.getAllByTitleContaining(title);
        List<PostRequest> postRequests = new ArrayList<>();
        for (Post post : posts) {
            postRequests.add(PostMapper.postModelToPostRequest(post));
        }
        return postRequests;
    }

    @Override
    public Boolean activePost(Long id) {
        Post post = postRepository.findById(id).orElse(null);
        if(post != null){
            post.setStatus(1);
            postRepository.save(post);
            return true;
        }
        return false;
    }
}