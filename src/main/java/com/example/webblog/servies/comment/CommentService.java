package com.example.webblog.servies.comment;

import com.example.webblog.mappers.CommentMapper;
import com.example.webblog.models.Account;
import com.example.webblog.models.Comment;
import com.example.webblog.models.Post;
import com.example.webblog.repositories.AccountRepository;
import com.example.webblog.repositories.CommentRepository;
import com.example.webblog.repositories.PostRepository;
import com.example.webblog.requestmodel.CommentRequest;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService implements ICommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Iterable<CommentRequest> findAll() {
        List<Comment> comments = commentRepository.findAll();
        List<CommentRequest> commentRequests = new ArrayList<>();
        for (Comment comment : comments) {
            commentRequests.add(CommentMapper.commentModelToCommentRequest(comment));
        }
        return commentRequests;
    }

    @Override
    public Optional<CommentRequest> findById(Long id) {
        Comment comment = commentRepository.findById(id).orElse(null);
        if (comment == null) {
            return Optional.empty();
        }
        CommentRequest commentRequest = CommentMapper.commentModelToCommentRequest(comment);
        return Optional.of(commentRequest);
    }

    @SneakyThrows
    @Override
    public CommentRequest save(CommentRequest commentRequest) {
        Comment comment = new Comment();
        if (commentRequest.getCommentId() != null) {
            comment = commentRepository.findById(commentRequest.getCommentId()).orElse(null);
            if (comment != null) {
                comment.setContent(commentRequest.getContent());
            }
        } else {
            comment.setCommentAt(new Date());
        }
        if (comment.getPost() == null) {
            Post post = postRepository.findById(commentRequest.getPostId()).get();
            comment.setPost(post);
        }
        if (request.getSession().getAttribute("account-login") != null) {
            Account accountLogin = (Account) request.getSession().getAttribute("account-login");
            comment.setAccount(accountLogin);
        }

        comment.setContent(commentRequest.getContent());
        Comment commentPost = commentRepository.save(comment);
        return CommentMapper.commentModelToCommentRequest(commentPost);
    }

    @Override
    public Boolean remove(Long id) {
        try {
            commentRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<CommentRequest> getCommentByPostId(Long postId) {
        List<Comment> comments = commentRepository.getAllByPostPostId(postId);
        List<CommentRequest> commentsRequests = new ArrayList<>();
        for (Comment comment : comments) {
            commentsRequests.add(CommentMapper.commentModelToCommentRequest(comment));
        }
        return commentsRequests;
    }

    @Override
    public Boolean removeAllByPost(Long postId) {
        List<Comment> comments = commentRepository.getAllByPostPostId(postId);
        try {
            for (Comment comment : comments) {
                commentRepository.deleteById(comment.getCommentId());
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean removeAllByAccount(Long accountId) {
        List<Comment> comments = commentRepository.getAllByAccountAccountId(accountId);
        try {
            for (Comment comment : comments) {
                commentRepository.deleteById(comment.getCommentId());
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}