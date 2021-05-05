package com.cadiz.accenture_test.repository

import com.cadiz.accenture_test.api.Repository

interface RepositorySelectListener {
    fun RepositorySelected(repository: Repository)
}