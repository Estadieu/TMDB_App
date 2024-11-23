package com.example.my_application1.ui.Model

data class CollectionResponse(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)