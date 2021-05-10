package com.cadiz.accenture_test.pullrequest

import com.cadiz.accenture_test.api.PullRequest
import com.cadiz.accenture_test.api.Repository

interface PullRequestSelectListener {
    fun PullRequestSelected(pull: PullRequest)
}