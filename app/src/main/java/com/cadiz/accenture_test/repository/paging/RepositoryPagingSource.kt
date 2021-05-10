package com.cadiz.accenture_test.repository.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.cadiz.accenture_test.api.*

class RepositoryPagingSource() : PagingSource<Int, Repository>() {


    override fun getRefreshKey(state: PagingState<Int, Repository>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repository> {
        return try {
            val currentPage = params.key ?: 1
            val response = service.getRepository(currentPage)
            val repoList = parseResult(response)
            val responseData = mutableListOf<Repository>()
            responseData.addAll(repoList)

            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = currentPage.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
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