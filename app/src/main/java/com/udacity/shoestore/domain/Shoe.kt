package com.udacity.shoestore.domain

data class Shoe(
    var name: String,
    var size: Double,
    var company: String,
    var description: String,
    val images: List<String>
)