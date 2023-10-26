package com.universal.containx.model;

import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "coin_transaction")
public class CoinTransaction {


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "user_id", nullable = false)
private User user;

@Enumerated(EnumType.STRING)
@Column(name = "transaction_type", nullable = false)
private TransactionType transactionType = TransactionType.REFERRAL;

@Column(name = "amount", nullable = false)
private Integer amount;

@Column(name = "source")
private String source;

@Column(name = "created_date", nullable = false)
@Temporal(TemporalType.TIMESTAMP)
private Date createdDate = new Date();

public CoinTransaction(User user, TransactionType transactionType, Integer amount) {
    this.user = user;
    this.transactionType = transactionType;
    this.amount = amount;
}
}

