package com.example.brainbyte

data class UrlData(
    val adult: Boolean,
    val category: String,
    val content_type: String,
    val country_code: String,
    val dns_valid: Boolean,
    val domain: String,
    val domain_age: DomainAge,
    val domain_rank: Int,
    val ip_address: String,
    val language_code: String,
    val malware: Boolean,
    val message: String,
    val page_size: Int,
    val parking: Boolean,
    val phishing: Boolean,
    val redirected: Boolean,
    val request_id: String,
    val risk_score: Int,
    val server: String,
    val spamming: Boolean,
    val status_code: Int,
    val success: Boolean,
    val suspicious: Boolean,
    val unsafe: Boolean
)