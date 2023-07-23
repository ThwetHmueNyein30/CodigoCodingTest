package com.codigo.codetest.code.data.source.remote.list

import com.squareup.moshi.Json

data class MovieResponse (
    @field:Json(name = "page") val page: Int,
    @field:Json(name = "results") val results: List<MovieDto>,
    @field:Json(name = "total_pages") val totalPages: Int?,
    @field:Json(name = "total_results") val totalResults: Int?,
)