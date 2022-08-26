package com.elthobhy.catalogmovie.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DomainModel(
    var overview: String? = null,
    var originalLanguage: String? = null,
    var original_title: String? = null,
    var backdrop_path: String? = null,
    var releaseDate: String? = null,
    var popularity: Double? = null,
    var voteAverage: Double? = null,
    var id: Int,
    var title: String? = null,
    var voteCount: Int? = null,
    var posterPath: String? = null,
    var favorite: Boolean = false,
    var isTvShows: Boolean = false
) : Parcelable