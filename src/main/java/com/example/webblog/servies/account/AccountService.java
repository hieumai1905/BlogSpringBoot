package com.example.webblog.servies.account;

import com.example.webblog.models.Account;
import com.example.webblog.models.Post;
import com.example.webblog.models.Rate;
import com.example.webblog.repositories.AccountRepository;
import com.example.webblog.repositories.PostRepository;
import com.example.webblog.repositories.RateRepository;
import com.example.webblog.servies.comment.ICommentService;
import com.example.webblog.servies.post.IPostService;
import com.example.webblog.servies.rate.IRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements IAccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private IPostService postService;

    @Autowired
    private ICommentService commentService;

    @Autowired
    private IRateService rateService;

    @Override
    public Iterable<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Optional<Account> findById(Long id) {
        return accountRepository.findById(id);
    }

    @Override
    public Account save(Account account) {
        if (account.getAccountId() == null) {
            return accountRepository.save(account);
        } else {
            Account accountUpdate = accountRepository.findById(account.getAccountId()).orElse(null);
            if (accountUpdate == null) {
                return null;
            }
            accountUpdate.setFullname(account.getFullname());
            accountUpdate.setPassword(account.getPassword());
            accountUpdate.setEmail(account.getEmail());
            accountUpdate.setBirthday(account.getBirthday());
            accountUpdate.setGender(account.getGender());
            accountUpdate.setRole(account.getRole());
            return accountRepository.save(accountUpdate);
        }
    }

    @Override
    public Boolean remove(Long id) {
        try {
            List<Post> posts = (List<Post>) postRepository.getAllByAccountAccountId(id);
            for (Post post : posts) {
                postService.remove(post.getPostId());
            }
            commentService.removeAllByAccount(id);
            rateService.removeAllByAccount(id);
            accountRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Account login(String email, String password) {
        return accountRepository.getAccountByEmailAndPassword(email, password);
    }

    @Override
    public Account getAccountByEmail(String email) {
        return accountRepository.getAccountByEmail(email);
    }

    @Override
    public Boolean checkEmailExists(String email) {
        Account account = accountRepository.getAccountByEmail(email);
        if (account == null) {
            return false;
        }
        return true;
    }

    @Override
    public boolean register(String fullname, String email, String password, String birthday, int gender) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDay = dateFormat.parse(birthday);
        Account account = new Account(fullname, password, email, birthDay, gender);
        Account accountRegister = save(account);
        if (accountRegister == null) {
            return false;
        }
        return true;
    }

}