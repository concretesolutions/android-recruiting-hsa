package com.cadiz.accenture_test.pullrequest

import android.util.Log
import com.cadiz.accenture_test.api.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PullRequestRepository() {



    suspend  fun fetchPullRequest(creator: String, repo: String): MutableList<PullRequest> {
        return withContext(Dispatchers.IO){
            val repoJsonResponse : MutableList<PullJsonResponse> = service.getPullRequest(creator,  repo)
            val pullList = parseResult(repoJsonResponse)
            pullList
        }
    }


    private fun parseResult(pullJsonResponse: MutableList<PullJsonResponse>): MutableList<PullRequest> {

        val pullList: MutableList<PullRequest> = mutableListOf<PullRequest>()



        for (pull: PullJsonResponse in pullJsonResponse) {

            val id = pull.id
            val title = pull.title
            val body = pull.body
            val html_url = pull.html_url
            val user = pull.user
            val login = user.login
            val avatar_url = user.avatar_url



            pullList.add(
                PullRequest(
                    id,
                    title,
                    body,
                    login,
                    avatar_url,
                    html_url

                )
            )

        }


        return pullList
    }
}