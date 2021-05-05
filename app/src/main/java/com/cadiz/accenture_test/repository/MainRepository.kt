package com.cadiz.accenture_test.repository

import com.cadiz.accenture_test.api.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainRepository {

     suspend  fun fetchRepositories(): MutableList<Repository> {
        return withContext(Dispatchers.IO){
            // TODO AGREGAR PAGINACION
            val repoJsonResponse : RepoJsonResponse = service.getRepository(1)
            val repoList = parseResult(repoJsonResponse)
            repoList
        }
    }

    private fun parseResult(ghJsonResponse: RepoJsonResponse): MutableList<Repository> {
        val repoList: MutableList<Repository> = mutableListOf<Repository>()

        val itemList: List<Item> = ghJsonResponse.items


        for (item: Item in itemList) {

            val id = item.id
            val name = item.name
            val description = item.description
            val owner: Owner = item.owner
            val autor = owner.login
            val avatar_url = owner.avatar_url
            val star_count = item.stargazers_count
            val fork_count = item.forks_count
            val login = owner.login

            repoList.add(
                Repository(
                    id,
                    name,
                    description,
                    autor,
                    avatar_url,
                    star_count,
                    fork_count,
                    login
                )
            )
        }
        return repoList
    }
}