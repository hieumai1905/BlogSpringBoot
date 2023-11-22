package com.example.webblog.servies.rate;

import com.example.webblog.mappers.RateMapper;
import com.example.webblog.models.Account;
import com.example.webblog.models.Post;
import com.example.webblog.models.Rate;
import com.example.webblog.models.RateId;
import com.example.webblog.repositories.AccountRepository;
import com.example.webblog.repositories.PostRepository;
import com.example.webblog.repositories.RateRepository;
import com.example.webblog.requestmodel.RateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RateService implements IRateService {

    @Autowired
    private RateRepository rateRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    HttpServletRequest request;

    @Override
    public Iterable<RateRequest> findAll() {
        return null;
    }

    @Override
    public Optional<RateRequest> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public RateRequest save(RateRequest rateRequest) {
        Rate rate = new Rate();
        Post post = postRepository.findById(rateRequest.getPostId()).get();
        rate.setPost(post);
        Account accountLogin = (Account) request.getSession().getAttribute("account-login");
        Account account = accountRepository.findById(accountLogin.getAccountId()).get();
        rate.setAccount(account);
        rate.setValue(rateRequest.getValue());
        RateId rateId = new RateId(rateRequest.getPostId(), accountLogin.getAccountId());
        rate.setRateId(rateId);
        Rate ratePost = rateRepository.save(rate);
        return RateMapper.rateModelToRateRequest(ratePost);
    }

    @Override
    public Boolean remove(Long id) {
        try {
            rateRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<RateRequest> getRateByPostId(Long postId) {
        List<Rate> rates = rateRepository.getAllByPostPostId(postId);
        List<RateRequest> rateRequests = new ArrayList<>();
        for (Rate rate : rates) {
            rateRequests.add(RateMapper.rateModelToRateRequest(rate));
        }
        return rateRequests;
    }

    @Override
    @Transactional
    public Boolean removeAllByPost(Long postId) {
        try {
            rateRepository.deleteAllByPost_PostId(postId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    @Transactional
    public Boolean removeAllByAccount(Long accountId) {
        try {
            rateRepository.deleteAllByAccount_AccountId(accountId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}