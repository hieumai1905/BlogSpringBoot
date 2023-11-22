package com.example.webblog.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "rates")
@Data
@NoArgsConstructor
public class Rate {
    @EmbeddedId
    private RateId rateId;

    @ManyToOne
    @MapsId("postId")
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @MapsId("accountId")
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "rating_value", nullable = false)
    private int value;
}