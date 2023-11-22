package com.example.webblog.controllers.apis;

import com.example.webblog.models.Rate;
import com.example.webblog.requestmodel.PostRequest;
import com.example.webblog.requestmodel.RateRequest;
import com.example.webblog.servies.post.IPostService;
import com.example.webblog.servies.rate.IRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/rates")
public class RateRestController {

    @Autowired
    private IRateService rateService;

    @Autowired
    private IPostService postService;

    @GetMapping("/posts/{postId}")
    public ResponseEntity<List<RateRequest>> getRateByPostId(@PathVariable Long postId) {
        List<RateRequest> rates = rateService.getRateByPostId(postId);
        return ResponseEntity.ok(rates);
    }

    @RequestMapping(value = "/posts/{postId}", method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity<RateRequest> createOrUpdateRate(@PathVariable Long postId, @RequestBody RateRequest rateRequest) {
        PostRequest postRequest = postService.findById(postId).orElse(null);
        if(postRequest == null){
            return ResponseEntity.notFound().build();
        }
        rateRequest.setPostId(postId);
        RateRequest rate = rateService.save(rateRequest);
        return ResponseEntity.ok(rate);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Boolean> deleteRate(@PathVariable("postId") Long postId) {
        boolean isDeleted = rateService.remove(postId);
        if (isDeleted) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
